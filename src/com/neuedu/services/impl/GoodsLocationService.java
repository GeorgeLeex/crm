package com.neuedu.services.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 模糊查询货位名
 * @author Administrator
 *
 */
public class GoodsLocationService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 模糊查询产品货位
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql = "SELECT hw_no, hw02 FROM t_hw WHERE hw02 LIKE ?";
		Object hw02 = "%" + this.getVal("term") + "%";
		return this.multipleQuery(sql, hw02);
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
