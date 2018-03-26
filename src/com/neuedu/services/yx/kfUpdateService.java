package com.neuedu.services.yx;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class kfUpdateService extends JdbcSupport {
	/**
	 *  编辑开发计划页面的
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql = " select v.*,j.jh06,j.jh07,j.jh09,u.name cjr  from v_zdjh v,t_jh j,t_us u"
				+ " where v.xs_no=j.xs_no(+) and v.us_no=u.us_no and v.xs_no=?";
		return this.singleQuery(sql, this.getVal("xs_no"));
	}

	/**
	 *查询开发计划
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		String sql = "select * from t_jh where xs_no=?";
		return this.multipleQuery(sql, this.getVal("xs_no"));
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("update".equalsIgnoreCase(type)) {
			return this.ZXJGupdate();
		}else if("success".equalsIgnoreCase(type)){
			return this.success();
		}else if("fail".equalsIgnoreCase(type)){
			
		}
		return false;
	}

	/**
	 * 执行开发计划结果
	 * @return
	 * @throws Exception
	 */
	public boolean ZXJGupdate() throws Exception {
		String sql = "update t_jh set  jh09=?  where jh01=? and jh07=?";
		return this.executeManipulation(sql, this.getVal("jh09"),
				getVal("jh01"), getVal("jh07"));
	}
	/**
	 * 开发成功
	 * @return
	 * @throws Exception
	 */
	public boolean success() throws Exception {
		String sql = "update t_jh set  jh08='开发成功'  where xs_no=?";
		return this.executeManipulation(sql, this.getVal("xs_no")); 
	}
	/**
	 * 开发失败
	 * @return
	 * @throws Exception
	 */
	public boolean fail() throws Exception {
		String sql = "update t_jh set  jh08='开发失败'  where xs_no=?";
		return this.executeManipulation(sql, this.getVal("xs_no"));
	}

}
