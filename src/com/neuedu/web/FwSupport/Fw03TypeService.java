package com.neuedu.web.FwSupport;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
import com.neuedu.web.support.ControllerSupport;

public class Fw03TypeService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql="select * from v_fw03select where  fwcl_no=?";
		return this.singleQuery(sql, this.getVal("fwcl_no"));
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		if (this.getVal("type").equals("type")) {
			String sql = "select * from v_fwclselect where cs_no=? and name=?";
			return this.multipleQuery(sql, this.getVal("no"),this.getVal("sysUserName"));
		}
		if (this.getVal("type").equals("state")) {
			String sql = null;
			if (this.getVal("no").equals("1")) {
				sql = "select * from v_fwclselect where fwcl06='未处理' and name=?";
			} else {
				sql = "select * from v_fwclselect where fwcl06='已处理' and name=?";
			}
			return this.multipleQuery(sql,this.getVal("sysUserName"));
		}
		if (this.getVal("type").equals("like")) {
			String sql = null;
			if (!this.getVal("name").equals("")) {
				System.out.println(this.getVal("name"));
				sql = "select * from v_fwclselect where kh02 like ?";
				return this.multipleQuery(sql, "%" + this.getVal("name") + "%");
			} else if (!this.getVal("fw03").equals("")) {
				System.out.println(this.getVal("fw03"));
				sql = "select * from v_fwclselect where fw03 like ?";
				return this.multipleQuery(sql, "%" + this.getVal("fw03") + "%");
			} else if (!this.getVal("begin").equals("")
					&& !this.getVal("end").equals("")) {
				sql = " select * from v_fwclselect where "
						+ " fw08 between to_date(?,'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')";
				return this.multipleQuery(sql, this.getVal("begin"),
						this.getVal("end"));
			}
		}
		return null;
	}

	@Override
	public boolean update(String type) throws Exception {
		if("update".equalsIgnoreCase(type)){
			return this.Fw03update();
		}
		return false;
	}

	private boolean Fw03update() throws Exception {
		String sql1="update t_fwcl set fwcl03=? , fwcl06='已处理',fwcl05=sysdate where fwcl_no=?";
		this.addPreparedStatement(sql1, this.getVal("fwcl03"),this.getVal("fwcl_no"));
		String sql2=" update t_fw set fw09='已处理'   where fw_no= "
				+ "(select fw.fw_no from t_fwcl fc,t_fwfp fp,t_fw fw "
				+ "  where fc.fwfp_no=fp.fwfp_no and fp.fw_no=fw.fw_no and  fc.fwcl_no=?)";
		this.addPreparedStatement(sql2, this.getVal("fwcl_no")); 
		return this.execute();
	}
}
