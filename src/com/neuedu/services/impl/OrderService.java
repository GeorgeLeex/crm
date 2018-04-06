package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 客户历史订单的处理
 * @author Administrator
 * @time 2018年3月10日 下午7:24:25
 */
public class OrderService extends JdbcSupport {

	/**
	 * 查询历史订单明细
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		//获取历史订单的编号
		Object lsNo = this.getVal("ls_no");
		//查询历史订单的sql语句
		StringBuilder sql1 = new StringBuilder()
		.append("SELECT t.ls_no, ls04, t.ls05, t.ls06, j.jy_name , s.dd07")
		.append("		    FROM t_ls t, t_jy j, (SELECT ls_no,SUM(dd07) dd07 FROM t_dd GROUP BY ls_no) s ")
		.append("		  WHERE  t.jy_no = j.jy_no")
		.append("        AND  t.ls_no = s.ls_no")
		.append("		    AND  t.ls_no = ?");
		//获取到历史订单信息集合
		Map<String, Object> map1 = this.singleQuery(sql1.toString(), lsNo);
		//查询指定订单下订单明细的sql语句
		StringBuilder sql2 = new StringBuilder()
		.append("SELECT s.sp08, s.sp03, d.dd04, d.dd06, d.dd07")
		.append("    FROM t_dd d, t_sp s")
		.append("  WHERE  d.sp_no = s.sp_no")
		.append("    AND  d.ls_no = ?");
		//获取订单明细的list集合
		List<Map<String, Object>> map2 = this.multipleQuery(sql2.toString(), lsNo);
		//将所有的查询结果添加至最终的Map集合
		Map<String, Object> map = new HashMap<String, Object>();
		//添加历史订单信息至map集合
		map.put("ls", map1);
		//添加订单明细至map集合
		map.put("dds", map2);
		return map;
	}

	/**
	 * 根据客户编号分页查询该客户的所有历史订单
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		//最终返回的list集合
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		Object kh_no = this.getVal("kh_no");
		//编写统计记录条数语句
		StringBuilder completedSql = new StringBuilder()
		.append("SELECT count(ls_no) rowcount FROM t_ls WHERE state = '1' AND kh_no = ").append(kh_no);
		//编写内部查询语句
		StringBuilder sql = new StringBuilder()
		.append("SELECT kh_no, kh02, ls_no, ls04, ls05, jy_name")
		.append("    FROM v_ls")
		.append("  WHERE  state = '1'")
		.append("   AND   kh_no = ?")
		.append("  ORDER BY  ls04");
		Map<String, Object> resultMap = this.paging(completedSql.toString(), sql.toString(), kh_no);
		//判断查询出的记录是否为空,如果为空就查询客户信息
		List<Map<String, Object>> rows = (List<Map<String, Object>>) resultMap.get("rows");
		if (rows == null || rows.size() == 0) {
			StringBuilder sql2 = new StringBuilder()
			.append("SELECT kh_no, kh02 FROM t_kh WHERE kh_no = ").append(kh_no);
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
		// TODO Auto-generated method stub
		return false;
	}

}
