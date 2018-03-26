package com.neuedu.services.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 处理商品名的自动补全请求
 * @author Administrator
 *
 */
public class AutoCompleteSp08Service extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		// TODO Auto-generated method stub
		return this.multipleQuery("SELECT sp08,sp_no FROM t_sp WHERE sp08 LIKE '%" + this.getVal("sp08") + "%'");
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
