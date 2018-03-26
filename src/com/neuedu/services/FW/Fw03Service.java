package com.neuedu.services.FW;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class Fw03Service extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
			String sql="select * from v_fwclselect where fpr_no=? and fwcl06='未处理'";
		return this.multipleQuery(sql,this.getVal("sysUserId")); 
	}

	@Override
	public boolean update(String type) throws Exception {
		return false;
	}

}
