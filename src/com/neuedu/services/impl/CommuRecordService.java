package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 处理交往记录
 * @author Administrator
 * @time 2018年3月10日 下午3:21:42
 */
public class CommuRecordService extends JdbcSupport {

	/**
	 * 根据交往记录编号查询交往记录
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("SELECT jw_no, kh_no, kh02, jw03, jw04, jw05, jw06,")
		.append("jw07, state FROM v_jw WHERE jw_no = ?");
		return this.singleQuery(sql.toString(), this.getVal("jw_no"));
	}
	
	/**
	 * 根据客户编号来分页查询所有的交往记录
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		//最终返回的list集合
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		//获取客户编号
		Object khNo = this.getVal("kh_no");
		//编写统计条数的语句
		StringBuilder completedSql =new StringBuilder()
		.append("SELECT count(jw_no) rowcount FROM t_jw WHERE state = '1' AND kh_no = ").append(khNo);
		//编写内部查询语句
		StringBuilder sql = new StringBuilder()
		.append("SELECT jw_no, kh_no, kh02, jw03, jw04, jw05, jw06,")
		.append("jw07, state FROM v_jw WHERE state = '1' AND kh_no = ?");
		//传递参数进行分页数据查询
		Map<String, Object> resultMap = this.paging(completedSql.toString(), sql.toString(), this.getVal("kh_no"));
		//判断查询出的记录是否为空,如果为空就查询客户信息
		List<Map<String, Object>> rows = (List<Map<String, Object>>) resultMap.get("rows");
		if (rows == null || rows.size() == 0) {
			StringBuilder sql2 = new StringBuilder()
			.append("SELECT kh_no, kh02 FROM t_kh WHERE kh_no = ").append(khNo);
			resultMap = this.singleQuery(sql2.toString());
			mapList.add(resultMap);
			mapList.add(null);
		} else {
			mapList.add(resultMap);
		}
		return mapList;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.addCommuRecord();
		} else if ("modify".equalsIgnoreCase(type)) {
			return this.modifyCommuRecord();
		} else if ("delete".equalsIgnoreCase(type)) {
			return this.deleteCommuRecord();
		} else {
			throw new RuntimeException(this.getClass().getName() + "中无此方法...");
		}
	}

	/**
	 * 根据交往记录编号删除单个交往记录
	 * @return
	 * @throws Exception 
	 */
	private boolean deleteCommuRecord() throws Exception {
		String sql = "UPDATE t_jw SET state = '0' WHERE jw_no = ?";
		return this.executeManipulation(sql, this.getVal("jw_no"));
	}

	/**
	 * 修改交往记录
	 * @return
	 * @throws Exception 
	 */
	private boolean modifyCommuRecord() throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE t_jw SET jw03 = ?, jw04 = ?, ")
		.append(" jw05 = ?, jw06 = ?, jw07 = ? WHERE jw_no = ?");
		Object[] args = {
				this.getVal("jw03"), this.getVal("jw04"), this.getVal("jw05"), 
				this.getVal("jw06"), this.getVal("jw07"), this.getVal("jw_no")
		};
		return this.executeManipulation(sql.toString(), args);
	}

	/**
	 * 添加交往记录
	 * @return
	 * @throws Exception 
	 */
	private boolean addCommuRecord() throws Exception {
		StringBuilder sql = new StringBuilder()
		.append(" INSERT INTO t_jw (kh_no, jw03, jw04, jw05, jw06, jw07)")
		.append(" VALUES(?, ?, ?, ?, ?, ?)");
		Object[] args = {
				this.getVal("kh_no"), this.getVal("jw03"), this.getVal("jw04"), 
				this.getVal("jw05"), this.getVal("jw06"), this.getVal("jw07")
		};
		return this.executeManipulation(sql.toString(), args);
	}

}
