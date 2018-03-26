package com.neuedu.services.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 处理客户姓名自动补全请求
 * @author Administrator
 *
 */
public class AutoCompleteKh02Service extends JdbcSupport{

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 模糊查询客户名以及编号
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql  = "SELECT kh02,kh_no FROM t_kh WHERE kh02 LIKE '%" + this.getVal("kh02") + "%'";
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean queryKh02() throws Exception {
		System.out.println(this.getVal("id"));
		return false;
	}
}
