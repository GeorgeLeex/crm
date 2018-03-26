package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 查询商品库存
 * @author Administrator
 *
 */
public class StockService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		
		return null;
	}

	/**
	 * 分页查询所有商品的库存信息
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		//最终返回的list集合
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		//编写统计记录条数的sql语句
		StringBuilder sql1 = new StringBuilder()
		.append("SELECT COUNT(s.sp_no) ROWCOUNT")
		.append("    FROM t_sp s, t_cf c, t_hw h, t_ck ck")
		.append("  WHERE  s.sp_no = c.sp_no")
		.append("    AND  c.hw_no = h.hw_no")
		.append("    AND  h.ck_no = ck.ck_no");
		//编写查询记录信息的sql语句
		StringBuilder sql2 = new StringBuilder()
		.append("SELECT s.sp08, h.hw02, c.cf04, ck.ck02")
		.append("    FROM t_sp s, t_hw h, t_cf c, t_ck ck")
		.append("  WHERE  s.sp_no = c.sp_no")
		.append("    AND  c.hw_no = h.hw_no")
		.append("    AND  h.ck_no = ck.ck_no");
		//不定条件查询
		List<Object> values = new ArrayList<>();
		//获取产品名
		Object sp08 = this.getVal("sp08");
		if (this.isNotNull(sp08)) {
			sql1.append(" AND s.sp08 like '%").append(sp08).append("%'");
			sql2.append(" AND s.sp08 like ? ");
			values.add("%" + sp08 + "%");
		}
		//获取仓库名
		Object ck02 = this.getVal("ck02");
		if (this.isNotNull(ck02)) {
			sql1.append(" AND ck.ck02 like '%").append(ck02).append("%'");
			sql2.append(" AND ck.ck02 like ?");
			values.add("%" + ck02 + "%");
		}
		Map<String, Object> map = this.paging(sql1.toString(), sql2.toString(), values.toArray());
		mapList.add(map);
		return mapList;
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
