package com.neuedu.services.bb;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class BbSelectService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql="select * from v_lssum";
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		return false;
	}

}
