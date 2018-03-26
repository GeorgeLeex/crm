package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 处理客户流失操作
 * @author Administrator
 *
 */
public class CustomerLossService extends JdbcSupport {

	/**
	 * 根据流失记录编号查询流失纪录表中单条流失纪录的信息
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		//编写sql语句
		StringBuilder sql = new StringBuilder()
		.append("SELECT kl.khls_no, k.kh_no,  k.kh02, u.name, to_char(kl.khls09, 'yyyy-mm-dd hh24:mi:ss') khls09, kl.khls05")
		.append("  FROM t_khls kl, t_kh k, t_us u")
		.append(" WHERE kl.kh_no = k.kh_no")
		.append("   AND k.us_no = u.us_no")
		.append("   AND kl.khls_no = ?");
		//获取参数,返回查询结果
		Object khlsNo = this.getVal("khls_no");
		return this.singleQuery(sql.toString(), khlsNo);
	}

	/**
	 * 查询流失记录表中的所有记录
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		//编写统计流失纪录条数的SQL语句
		StringBuilder sql1 = new StringBuilder()
		.append("SELECT COUNT(kl.khls_no) rowcount ")
		.append("    FROM t_khls kl, t_kh k, t_us u ")
		.append("  WHERE  kl.kh_no = k.kh_no")
		.append("    AND  k.us_no = u.us_no");
		//编写查询流失纪录信息的SQL语句
		StringBuilder sql2 = new StringBuilder()
		.append("SELECT kl.khls_no, k.kh02, u.name, to_char(kl.khls09, 'yyyy-mm-dd hh24:mi:ss') khls09, ")
		.append("       to_char(kl.khls04, 'yyyy-mm-dd hh24:mi:ss') khls04, ")
		.append("       decode(kl.khls06,'0','暂缓流失','1','确认流失','') khls06")
		.append("  FROM t_khls kl, t_kh k, t_us u")
		.append(" WHERE kl.kh_no = k.kh_no")
		.append("   AND k.us_no = u.us_no");
		//不定条件查询的sql语句拼接与值集合的添加
		//不定条件查询的值集合
		List<Object> values = new ArrayList<Object>();
		//获取不定查询值
		Object kh02 = this.getVal("qkh02");
		//如果获取到的查询客户名称的值不为空,则进行sql语句拼接
		if (this.isNotNull(kh02)) {
			sql2.append("    AND  k.kh02 like ? ");
			values.add("%" + kh02 + "%");
			//统计记录条数的语句也进行条件拼接
			sql1.append(" AND k.kh02 like '%").append(kh02).append("%' ");
		}
		//如果获取到的查询客户经理的值不为空,则进行sql语句拼接
		Object name = this.getVal("qusno");
		if (this.isNotNull(name)) {
			sql2.append("   AND  u.us_no = ? ");
			values.add(name);
			sql1.append(" AND u.us_no = ").append(name);
		}
		//如果获取到的查询流失状态的值不为空,则进行sql语句拼接
		Object khls06 = this.getVal("qkhls06");
		if (this.isNotNull(khls06)) {
			sql2.append("    AND  kl.khls06 = ? ");
			values.add(khls06);
			sql1.append(" AND kl.khls06 = '").append(khls06).append("'");
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		mapList.add(this.paging(sql1.toString(), sql2.toString(), values.toArray()));
		return mapList;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equals(type)) {
			return this.addReprieve();
		} else if ("sure".equalsIgnoreCase(type)) {
			return this.SureCustomerLoss();
		}
		return false;
	}

	/**
	 * 确认客户流失
	 * @return
	 * @throws Exception
	 */
	private boolean SureCustomerLoss() throws Exception{
		//编写添加客户流失原因和修改客户流失状态的SQL语句
		String sql1 = "UPDATE t_khls SET khls04 = sysdate, khls06 = '1', khls07 = ? WHERE khls_no = ?";
		Object[] args1 = {
			//获取客户流失原因
			this.getVal("khls07"),
			//获取流失纪录编号
			this.getVal("khls_no")
		};
		//加入事务托管器
		this.addPreparedStatement(sql1, args1);
		//编写修改客户状态的sql语句,根据客户编号修改客户状态为3:已流失
		String sql2 = "UPDATE t_kh SET state = '2' WHERE kh_no = ?";
		//获取客户编号
		Object args2 = this.getVal("kh_no");
		//加入事务托管器
		this.addPreparedStatement(sql2, args2);
		//执行所有事务
		return this.execute();
	}

	/**
	 * 根据客户流失编号对其追加暂缓措施
	 * @return
	 * @throws Exception
	 */
	private boolean addReprieve() throws Exception{
		String sql = "UPDATE t_khls SET khls05 = (SELECT khls05 FROM t_khls WHERE khls_no = ?) || ? ||';' WHERE khls_no = ?";
		//获取客户流失编号
		Object khlsNo = this.getVal("khls_no");
		//获取追加的措施
		Object khls05 = this.getVal("khls05");
		return this.executeManipulation(sql, khlsNo, khls05, khlsNo);
	}

}
