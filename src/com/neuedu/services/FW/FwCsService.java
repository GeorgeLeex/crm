package com.neuedu.services.FW;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class FwCsService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql="select cs_no,cs_name from t_class";
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		return false;
	}

}
