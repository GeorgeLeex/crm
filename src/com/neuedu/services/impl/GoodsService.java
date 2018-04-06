package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
import com.neuedu.system.tools.Tools;
/**
 * 对商品的处理
 * @author Administrator
 *
 */
public class GoodsService extends JdbcSupport {

	/**
	 * 根据商品编号查询单个商品的信息
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("SELECT s.sp_no, s.sp08, s.splb_no, s.sp10, s.sp09, s.sp03, s.sp05, h.hw_no, h.hw02, c.cf04 ")
		.append("    FROM t_sp s, t_hw h, t_cf c")
		.append("  WHERE  s.sp_no = c.sp_no")
		.append("    AND  c.hw_no = h.hw_no")
		.append("    AND  s.sp_no = ?");
		return this.singleQuery(sql.toString(), this.getVal("sp_no"));
	}

	/**
	 * 查询所有的商品
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		//编写统计记录条数的sql语句
		StringBuilder sql1 = new StringBuilder()
		.append("SELECT COUNT(sp_no) rowcount FROM t_sp WHERE state = '1' ");
		StringBuilder sql2 = new StringBuilder()
		.append("SELECT s.sp_no, s.sp08, s.sp09, s.sp10, s.sp03, s.sp05, z.zd_name")
		.append("    FROM t_sp s, t_zd z")
		.append("  WHERE  s.splb_no = z.zd_value")
		.append("    AND  z.zd_class = '商品类型'")
		.append("    AND  s.state = '1' ");
		//不定条件查询拼接
		List<Object> values = new ArrayList<Object>();
		//获取商品名称
		Object sp08 = this.getVal("qsp08");
		if (this.isNotNull(sp08)) {
			sql1.append(" AND sp08 like '%").append(sp08).append("%'");
			sql2.append(" AND s.sp08 like ? ");
			values.add("%" + sp08 + "%");
		}
		//获取产品型号
		Object pc03 = this.getVal("qsp10");
		if (this.isNotNull(pc03)) {
			sql1.append(" AND sp10 like '%").append(pc03).append("%'");
			sql2.append(" AND s.sp10 like ?");
			values.add("%" + pc03 + "%");
		}
		//获取产品批次
		Object pc02 = this.getVal("qsp09");
		if (this.isNotNull(pc02)) {
			sql1.append(" AND sp09 like '%").append(pc02).append("%'");
			sql2.append(" AND s.sp09 like ?");
			values.add("%" + pc02 + "%");
		}
		//获取产品类别
		Object sblbNo = this.getVal("qsplb_no");
		if (this.isNotNull(sblbNo)) {
			sql1.append(" AND splb_no = ").append(sblbNo);
			sql2.append(" AND s.splb_no = ?");
			values.add(sblbNo);
		}
		List<Map<String, Object>> mapList = new ArrayList<>();
		Map<String, Object> map = this.paging(sql1.toString(), sql2.toString(), values.toArray());
		mapList.add(map);
		return mapList;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.addGoods();
		} else if ("modify".equalsIgnoreCase(type)) {
			return this.modifyGoods();
		} else if ("delete".equalsIgnoreCase(type)) {
			return this.deleteGoods();
		}
		return false;
	}

	/**
	 * 删除商品
	 * @return
	 * @throws Exception
	 */
	private boolean deleteGoods() throws Exception{
		StringBuilder sql1 = new StringBuilder()
		.append("UPDATE t_sp SET state = '0' WHERE sp_no = ?");
		return this.executeManipulation(sql1.toString(), this.getVal("sp_no"));
	}

	/**
	 * 修改商品信息
	 * @return
	 * @throws Exception
	 */
	private boolean modifyGoods() throws Exception{
		//获取商品编号
		Object sp_no = this.getVal("sp_no");
		//修改商品自身信息
		StringBuilder sql1 = new StringBuilder()
		.append("UPDATE t_sp SET splb_no = ?, sp03 = ?, sp05 = ?,")
		.append(" sp08 = ?, sp09 = ?, sp10 = ?")
		.append(" WHERE sp_no = ?");
		Object[] values1 = {
				//商品类别编号, 单位, 价格
				this.getVal("splb_no"), this.getVal("sp03"), this.getVal("sp05"),
				//商品名, 批次, 型号
				this.getVal("sp08"),  this.getVal("sp09"),  this.getVal("sp10"),
				//商品编号
				sp_no
		};
		this.addPreparedStatement(sql1.toString(), values1);
		//修改存放记录
		StringBuilder sql2 = new StringBuilder()
		.append("UPDATE t_cf SET hw_no = ?, cf04 = ? WHERE sp_no = ?");
		Object[] values2 = {
				//货位编号,  数量,  商品编号
				this.getVal("hw_no"), this.getVal("cf04"), sp_no
		};
		this.addPreparedStatement(sql2.toString(), values2);
		return this.execute();
	}

	/**
	 * 添加商品
	 * @return
	 * @throws Exception
	 */
	private boolean addGoods() throws Exception{
		//编写添加商品的SQL语句
		StringBuilder sql1 = new StringBuilder()
		//插入商品编号, 商品类别编号, 单位, 价格, 商品名, 批次, 型号
		.append("INSERT INTO t_sp(sp_no, splb_no, sp03, sp05, sp08, sp09, sp10)")
		.append("VALUES(?, ?, ?, ?, ?, ?, ?)");
		//获取商品编号
		int sp_no = Tools.getSequenceId("sp_no");
		Object[] values1 = {
				//商品编号, 商品类别编号, 单位, 价格
				sp_no, this.getVal("splb_no"), this.getVal("sp03"), this.getVal("sp05"),
				//商品名, 批次, 型号
				this.getVal("sp08"),  this.getVal("sp09"),  this.getVal("sp10"),
		};
		this.addPreparedStatement(sql1.toString(), values1);
		//编写添加至存放记录表的sql语句
		String sql2 = "INSERT INTO t_cf(sp_no, hw_no, cf04) VALUES(?, ?, ?)";
		Object[] values2 = {
				//商品编号, 货位编号, 数量
				sp_no, this.getVal("hw_no"), this.getVal("cf04")
		};
		this.addPreparedStatement(sql2, values2);
		return this.execute();
	}

}
