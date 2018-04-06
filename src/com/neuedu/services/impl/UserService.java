package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 用户管理
 */
import com.neuedu.services.support.JdbcSupport;
/**
 * 处理用户的操作
 * @author Administrator
 *
 */
public class UserService extends JdbcSupport {

	/**
	 * 根据用户编号查询用户信息
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return this.singleQuery("SELECT * FROM t_us WHERE us_no = ?", this.getVal("us_no"));
	}

	/**
	 * 分页查询所有用户
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		//编写统计记录条数的语句
		StringBuilder sql1 = new StringBuilder()
		.append("SELECT COUNT(us_no) rowcount FROM t_us WHERE state = '1' ");
		StringBuilder sql2 = new StringBuilder()
		.append("SELECT * FROM t_us WHERE state = '1' ");
		//不定条件查询
		List<Object> values = new ArrayList<>();
		//获取用户名
		Object name = this.getVal("qname");
		if (this.isNotNull(name)) {
			sql1.append(" AND name like '%").append(name).append("%'");
			sql2.append(" AND name like ? ");
			values.add("%" + name +"%");
		}
		//获取用户身份
		Object us03 = this.getVal("qus03");
		if (this.isNotNull(us03)) {
			sql1.append(" AND us03 = '").append(us03).append("'");
			sql2.append(" AND us03 = ?");
			values.add(us03);
		}
		Map<String, Object> map = this.paging(sql1.toString(), sql2.toString(), values.toArray());
		list.add(map);
		return list;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.addUser();
		} else if ("modify".equalsIgnoreCase(type)) {
			return this.modifyUser();
		} else if ("delete".equalsIgnoreCase(type)) {
			return this.deleteUser();
		};
		return false;
	}

	/**
	 * 根据客户编号删除客户
	 * @return
	 * @throws Exception
	 */
	private boolean deleteUser() throws Exception{
		return this.executeManipulation("UPDATE t_us SET state = '0' WHERE us_no = ?", this.getVal("us_no"));
	}

	/**
	 * 修改用户信息
	 * @return
	 * @throws Exception
	 */
	private boolean modifyUser() throws Exception{
		String sql = "UPDATE t_us SET name = ?, us03 = ?, us_name = ?, us_pwd = ? WHERE us_no = ?";
		Object[] values = {
				//姓名, 身份, 登录名, 登录密码, 用户编号
				this.getVal("name"), this.getVal("us03"), this.getVal("us_name"),
				this.getVal("us_pwd"), this.getVal("us_no")
		};
		return this.executeManipulation(sql, values);
	}

	/**
	 * 添加用户
	 * @return
	 * @throws Exception
	 */
	private boolean addUser() throws Exception{
		String sql = "INSERT INTO t_us(name, us03, us_name, us_pwd) VALUES(?,?,?,?)";
		Object[] values = {
				//姓名, 身份, 登录名, 登录密码
				this.getVal("name"), this.getVal("us03"), this.getVal("us_name"), this.getVal("us_pwd")
		};
		return this.executeManipulation(sql, values);
	}

}
