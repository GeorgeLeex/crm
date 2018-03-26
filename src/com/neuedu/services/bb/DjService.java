package com.neuedu.services.bb;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class DjService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql = "select d.dj_no no,d.dj_name name,count(k.dj_no) ct from t_kh k,t_dj d  "
				+ "  where k.dj_no(+)=d.dj_no group by d.dj_no,d.dj_name"
				+ "  order by d.dj_no asc  ";
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
