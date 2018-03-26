package com.neuedu.services.FW;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class FwLikeService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	// khname fw03 begin end
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		if (this.getVal("khname") != null && !this.getVal("khname").equals("")) {
			String sql = "select * from v_fwselect where kh02 like ? and fw05='新创建'";
			return this.multipleQuery(sql, "%" + this.getVal("khname") + "%");
		} else if (this.getVal("fw03") != null
				&& !this.getVal("fw03").equals("")) {
			String sql = "select * from v_fwselect where fw03 like ?  and fw05='新创建'";
			return this.multipleQuery(sql, "%" + this.getVal("fw03") + "%");
		} else if (this.getVal("begin") != null && this.getVal("end") != null) {
			String sql = "select * from v_fwselect"
					+ " where   fw08 between to_date(?,'yyyy-mm-dd') "
					+ " and to_date(?,'yyyy-mm-dd')  and fw05='新创建'";
			return this.multipleQuery(sql, this.getVal("begin"),
					this.getVal("end"));
		}
		String sql = "select * from v_fwselect";
		
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		if("delete".equalsIgnoreCase(type)){
			return deleteOne();
		}
		return false;
	}

	public boolean deleteOne() throws Exception {
		String sql="delete from t_fw where fw_no=?";
		System.out.println(this.getVal("fw_no"));
		return this.executeManipulation(sql, this.getVal("fw_no"));
	}

}
