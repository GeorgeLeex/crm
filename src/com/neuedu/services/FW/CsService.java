package com.neuedu.services.FW;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class CsService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql="select * from v_fwselect where cs_no=? and fw05='新创建'";
		return this.multipleQuery(sql, this.getVal("cs_no"));
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
