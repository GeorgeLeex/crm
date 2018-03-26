package com.neuedu.services.yx;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class UpdateSelectService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql="select * from t_xs where xs_no=?";
		return this.singleQuery(sql,  this.getVal("xs_no"));
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql=" select name from t_us where us03='客户经理'";
		System.out.println("      mult   *"+multipleQuery(sql));
		return multipleQuery(sql);
	}

	@Override
	public boolean update(String type) throws Exception {
			String sql="update t_xs set xs03=?,xs02=?,xs04=?,xs05=?,xs06=?,xs07=?,xs08=?  where xs_no=?";
			boolean flag=this.executeManipulation(sql,this.getVal("xs03"),this.getVal("xs02"),
					this.getVal("xs04"),this.getVal("xs05"),this.getVal("xs06"),this.getVal("xs07")
					,this.getVal("xs08"),this.getVal("xs_no"));
			return flag;
	}
}
