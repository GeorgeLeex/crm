package com.neuedu.services.FW;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class TypeService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql="select  kh_no, kh02 from t_kh";
		
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.fwAdd();
		}
		return false;
	}

	private boolean fwAdd() throws Exception {
		String sql="insert into t_fw(fw_no,cs_no,fw03,kh_no,fw06,us_no) values(seq.nextval,?,?,"
				+ " ?,?,(select us_no from t_us where name=? and us03=?))";
		return this.executeManipulation(sql,this.getVal("cs_no"),this.getVal("fw03")
				,this.getVal("kh_no"),this.getVal("fw06"),this.getVal("name"),this.getVal("us03")); 
	}

}
