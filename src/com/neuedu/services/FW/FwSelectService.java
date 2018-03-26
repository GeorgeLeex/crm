package com.neuedu.services.FW;

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
		String sql="select * from v_fwselect  where fw05='新创建' ";
		return this.multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			 return this.addFp();
		}
		return false;
	}
 
	public boolean addFp() throws Exception {
		System.out.println(",fw :"+this.getVal("fw_no")+"hh   :"+ this.getVal("kh02")+"us  :"+ this.getVal("us_no"));
		String sql1="insert into t_fwfp(fwfp_no,fw_no,kh_no,us_no) values(seq.nextval,?,"
				+ " (select kh_no from t_kh where kh02=?),?)";
		this.addPreparedStatement(sql1, this.getVal("fw_no"), this.getVal("kh02"), this.getVal("us_no"));
		String sql2="update t_fw set fw05='已分配' where fw_no=?";
		this.addPreparedStatement(sql2,this.getVal("fw_no"));
		if (this.execute()) {
			String sql3=" insert into t_fwcl(fwcl_no,fwfp_no,us_no) "
					+ " values(seq.nextval,(select fwfp_no from t_fwfp "
					+ "  where fw_no=? and us_no=? and state='1'),?)";
			
			return this.executeManipulation(sql3, this.getVal("fw_no"),this.getVal("us_no"),this.getVal("us_no"));
		}
		return false;
	}

}
