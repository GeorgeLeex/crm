package com.neuedu.services.bb;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class FwSelectService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql = "select c.cs_no no,c.cs_name name,count(f.cs_no) ct from t_fw f,t_class c "
				+ "where f.cs_no(+)=c.cs_no group by c.cs_no,c.cs_name  order by c.cs_no asc";
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
