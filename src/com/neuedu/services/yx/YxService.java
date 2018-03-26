package com.neuedu.services.yx;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class YxService extends JdbcSupport {

	/**
	 *  单例查询销售表的数据
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql = "select * from t_xs where xs_no=?";
		return this.singleQuery(sql, this.getVal("xs_no"));
	}

	/**
	 *  查询销售表所有未分配的数据
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql = "select xs_no,xs03,xs05,xs06,xs07,xs10 from t_xs  where xs11='未分配'";
		return this.multipleQuery(sql);
	}
/**
 * 分配销售计划  事物处理 添加分配信息到分配表
 */
	@Override
	public boolean update(String type) throws Exception {
//		Map<String, Object> map = QueryMap();
//		if (map.get("xs_no") != null && !map.get("xs_no").equals("")) {
			String sql1 = " insert into t_fp(fp_no,xs_no,us_no) values(seq.nextval,?,"
					+ " (select us_no from t_us where name=? and us03='客户经理') )";
			this.addPreparedStatement(sql1, this.getVal("xs_no"),
					this.getVal("select"));
			String sql2 = "update t_xs set xs11='已分配' where xs_no=?";
			this.addPreparedStatement(sql2, this.getVal("xs_no"));
			//*********************
			String sql3="insert into t_jh(jh01,xs_no) values(seq.nextval,?)";
			this.addPreparedStatement(sql3, this.getVal("xs_no")); 
			return this.execute();
//		} else {
//			String sql1 = "delete from t_fp where xs_no=?";
//			this.addPreparedStatement(sql1, getVal("xs_no"));
//			String sql2 = " insert into t_fp(fp_no,xs_no,us_no) values(seq.nextval,?,"
//					+ " (select us_no from t_us where name=? and us03='客户经理') )";
//			this.addPreparedStatement(sql2, this.getVal("xs_no"),
//					this.getVal("select"));
//			return this.execute();
//		}
	}

//	public Map<String, Object> QueryMap() throws Exception {
//		String sql = "select * from t_fp where xs_no=?";
//		return this.singleQuery(sql, this.getVal("xs_no"));
//	}
}
