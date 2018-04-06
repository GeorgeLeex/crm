package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 处理数据字典
 * @author Administrator
 *
 */
public class DictionaryService extends JdbcSupport {

	/**
	 * 根据数据字典编号查询单条数据字典的信息
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return this.singleQuery("SELECT * FROM t_zd WHERE zd_no = ?", this.getVal("zd_no"));
	}

	/**
	 * 查询所有的数据字典信息
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		//编写统计记录条数的SQL语句
		StringBuilder sql1 = new StringBuilder()
		.append("SELECT COUNT(zd_no) rowcount FROM t_zd WHERE state = '1'");
		//查询数据字典所有信息的sql语句
		StringBuilder sql2 = new StringBuilder()
		.append("SELECT * FROM t_zd WHERE state = '1'");
		//不定条件查询的拼接
		//创建不定查询条件的值集合
		List<Object> values = new ArrayList<Object>();
		//获取类别
		Object zdClass = this.getVal("qzd_class");
		if (this.isNotNull(zdClass)) {
			sql1.append(" AND zd_class like '%").append(zdClass).append("%'");
			sql2.append(" AND zd_class like ? ");
			values.add("%" + zdClass + "%");
		}
		//获取条目名
		Object zdName = this.getVal("qzd_name");
		if (this.isNotNull(zdName)) {
			sql1.append(" AND zd_name like '%").append(zdName).append("%'");
			sql2.append(" AND zd_name like ?");
			values.add("%" + zdName + "%");
		}
		//获取条目值
		Object zdValue = this.getVal("qzd_value");
		if (this.isNotNull(zdValue)) {
			sql1.append(" AND zd_value = ").append(zdValue);
			sql2.append(" AND zd_value = ?");
			values.add(zdValue);
		}
		//根据商品类别进行排序
		sql2.append(" ORDER BY zd_class, zd_value");
		//创建最终返回的list集合
		List<Map<String, Object>> mapList = new ArrayList<>();
		//获取分页的map集合
		Map<String, Object> map = this.paging(sql1.toString(), sql2.toString(), values.toArray());
		mapList.add(map);
		return mapList;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.addDictionary();
		} else if ("modify".equalsIgnoreCase(type)) {
			return this.modifyDictionary();
		} else if ("delete".equalsIgnoreCase(type)) {
			return this.deleteDictionary();
		}
		return false;
	}

	/**
	 * 删除数据字典条目
	 */
	private boolean deleteDictionary() throws Exception{
		return this.executeManipulation("UPDATE t_zd SET state = '0' WHERE zd_no = ?", this.getVal("zd_no"));
	}

	/**
	 * 根据数据字典编号修改数据字典
	 * @return
	 * @throws Exception
	 */
	private boolean modifyDictionary() throws Exception{
		String sql = "UPDATE t_zd SET zd_name = ?, zd_value = ?, zd_class = ?, zd_bj = ? WHERE zd_no = ?";
		List<Object> values= new ArrayList<Object>();
		//获取添加条目名
		values.add(this.getVal("zd_name"));
		//获取添加条目值
		values.add(this.getVal("zd_value"));
		//获取添加条目类别
		values.add(this.getVal("zd_class"));
		//获取编辑状态
		Object zdBj = this.getVal("zd_bj");
		if (this.isNotNull(zdBj)) {
			values.add("1");
		} else {
			values.add("0");
		}
		//获取数据字典编号
		values.add(this.getVal("zd_no"));
		return this.executeManipulation(sql, values.toArray());
	}

	/**
	 * 添加数据字典条目
	 * @return
	 * @throws Exception
	 */
	private boolean addDictionary() throws Exception{
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO t_zd(d_class, zd_name, zd_value, zd_bj)")
		.append(" VALUES(?, ?, ?, ?)");
		List<Object> values = new ArrayList<>();
		//添加条目类别
		values.add(this.getVal("zd_class"));
		//添加条目名
		values.add(this.getVal("zd_name"));
		//添加条目值
		values.add(this.getVal("zd_value"));
		//获取是否可编辑状态
		Object zdBj = this.getVal("zd_bj");
		//如果可编辑状态为不为空,则表示勾选上了复选框,为可编辑状态
		if (this.isNotNull(zdBj)) {
			values.add("1");
		} else {
			values.add("0");
		}
		return this.executeManipulation(sql.toString(), values.toArray());
	}

}
