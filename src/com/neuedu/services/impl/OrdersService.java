package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
import com.neuedu.system.tools.Tools;
/**
 * 处理所有的订单
 * @author Administrator
 *
 */
public class OrdersService extends JdbcSupport {

	/**
	 * 根据订单编号查询某一订单的订单明细
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		//获取历史订单编号
		Object lsNo = this.getVal("ls_no");
		//查询订单信息
		StringBuilder sql1 = new StringBuilder()
		.append("SELECT k.kh02, k.kh10, ls04, l.ls05, j.jy_name, s.ls06")
		.append("  FROM t_kh k, t_ls l, t_jy j,")
		.append("       (SELECT ls_no, SUM(dd07) ls06  FROM t_dd GROUP BY ls_no) s")
		.append(" WHERE k.kh_no = l.kh_no")
		.append("   AND l.jy_no = j.jy_no")
		.append("   AND l.ls_no = s.ls_no")
		.append("   AND l.ls_no = ?");
		Map<String, Object> map = this.singleQuery(sql1.toString(), lsNo);
		//查询订单明细信息,各种商品的信息
		StringBuilder sql2 = new StringBuilder()
		.append("SELECT s.sp08, d.dd06, d.dd04, d.dd07")
		.append("  FROM t_sp s, t_dd d")
		.append(" WHERE s.sp_no = d.sp_no")
		.append("   AND d.ls_no = ?");
		List<Map<String,Object>> list = this.multipleQuery(sql2.toString(), lsNo);
		resultMap.put("ls", map);
		resultMap.put("dds", list);
		return resultMap;
	}

	/**
	 * 查询所有的订单
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		//编写统计记录条数的SQL语句
		StringBuilder sql1 = new StringBuilder()
		.append("SELECT   COUNT(l.ls_no) rowcount")
		.append("    FROM t_ls l, t_kh k")
		.append("  WHERE  l.kh_no = k.kh_no")
		.append("    AND  l.state = '1'");
		//编写内部查询语句
		StringBuilder sql2 = new StringBuilder()
		.append("SELECT   l.ls_no, k.kh02, ls04, l.ls05, k.kh10, j.jy_name")
		.append("    FROM t_ls l, t_kh k, t_jy j")
		.append("  WHERE  l.kh_no = k.kh_no")
		.append("    AND  l.jy_no = j.jy_no")
		.append("    AND  l.state = '1'");
		//不定条件查询
		List<Object> values = new ArrayList<>();
		//获取客户名称
		Object kh02 = this.getVal("qkh02");
		if (this.isNotNull(kh02)) {
			sql1.append(" AND  k.kh02 LIKE '%").append(kh02).append("%'");
			sql2.append(" AND  k.kh02 LIKE ?");
			values.add("%" + kh02 + "%");
		}
		//获取订单日期
		Object ls04 = this.getVal("qls04");
		if (this.isNotNull(ls04)) {
			sql1.append(" AND  date_format(l.ls04, '%Y-%m-%d') = '").append(ls04).append("'");
			sql2.append(" AND  date_format(l.ls04, '%Y-%m-%d') = ?");
			values.add(ls04);
		}
		Map<String, Object> map = this.paging(sql1.toString(), sql2.toString(), values.toArray());
		mapList.add(map);
		return mapList;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.addOrders();
		} else if ("delete".equalsIgnoreCase(type)) {
			return this.deleteOrders();
		}
		return false;
	}

	/**
	 * 根据订单编号删除某个订单
	 * @return
	 * @throws Exception
	 */
	private boolean deleteOrders() throws Exception{
		return this.executeManipulation("UPDATE t_ls SET state = '0' WHERE ls_no = ?", this.getVal("ls_no"));
	}

	/**
	 * 给指定用户添加订单记录
	 * @return
	 * @throws Exception
	 */
	private boolean addOrders() throws Exception{
		//获取订单编号
		int lsNo = Tools.getSequenceId("ls_no");
		//向历史订单中表中插入记录
		String sql1 = "INSERT INTO t_ls(ls_no, kh_no, ls04, ls05, jy_no) VALUES(?,?,?,?,4)";
		//值数组
		Object[] values1 = {
			//订单编号, 客户编号, 日期, 送货地址
			lsNo, this.getVal("kh_no"), this.getVal("ls04"), this.getVal("ls05")
		};
		this.addPreparedStatement(sql1, values1);
		//获取商品编号和数量的数组
		String[] spNos = this.getValForArray("sp_no");
		String[] cf04s = this.getValForArray("cf04");
		//向订单明细表中插入记录
		StringBuilder sql2 = new StringBuilder()
		 .append(" INSERT INTO t_dd(ls_no, sp_no, dd04,dd06, dd07) ")
		 .append(" VALUES(?, ?, ?, ")
		 .append(" (SELECT sp05 FROM t_sp WHERE sp_no = ?) , ")
		 .append(" (SELECT sp05 FROM t_sp WHERE sp_no = ?)*?)");
		Object[][] values = new Object[spNos.length][];
		for (int i = 0; i < spNos.length; i++) {
			Object spNo = spNos[i];
			Object cf04 = cf04s[i];
			Object[] values2 = {
				lsNo, spNo, cf04, spNo, spNo, cf04 
			};
			values[i] = values2;
		}
		this.addPreparedStatementForBatch(sql2.toString(), values);
		return this.execute();
	}

}
