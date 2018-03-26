package com.neuedu.services.bb;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class XyService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql = "select x.xy_no no,x.xy_name name,count(k.xy_no) ct from t_kh k,t_xy x "
				+ "where k.xy_no(+)=x.xy_no group by x.xy_no,x.xy_name "
				+ " order by x.xy_no desc";
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
