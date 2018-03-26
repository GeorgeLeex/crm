package com.neuedu.services.FW;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class Fw05Service extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql="select * from v_fw04select where fw_no=?";
		return this.singleQuery(sql, this.getVal("fw_no")); 
	}
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		if (this.getVal("type").equals("type")) {
			String sql = "select * from v_fw05select where fw09='已归档' and cs_no=?";
			return this.multipleQuery(sql, this.getVal("no"));
		}
		if (this.getVal("type").equals("like")) {
			String sql = null;
			if (!this.getVal("name").equals("")) {
				System.out.println(this.getVal("name"));
				sql = "select * from v_fw05select where fw09='已归档' and  kh02 like ?";
				return this.multipleQuery(sql, "%" + this.getVal("name") + "%");
			} else if (!this.getVal("fw03").equals("")) {
				System.out.println(this.getVal("fw03"));
				sql = "select * from v_fw05select where fw09='已归档' and fw03 like ?";
				return this.multipleQuery(sql, "%" + this.getVal("fw03") + "%");
			} else if (!this.getVal("begin").equals("")
					&& !this.getVal("end").equals("")) {
				sql = " select * from v_fw05select and fw09='已归档'"
						+ "  and fw08 between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')";
				return this.multipleQuery(sql, this.getVal("begin"),
						this.getVal("end"));
			}
		}
		return null;
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
