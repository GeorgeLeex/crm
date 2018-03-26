package com.neuedu.services.bb;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class NameSelectService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql=" select k.kh_no,k.kh02,sum(money) money  from "
				+ "  (   select l.*,d.money,extract(year from l.ls04) year from t_ls l,"
				+ "  ( select ls_no,sum(dd07) money from t_dd   group by ls_no ) d  "
				+ " where l.ls_no=d.ls_no  ) b,t_kh k"
				+ " where k.kh_no=b.kh_no  and kh02 like ?"
				+ " group by k.kh_no,k.kh02"
				+ " order by money desc";
		return this.multipleQuery(sql, "%"+this.getVal("name")+"%");
	}

	@Override
	public boolean update(String type) throws Exception {
		return false;
	}

}
