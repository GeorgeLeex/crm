package com.neuedu.services.bb;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class PxService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql = " select p.px_no no,p.px_name name,count(k.px_no) ct from t_kh k,t_px p "
				+ " where k.px_no(+)=p.px_no group by p.px_no,p.px_name  "
				+ " order by p.px_no desc";
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
