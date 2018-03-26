package com.neuedu.services.yx;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class KhAddService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}
//客户添加完跳转到客户开发计划页面的dao
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
//		String sql = "  select * from v_zdjh where"
//				+ " fpr in (select fpr from v_zdjh where xs_no=?)";
	String sql=" select * from v_zzkf where fpr=(select fpr from v_zzkf where xs_no=?) and jh08 not in('开发失败') ";
		return this.multipleQuery(sql, this.getVal("xs_no"));
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.KhAdd();
		} else if ("fail".equalsIgnoreCase(type)) {
			return this.Jhupdate();
		}
		return false;
	}

	private boolean Jhupdate() throws Exception {
		String sql = "update t_jh set jh08='开发失败' where xs_no=?  ";
		return this.executeManipulation(sql, this.getVal("xs_no"));
	}

	public boolean KhAdd() throws Exception {
		String sql = "insert  into t_kh(kh_no,kh02,kh03,us_no,dj_no,px_no,xy_no,"
				+ " kh08,kh09,kh10,kh11,kh12,kh13,kh14,"
				+ " kh15,kh16,kh17,kh18,kh19,kh20) "
				+ " values(seq.nextval,?,?,?,"
				+ " ?,?,"
				+ " ?,?,?,?,?,?,?,?,"
				+ " ?,?,?,?,?,?)";
		boolean flag = this.executeManipulation(sql, this.getVal("kh02"),
				this.getVal("kh03"), this.usno(), this.getVal("dj_no"),
				this.getVal("px_no"), this.getVal("xy_no"),
				this.getVal("kh08"), this.getVal("kh09"), this.getVal("kh10"),
				this.getVal("kh11"), this.getVal("kh12"), this.getVal("kh13"),
				this.getVal("kh14"), this.getVal("kh15"), this.getVal("kh16"),
				this.getVal("kh17"), this.getVal("kh18"), this.getVal("kh19"),
				this.getVal("kh20"));
		return flag;
	}

	public Object usno() throws Exception {
		String sql = "select * from t_us where  name=?";
		Map<String, Object> map = this.singleQuery(sql, this.getVal("us_no"));
		Object no = map.get("us_no");
		return no;
	}

}
