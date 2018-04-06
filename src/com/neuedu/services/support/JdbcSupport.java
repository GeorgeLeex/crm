package com.neuedu.services.support;

import com.neuedu.system.db.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

public abstract class JdbcSupport implements BaseService{

    /**
     * dto副本
     */
    private Map<String, Object> dto;

    /**
     * 在ControllerSupport中的setDTO方法中被调用,传入dto副本
     */
    @Override
    public void setMapDTO(final Map<String, Object> dto) {
        this.dto = dto;
    }

    /**
     * 往dto中添加数据,用于ServiceImpl取值
     */
    @Override
    public void addValueToDTO(String key, Object value) {
    	this.dto.put(key, value);
    }
    
    /**
     * 为子类提供获取dto的方法
     * @return
     */
    protected Map<String, Object> getMapDto() {
        return this.dto;
    }

    /**
     * 为子类提供的从dto里获取数据的辅助方法
     */
    protected Object getVal(final String key) {
        return this.dto.get(key);
    }
    
    /**
     * 拆分复选框或同名多值的表单控件转换后的值, 根据逗号, 作为分隔符拆分
     * @param key
     * @return
     */
    protected String[] getValForArray(final String key) {
        String value = (String) this.dto.get(key);
        if (null != value && value.contains(",")) {
            return value.split(",");
        } else {
            return new String[]{value};
        }
    }

    /**
     * 非空判定, 如果数据不等于null也不等于空字符串也不等于字符串null则返回true
     * @param param
     * @return
     */
    protected boolean isNotNull(final Object param) {
        return param != null && !"".equals(param) && !"null".equals(param);
    }

    /**
     * 事务托管器,每次调用addPreparestatement方法都会把编译好后赋好值的PreparedStatement对象放入该list集合
     */
    private List<PreparedStatementEx> pstmtList = new ArrayList<>();

    /************************************************************************
     ************************多表事务处理********************************
     ***********************************************************************/

    /**
     * 非批处理事务处理
     */
    protected void addPreparedStatement(String sql, Object...args) throws Exception {
        PreparedStatement pstmt = DBUtils.prepareStatement(sql);
        int index = 1;
        for (Object arg :
                args) {
            pstmt.setObject(index++, arg);
        }
        pstmtList.add(new PreparedStatementEx(pstmt, false));
    }

    /**
     * 单问号占位符的批处理事务操作
     * @param sql
     * @param args
     * @throws Exception
     */
    protected void addPreparedStatementForBatch(String sql, Object...args) throws Exception {
        this.addPreparedStatementForBatch(sql, new Object[]{}, args);
    }

    /**
     * 两个占位符的批量操作的事务处理
     * 适用于给一批数据设置一个相同的值
     * @param sql SQL语句
     * @param state 第一个?占位符的值
     * @param args 第二个?占位符的值数组
     * @throws Exception
     */
    protected void addPreparedStatementForBatch(String sql, Object state, Object...args) throws Exception {
        /*PreparedStatement pstmt = DBUtils.prepareStatement(sql);
        pstmt.setObject(1, state);
        for (Object arg :
                args) {
            pstmt.setObject(2, arg);
            pstmt.addBatch();
        }
        pstmtList.add(new PreparedStatementEx(pstmt, true));*/
        this.addPreparedStatementForBatch(sql, new Object[]{state}, args);
    }

    /**多个占位符的批量操作的事务处理
     * 适用于给一批数据设置多个相同的值
     * @param sql SQL语句
     * @param states 固定数据值数组, n个占位符的值
     * @param args 第 n + 1个占位符的值
     * @throws Exception
     */
    protected void addPreparedStatementForBatch(String sql, Object[] states, Object...args) throws Exception {
        PreparedStatement pstmt = DBUtils.prepareStatement(sql);
        int index = 1;
        for (Object state :
                states) {
            pstmt.setObject(index++, state);
        }
        for (Object arg :
                args) {
            pstmt.setObject(index, arg);
            pstmt.addBatch();
        }
        pstmtList.add(new PreparedStatementEx(pstmt, true));
    }

    /**
     * 不规则数据批处理的事务操作
     * @param sql
     * @param args
     * @throws Exception
     */
    protected void addPreparedStatementForBatch(String sql, Object[][]args) throws Exception {
    	PreparedStatement pstmt = DBUtils.prepareStatement(sql);
    	for (int i = 0; i < args.length; i++) {
    		Object[] arg = args[i];
    		for (int j = 0; j < arg.length; j++) {
    			pstmt.setObject(j + 1, arg[j]);
    		}
    		pstmt.addBatch();
    	}
    	pstmtList.add(new PreparedStatementEx(pstmt, true));
    }
    
    /**
     * 执行所有事物
     * @return
     * @throws Exception
     */
    protected boolean execute() throws Exception{
        boolean flag =false;
        try {
        	//开启事务,关闭Connection对象的自动提交
            DBUtils.startTransaction();
            //遍历所有的PreparedStatement对象
            for (PreparedStatementEx p :
                    this.pstmtList) {
            	//执行语句
                p.executeTransaction();
            }
            //提交
            DBUtils.commit();
            flag = true;
        } catch (Exception ex) {
        	//出现异常回滚
            DBUtils.rollback();
            ex.printStackTrace();
        } finally {
        	//关闭事务,恢复Connection对象的自动提交
            DBUtils.stopTransaction();
            for (PreparedStatementEx p :
                    this.pstmtList) {
            	//关闭PreparedStatement对象
                p.closePreparedStatement();
            }
            //清除PreparedStatement对象集合中的所有PreparedStatement对象
            this.pstmtList.clear();
        }
        return flag;
    }

    /**
     * 单例查询方法的通用实现
     * @param sql SQL语句
     * @param args 值数组
     * @return
     *                  要求值数组的值与sql语句中的?占位符一一对应
     * @throws Exception
     */
    protected final Map<String, Object> singleQuery(final String sql, final Object...args) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = DBUtils.prepareStatement(sql);
            int index = 1;
            for (Object arg :
                    args) {
                preparedStatement.setObject(index++, arg);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            Map<String, Object> map = new HashMap<>((int) (count / 0.75) + 1);
            if (resultSet.next()) {
                for (int i = 1; i <= count; i++) {
                    map.put(metaData.getColumnLabel(i).toLowerCase(), resultSet.getObject(i));
                }
            }
            return map;
        } finally {
            DBUtils.Close(resultSet);
            DBUtils.Close(preparedStatement);
        }
    	//return this.queryRunner.query(DBUtils.getConnection(), sql, new MapHandler(), args);
    }

    /**
     * 多例查询方法的通用实现
     * @param sql SQL语句
     * @param args 值数组
     * @return
     *                  要求值数组的值与sql语句中的?占位符一一对应
     * @throws Exception
     */

    protected final List<Map<String, Object>> multipleQuery(final String sql, final Object...args) throws Exception {   
    PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = DBUtils.prepareStatement(sql);
            int index = 1;
            if (args != null) {
                for (Object arg :
                        args) {
                    preparedStatement.setObject(index++, arg);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            int initCapacity = (int) (count / 0.75) + 1;
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            while (resultSet.next()) {
                Map<String, Object> temp = new HashMap<String, Object>(initCapacity);
                for (int i = 1; i <= count; i++) {
                    temp.put(metaData.getColumnLabel(i).toLowerCase(), resultSet.getObject(i));
                }
                mapList.add(temp);
            }
            return mapList;
        } finally {
            DBUtils.Close(resultSet);
            DBUtils.Close(preparedStatement);
        }
    	//return this.queryRunner.query(DBUtils.getConnection(), sql, new MapListHandler(), args);
    }
    
    /**
     * 多例查询方法的通用实现
     * @param order 加入该值则表示查询结果按顺序加入Map集合
     * @param sql SQL语句
     * @param args 值数组
     * @return
     * @throws Exception
     */
    protected final List<Map<String, Object>> multipleQuery(boolean order,final String sql, final Object...args) throws Exception {   
        PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = DBUtils.prepareStatement(sql);
                int index = 1;
                if (args != null) {
                    for (Object arg :
                            args) {
                        preparedStatement.setObject(index++, arg);
                    }
                }
                resultSet = preparedStatement.executeQuery();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int count = metaData.getColumnCount();
                int initCapacity = (int) (count / 0.75) + 1;
                List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
                while (resultSet.next()) {
                    Map<String, Object> temp = new LinkedHashMap<String, Object>(initCapacity);
                    for (int i = 1; i <= count; i++) {
                        temp.put(metaData.getColumnLabel(i).toLowerCase(), resultSet.getObject(i));
                    }
                    mapList.add(temp);
                }
                return mapList;
            } finally {
                DBUtils.Close(resultSet);
                DBUtils.Close(preparedStatement);
            }
        	//return this.queryRunner.query(DBUtils.getConnection(), sql, new MapListHandler(), args);
        }

    /************************************************************************
     ************************单一表操作********************************
     ***********************************************************************/

    /**
     *单一表的批处理
     * @param sql SQL语句
     * @param args 值数组
     * @return
     *                  要求值数组的值与sql语句中的?占位符一一对应
     * @throws Exception
     */

    protected final boolean executeBatchManipulation (final String sql, final Object...args) throws Exception {
        return this.executeBatchManipulation(sql, new Object[]{}, args);
    }

    /**
     * 单一表的单个值批处理
     * 对某一列相同数据的批处理
     */
    protected final boolean executeBatchManipulation(final String sql, final Object state, final Object...args) throws Exception {
        return this.executeBatchManipulation(sql, new Object[]{state}, args);
    }

    /**
     * 单一表的多值批处理
     * @param sql
     * @param states
     * @param args
     * @return
     * @throws Exception
     * 对多列相同数据的批处理
     */
    protected final boolean executeBatchManipulation(final String sql, final Object[] states, final Object...args) throws Exception {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBUtils.prepareStatement(sql);
            int index = 1;
            for (Object state :
                    states) {
                preparedStatement.setObject(index++, state);
            }
            for (Object arg :
                    args) {
                preparedStatement.setObject(index, arg);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            return true;
            /*boolean flag = false;
            DBUtils.startTransaction();
            try {
                preparedStatement.executeBatch();
                DBUtils.commit();
                flag = true;
            } catch (Exception ex) {
                DBUtils.rollback();
                ex.printStackTrace();
            } finally {
                DBUtils.stopTransaction();
            }
            return flag;*/
        } finally {
            DBUtils.Close(preparedStatement);
        }

    }
    
    /**
     * 不规则数据的批处理,要求二维数组中的每一个数组的长度都相等,且每个数组的中的元素与sql语句中的?占位符一一对应
     * @param sql
     * @param args
     * @return
     * @throws Exception
     */
    protected final boolean executeBatchManipulation(final String sql, final Object[][] args) throws Exception {
    	//this.queryRunner.batch(DBUtils.getConnection(), sql, args);
    	PreparedStatement pstmt = null;
    	try {
    		pstmt = DBUtils.prepareStatement(sql);
    		for (Object[] arg : args) {
    			for (int i = 0; i < arg.length; i++) {
    				pstmt.setObject(i + 1, arg[i]);
    			}
    			pstmt.addBatch();
    		}
    		pstmt.executeBatch();
    		return true;
    	} finally {
    		DBUtils.Close(pstmt);
    	}
    }

    /**
     * 单例操作方法的通用实现
     * @param sql SQL语句
     * @param args 值数组
     * @return
     *                  要求值数组的值与sql语句中的?占位符一一对应
     * @throws Exception
     */

    protected final boolean executeManipulation (final String sql, final Object...args) throws Exception {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBUtils.prepareStatement(sql);
            int index = 1;
            for (Object arg :
                    args) {
                preparedStatement.setObject(index++, arg);
            }
            return preparedStatement.executeUpdate() > 0;
        } finally {
            DBUtils.Close(preparedStatement);
        }
    	//return this.queryRunner.update(DBUtils.getConnection(), sql, args) > 0;
    }
    
    /**
     * 分页的处理操作, 适应性不强
     * @param completedSql 编译好赋完值的统计记录条数的SQL语句,要求列别名必须为rowcount
     * @param inSql 内部条件查询部分的sql语句
     * @param args 值数组, 要求与SQL语句中的?占位符一一对应, 且最后两个值为rownum的边界值, 其第一个值为上限: <= 第一个值, 第二个值为下限: > 第二个值
     * @return Map<String,Object>
     * 		   key为rows的键值对其值为查询结果集合 
     * 		   key为nowPage的键值对其值为当前页码
     * 		   key为rowCount的键值对其值为总记录条数
     * 		   key为pageCount的键值对其值为可分页数
     * 		   key为numbers的键值对其值为每页的数据条数 
     * @throws Exception
     */
    protected final Map<String, Object> paging(final String completedSql, final String inSql, final Object...args) throws Exception{
    	//统计记录条数的sql语句
    	/*StringBuilder countSql = new StringBuilder()
    	.append("SELECT count(").append(pk).append(") rowcount").
    	append(" FROM ").append(tableName)
    	.append(" WHERE state = '1'");*/
    	
    	Map<String, Object> map = this.singleQuery(completedSql);
		//获取查询出的记录总条数
		int rowCount = Integer.parseInt(map.get("rowcount").toString());
		
		//定义默认情况下每页条数
		int numbers = 5;
		//获取页面输入的每页条数,如果不为空则将条数设为页面值
		Object inputNumbers = this.getVal("numbers");
		if (this.isNotNull(inputNumbers)) {
			int numbersInt = Integer.parseInt(inputNumbers.toString());
			if (numbersInt > 0) {
				numbers = numbersInt; 
			}
		}
		
		//计算可分页页数
		int pageCount = (int) Math.ceil(rowCount * 1.0 / numbers);
		//设定默认查询页码为1,如果未获取到页面传入的查询页码数,则默认查询第一页
		int nowPage = 1;
		//获取当前要查询的页码
		Object inputNowPage = this.getVal("nowPage");
		if (this.isNotNull(inputNowPage)) {
			int nowPageInt = Integer.parseInt(inputNowPage.toString());
			if (nowPageInt <= pageCount && nowPageInt > 0) {
				nowPage = nowPageInt; 
			}
		}
		
		//计算起始索引
		int beginIndex = (nowPage - 1) * numbers;

		//分页sql语句头部分
    	StringBuilder sql = new StringBuilder(inSql);
    	sql.append(" LIMIT ?,?");
    	//将值数组进行合并,将内部sql语句的值数组合并至值集合
    	List<Object> values = new ArrayList<Object>();
    	if (args != null && args.length > 0) {
    		for (int i = 0; i < args.length; i++) {
        		values.add(args[i]);
        	}
    	}
    	//将rownum边界值的?占位符的值添加至值集合
    	values.add(beginIndex);
    	values.add(numbers);
    	//获取查询结果
    	List<Map<String, Object>> result = this.multipleQuery(sql.toString(), values.toArray());
    	//将所有的数据添入Map集合返回
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rows", result);
		resultMap.put("nowPage", nowPage);
		resultMap.put("rowCount", rowCount);
		resultMap.put("pageCount", pageCount);
		resultMap.put("numbers", numbers);
		return resultMap;
    }
}
