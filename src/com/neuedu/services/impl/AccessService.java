package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
import com.neuedu.system.tools.Tools;
/**
 * 处理访问权限
 * @author Administrator
 *
 */
public class AccessService extends JdbcSupport {

	/**
	 * 根据二级菜单编号查询二级菜单信息
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("SELECT sm.mname, fm.mno, sm.url, a.ident")
		.append("  FROM menu fm, menu sm, t_access a")
		.append(" WHERE fm.mno = sm.mfno")
		.append("   AND sm.mno = a.m_no")
		.append("   AND sm.mno = ?");
		return this.singleQuery(sql.toString(), this.getVal("mno"));
	}

	/**
	 * 查询所有的二级菜单名 链接 可访问者以及其父菜单的名字
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		//统计记录条数
		StringBuilder sql1 = new StringBuilder()
		.append("   SELECT COUNT(sm.mno) rowcount")
		.append("     FROM menu fm, menu sm, t_access a")
		.append("    WHERE fm.mno = sm.mfno")
		.append("      AND sm.mno = a.m_no")
		.append("      AND sm.mstate = '1'");
		StringBuilder sql2 = new StringBuilder()
		.append("SELECT sm.mno, sm.mname sname,  fm.mname fname,sm.url, a.ident")
		.append(" FROM menu fm, menu sm, t_access a")
		.append(" WHERE fm.mno = sm.mfno")
		.append("   AND sm.mno = a.m_no")
		.append("   AND sm.mstate = '1'");
		//不定条件查询
		List<Object> values = new ArrayList<>();
		//获取一级菜单编号
		Object fname = this.getVal("qfno");
		if (this.isNotNull(fname)) {
			sql1.append(" AND fm.mno = ").append(fname);
			sql2.append(" AND fm.mno = ?");
			values.add(fname);
		}
		//获取二级菜单名
		Object sname = this.getVal("qsname");
		if (this.isNotNull(sname)) {
			sql1.append(" AND sm.mname like '%").append(sname).append("%'");
			sql2.append(" AND sm.mname like ?");
			values.add("%" + sname + "%");
		}
		sql2.append("ORDER BY sm.mno");
		//获取分页结果
		Map<String, Object> map = this.paging(sql1.toString(), sql2.toString(), values.toArray());
		//查询所有的一级菜单名以及编号作为下拉框的初始数据
		List<Map<String,Object>> list2 = this.multipleQuery("SELECT mname, mno FROM menu WHERE mstate = '1' AND mfno IS NULL");
		map.put("fs", list2);
		list.add(map);
		return list;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.addAccess();
		} else if ("modify".equalsIgnoreCase(type)) {
			return this.modifyAccess();
		} else if ("delete".equalsIgnoreCase(type)) {
			return this.deleteAccess();
		}
		return false;
	}

	/**
	 * 删除二级菜单
	 * @return
	 * @throws Exception
	 */
	private boolean deleteAccess() throws Exception{
		return this.executeManipulation("UPDATE menu SET mstate = '0' WHERE mno = ?", this.getVal("mno"));
	}

	/**
	 * 修改二级菜单
	 * @return
	 * @throws Exception
	 */
	private boolean modifyAccess() throws Exception{
		//获取二级菜单编号
		Object mno = this.getVal("mno");
		String sql1 = "UPDATE menu SET mname = ?, url = ?, mfno = ? WHERE mno = ?";
		Object[] values1 = {
				//二级菜单名, url, 一级菜单编号
				this.getVal("sname"), this.getVal("url"), this.getVal("fno"), mno
		};
		this.addPreparedStatement(sql1, values1);
		String sql2 = "UPDATE t_access SET ident = ? WHERE m_no = ?";
		//获取可访问用户身份
		String[] array = this.getValForArray("ident");
		StringBuilder ident = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				ident.append(array[i]);
				break;
			}
			ident.append(array[i]).append(",");
		}
		this.addPreparedStatement(sql2, ident.toString(), mno);
		return this.execute();
	}

	/**
	 * 添加二级菜单
	 * @return
	 * @throws Exception
	 */
	private boolean addAccess() throws Exception{
		//获取二级菜单编号
		Object mno = Tools.getSequenceId("mno");
		StringBuilder sql1 = new StringBuilder()
		.append("INSERT INTO menu(mno, mname, url, mfno) VALUES(?, ?, ?, ?)");
		Object[] values1 = {
				//二级菜单编号, 二级菜单名, 访问路径, 父菜单编号
				mno, this.getVal("sname"), this.getVal("url"), this.getVal("fno")
		};
		this.addPreparedStatement(sql1.toString(), values1);
		StringBuilder sql2 = new StringBuilder()
		.append("INSERT INTO t_access(m_no, ident) VALUES(?, ?)");
		String[] array = this.getValForArray("ident");
		StringBuilder ident = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				ident.append(array[i]);
				break;
			}
			ident.append(array[i]).append(",");
		}
		Object[] values2 = {
				mno, ident.toString()
		};
		this.addPreparedStatement(sql2.toString(), values2);
		return this.execute();
	}

}
