package com.neuedu.services.FW;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class StateService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		if (this.getVal("fw05").equals("1")) {
			String sql="select * from v_fwselect where fw05='新创建'";
			return this.multipleQuery(sql);
		}else if(this.getVal("fw05").equals("2")){
			String sql="select * from v_fwselect where fw05='已分配'";
			return this.multipleQuery(sql);
		}
		return null;
	}

	@Override
	public boolean update(String type) throws Exception {
		return false;
	}

}
