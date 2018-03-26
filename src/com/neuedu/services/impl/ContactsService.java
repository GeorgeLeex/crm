package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 处理客户联系人相关操作
 * @author Administrator
 * @time 2018年3月10日 上午11:37:27
 */
public class ContactsService extends JdbcSupport {

	/**
	 * 根据联系人编号查询该联系人信息
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql = "SELECT * FROM t_lxr WHERE lxr_no = ?";
		return this.singleQuery(sql, this.getVal("lxr_no"));
	}

	/**
	 * 根据客户编号查询该客户的所有联系人信息
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		//根据客户编号查询联系人表中该客户的所有状态为1的联系人
		String sql = "SELECT * FROM v_lxr WHERE state = 1 AND kh_no = ?";
		List<Map<String,Object>> list = this.multipleQuery(sql, this.getVal("kh_no"));
		//如果是新建的客户,没有联系人,就会无法查询到数据,但页面需要客户信息,所以查询客户信息
		if (list == null || list.size() == 0) {
			list = new ArrayList<>();
			String sql2 = "SELECT kh_no, kh02 FROM t_kh WHERE kh_no = ?";
			Map<String, Object> map = this.singleQuery(sql2, this.getVal("kh_no"));
			list.add(map);
		}
		return list;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.addContact();
		} else if ("modify".equalsIgnoreCase(type)) {
			return this.modifyContact();
		} else if ("delete".equalsIgnoreCase(type)) {
			return this.deleteContact();
		}
		return false;
	}
	
	/**
	 * 添加客户联系人
	 * @return
	 * @throws Exception
	 */
	private boolean addContact() throws Exception {
		StringBuilder sql = new StringBuilder()
		 //                          联系人编号     客户编号   姓名          性别   
		 .append("  INSERT INTO t_lxr(lxr_no, kh_no, lxr03, lxr04,")
		 //         职位        办公电话   手机           备注
		 .append("  lxr05, lxr06, lxr07, lxr08)")
		 .append("  VALUES(seq_lxr_no.nextval, ?, ?, ?, ?, ?, ?, ?)");
		Object[] args = {
				this.getVal("kh_no"), this.getVal("lxr03"), this.getVal("lxr04"),
				this.getVal("lxr05"), this.getVal("lxr06"), this.getVal("lxr07"), this.getVal("lxr08"),
		};
		return this.executeManipulation(sql.toString(), args);
	}
	
	/**
	 * 修改客户联系人信息
	 * @return
	 * @throws Exception
	 */
	private boolean modifyContact() throws Exception {
		StringBuilder sql = new StringBuilder()
		.append(" UPDATE t_lxr SET")
		.append(" lxr03 = ?,lxr04 = ?,lxr05 = ?,lxr06 = ?,lxr07 = ?,lxr08 = ?")
		.append(" WHERE lxr_no = ?");
		Object[] args = {
				this.getVal("lxr03"), this.getVal("lxr04"), this.getVal("lxr05"), 
				this.getVal("lxr06"), this.getVal("lxr07"), this.getVal("lxr08"), this.getVal("lxr_no")
		};
		return this.executeManipulation(sql.toString(), args);
	}
	
	/**
	 * 根据联系人编号删除联系人
	 * @return
	 * @throws Exception
	 */
	private boolean deleteContact() throws Exception {
		String sql = "UPDATE t_lxr SET state = '0' WHERE lxr_no = ?";
		return this.executeManipulation(sql, this.getVal("lxr_no"));
	}
}
