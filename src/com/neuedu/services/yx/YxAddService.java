package com.neuedu.services.yx;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;

public class YxAddService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		return null;

	}

	/**
	 *  客户开发查询全部数据
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
//		String sql = "select xs_no,xs02,xs03,xs04,xs05,xs06,xs07,xs08,us_no,xs10,xs11,state from t_xs"
//				+ " where xs_no in(select xs_no from t_fp where us_no=?)";
		String sql=" select * from v_zzkf where  jh08 not in ('开发失败') and fpr=?";
		return this.multipleQuery(sql, this.getVal("sysUserId"));
	}
	/**
	 * 根据实际参数来判断调用哪个方法 返回的都是数据更新的结果
	 */
	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.add();
		}
		if ("delete".equals(type)) {
			return this.delete();
		}
		return false;
	}
/**
 * 添加客户销售计划
 */
	public boolean add() throws Exception {
		String sql = "insert into  t_xs(xs_no,xs02,xs03,xs04,xs05,xs06,xs07,xs08,us_no)  "
				+ " values(seq.nextval,?,?,?,?,"
				+ " ?,?,?,(select us_no from t_us where name=?))";
		int xs04 = Integer.parseInt((String) this.getVal("xs04"));
		boolean flag = this.executeManipulation(sql, this.getVal("xs02"),
				this.getVal("xs03"), xs04, this.getVal("xs05"),
				this.getVal("xs06"), this.getVal("xs07"), this.getVal("xs08"),
				this.getVal("us_no"));
		return flag;
	}
	/**
	 * ajax数据删除
	 * @return
	 * @throws Exception
	 */
	public boolean delete() throws Exception {
		String sql = "delete from t_xs where xs_no=?";
		return this.executeManipulation(sql, this.getVal("xs_no"));
	}
}
