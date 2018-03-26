package com.neuedu.services.yx;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class MhService extends JdbcSupport {
	/**
	 * 单例查询销售计划数据
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql = " select x.*,j.jh06,j.jh07 from t_xs x,t_jh j "
				+ " where x.xs_no=j.xs_no and x.xs_no=?";
		return this.singleQuery(sql, this.getVal("xs_no"));
	}
/**
 * 模糊查询销售计划数据
 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql = " select * from t_xs where (xs03 like ?  or  xs07 =?) and xs11='未分配'";
		return this.multipleQuery(sql, "%" + getVal("xs03") + "%",
				getVal("xs07"));

	}

	@Override
	public boolean update(String type) throws Exception {
		if ("update".equalsIgnoreCase(type)) {
			return this.JHupdate(type);
		}
		return false;
	}
	/**
	 * 编辑销售数据
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean JHupdate(String type) throws Exception {
		String sql = "update t_jh set jh06=to_date(?,'yyyy-mm-dd'),jh07=?,fp_no=? where xs_no=?";
//		String sql = "insert into t_jh(jh01,xs_no,fp_no,jh06,jh07) values(seq.nextval,?,?,to_date(?,'yyyy-mm-dd'),?)";
		return this
				.executeManipulation(sql, this.getVal("jh06"),
						this.getVal("jh07"), this.getVal("fp_no"),
						this.getVal("xs_no"));
	}

}
