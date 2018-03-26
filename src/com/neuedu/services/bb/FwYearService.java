package com.neuedu.services.bb;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class FwYearService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
     String sql = "  select c.cs_no no,c.cs_name name,count(f.cs_no) ct,extract(year from f.fw08) year "
				+ "  from t_fw f,t_class c"
				+ "  where f.cs_no(+)=c.cs_no  and  extract(year from f.fw08)=? "
				+ "  group by c.cs_no,c.cs_name,extract(year from f.fw08) "
				+ "  order by c.cs_no asc ";
		return this.multipleQuery(sql, this.getVal("year"));
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
