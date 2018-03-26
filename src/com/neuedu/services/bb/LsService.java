package com.neuedu.services.bb;

import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.neuedu.services.support.JdbcSupport;

public class LsService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		if (this.getVal("khname")==null && this.getVal("usname")==null) {
			String sql="select * from v_fw02";
			return this.multipleQuery(sql);
		}
		System.out.println();
		System.out.println("  us  "+this.getVal("usname"));
		System.out.println("  kh  "+this.getVal("khname"));
		if(this.getVal("khname")!=null){
			String sql="select * from v_fw02 where khname like ?";
			return this.multipleQuery(sql,"%"+this.getVal("khname")+"%");
		}
		if (this.getVal("usname")!=null) {
			String sql="select * from v_fw02 where usname=?";
			return this.multipleQuery(sql,"%"+this.getVal("usname")+"%");
		}
		return null;
	}

	@Override
	public boolean update(String type) throws Exception {
		return false;
	}

}
