package com.neuedu.web.servlet.impl.dgp;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class BbYearService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql="select distinct extract(year from ls04) year from t_ls ";
			return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
