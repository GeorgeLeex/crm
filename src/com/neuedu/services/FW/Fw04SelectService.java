package com.neuedu.services.FW;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class Fw04SelectService extends JdbcSupport{

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql="select * from v_fw04select  where  fwcl_no=?";
		return this.singleQuery(sql, this.getVal("fwcl_no"));
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		if (this.getVal("type").equals("type")) {
			String sql = "select * from v_fwclselect where cs_no=? and fwcl06='已处理'";
			return this.multipleQuery(sql, this.getVal("no"));
		}
		if (this.getVal("type").equals("like")) {
			String sql = null;
			if (!this.getVal("name").equals("")) {
				sql = "select * from v_fwclselect where kh02 like ? and fwcl06='已处理'";
				return this.multipleQuery(sql, "%" + this.getVal("name") + "%");
			} else if (!this.getVal("fw03").equals("")) {
				System.out.println(this.getVal("fw03"));
				sql = "select * from v_fwclselect where fw03 like ? and fwcl06='已处理'";
				return this.multipleQuery(sql, "%" + this.getVal("fw03") + "%");
			} else if (!this.getVal("begin").equals("")
					&& !this.getVal("end").equals("")) {
				sql = " select * from v_fwclselect where "
						+ " fw08 between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')  "
						+ " and fwcl06='已处理'";
				return this.multipleQuery(sql, this.getVal("begin"),
						this.getVal("end"));
			}
		}
		return null;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("update".equalsIgnoreCase(type)) {
			return updateFw04();
		}
		return false;
	}

	private boolean updateFw04() throws Exception {
		if (this.getVal("px").equals("☆☆") || this.getVal("px").equals("☆")) {
			String sql1="delete from t_fwcl where fwcl_no= ?";
			this.addPreparedStatement(sql1, this.getVal("fwcl_no"));
			String sql2="delete from t_fwfp where fwfp_no=";
			this.addPreparedStatement(sql2, this.getVal("fwfp_no"));
			String sql3=" update t_fw set fw05='未分配',fw09='未处理' where fw_no";
			this.addPreparedStatement(sql3, this.getVal("fw_no"));
			return this.execute();
		}else {
		String sql1="update t_fwcl set px_no=(select px_no from t_px where px_name=?),"
				+ " fwcljg=?,fwcl06='已归档' where fwcl_no=?";
		this.addPreparedStatement(sql1, this.getVal("px"),this.getVal("fwcljg"),this.getVal("fwcl_no")); 
		String sql2="update t_fw set fw09='已归档' where fw_no=? ";
		this.addPreparedStatement(sql2, this.getVal("fw_no"));
		return this.execute();
		}
	}

}
