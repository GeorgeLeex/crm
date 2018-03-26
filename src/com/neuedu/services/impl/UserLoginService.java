package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 处理用户登录的操作
 * @author Administrator
 *
 */
public class UserLoginService extends JdbcSupport {
	/**
	 * 根据用户名与密码查询用户表中的记录 -实现登录功能模块
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql = "SELECT * FROM t_us WHERE us_name = ? AND us_pwd = ?";
		return this.singleQuery(sql, this.getVal("us_name"), this.getVal("us_pwd"));
	}
	/**
	 * 获取下拉菜单数据
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		//查询所有的客户经理
		String sql1 = "SELECT name, us_no FROM t_us WHERE us03 = '客户经理'";
		List<Map<String, Object>> managerNames = this.multipleQuery(sql1);
		//查询所有信用的名与编号
		String sql2 = "SELECT xy_name, xy_no FROM t_xy";
		List<Map<String, Object>> xys = this.multipleQuery(sql2);
		//查询所有的客户满意度的名与编号
		String sql3 = "SELECT px_name, px_no FROM t_px";
		List<Map<String, Object>> pxs = this.multipleQuery(sql3);
		//从数据字典中查询所有的客户等级的名与值
		String sql4 = "SELECT zd_name, zd_value FROM t_zd WHERE zd_class='企业客户等级'";
		List<Map<String, Object>> djs = this.multipleQuery(sql4);
		//从数据字典中查询所有的商品类别的名与值
		String sql5 = "SELECT zd_name, zd_value FROM t_zd WHERE zd_class = '商品类型'";
		List<Map<String, Object>> lbs = this.multipleQuery(sql5.toString());
		//根据用户身份查询菜单
		//查询每一个二级菜单的菜单名和访问路径以及父菜单的菜单名
		StringBuilder sql6 = new StringBuilder()
		.append("SELECT m1.mname fname, m2.mname sname, m2.url")
		.append("  from menu m1, menu m2, t_us u, t_access a")
		.append(" where m2.mfno = m1.mno")
		.append("   AND m2.mno = a.m_no")
		.append("   AND instr(a.ident, u.us03) > 0")
		.append("   AND m2.mstate = '1'")
		.append("   AND u.us_no = ?")
		.append("   ORDER BY m2.mno");
		List<Map<String,Object>> list = this.multipleQuery(sql6.toString(), this.getVal("usNo"));
		//最终结果集合,key为父菜单名, value为一个list集合,集合中的每一个map集合都是该父菜单的子二级菜单,
		//子菜单map集合的key为二级菜单的名, value为该二级菜单的url
		Map<String, List<Map<String, Object>>> map1 = new LinkedHashMap<>();
		List<Map<String, Object>> list2 = null;
		//遍历查询结果集
		for (Map<String, Object> map : list) {
			//取得父菜单名
			Object obj = map.get("fname");
			//取得该列中子菜单的名
			Object mName2 = map.get("sname");
			//取得该列中子菜单的访问路径
			Object url = map.get("url");
			Map<String, Object> temp = new HashMap<>();
			//将子菜单的名和访问路径以键值对的形式存入map集合
			temp.put(mName2.toString(), url);
			//如果最终集合中没有包含该父菜单
			if (!map1.containsKey(obj)) {
				//创建一个新的list集合
				list2 = new ArrayList<>();
				//将该父菜单的一个子菜单的键值对添入list集合
				list2.add(temp);
				//往最终集合中添加数据,以父菜单名为key, 包含二级菜单的list集合作为value
				map1.put(obj.toString(), list2);
			} else {
				//如果已经存在该父菜单名,则取出其存放着二级菜单的list集合
				list2 = map1.get(obj.toString());
				//把该父菜单的二级菜单添入list集合
				list2.add(temp);
			}
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("managers", managerNames);
		map.put("xys", xys);
		map.put("pxs", pxs);
		map.put("djs", djs);
		map.put("lbs", lbs);
		map.put("menus", map1);
		List<Map<String, Object>> all = new ArrayList<>();
		all.add(map);
		return all;
	}

	@Override
	public boolean update(String type) throws Exception {
		return false;
	}
}
