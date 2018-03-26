package com.neuedu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 处理客户的操作
 * @author Administrator
 *
 */
public class CustomerService extends JdbcSupport {

	/**
	 * 客户单例查询,根据客户编号查询所有信息
	 */
	@Override
	public Map<String, Object> queryForMap() throws Exception {
		String sql = "SELECT * FROM v_kh WHERE state = '1' AND kh_no = ?";
		return this.singleQuery(sql, this.getVal("kh_no"));
	}

	/**
	 * 查询所有客户的编号, 名称, 客户经理, 等级, 电话, 法人, 开户银行信息
	 */
	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		//编写统计记录条数的sql语句
		StringBuilder completedSql = new StringBuilder() 
		.append("SELECT count(kh_no) rowcount FROM t_kh WHERE state = '1' ");
		//参数值集合
		List<Object> values = new ArrayList<>();
		//编写内部sql语句
		StringBuilder sql2 = new StringBuilder()
		.append("            SELECT kh_no, kh02, name, dj_name, kh10, kh14, kh17")
		.append("            FROM v_kh WHERE state = '1'");
		//.append("            FROM t_kh k, t_us u, t_dj d ")
		/*.append("            WHERE k.dj_no = d.dj_no(+)")
		.append("            AND k.us_no = u.us_no(+)");*/
		
		//不定条件查询,根据客户名称, 法人, 客户经理, 客户等级中任意挑选几个条件进行查询
		//获取客户名称
		Object kh02 = this.getVal("qkh02");
		if (this.isNotNull(kh02)) {
			completedSql.append(" AND kh02 like ").append("'%").append(kh02).append("%'");
			sql2.append(" AND kh02 like ?");
			values.add("%" + kh02 + "%");
		}
		//获取法人
		Object kh14 = this.getVal("qkh14");
		if (this.isNotNull(kh14)) {
			completedSql.append(" AND kh14 like ").append("'%").append(kh14).append("%'");
			sql2.append(" AND kh14 like ?");
			values.add("%" + kh14 + "%");
		}
		//获取客户经理编号
		Object us_no = this.getVal("qus_no");
		if (this.isNotNull(us_no)) {
			completedSql.append(" AND us_no = ").append(us_no);
			sql2.append(" AND us_no = ?");
			values.add(us_no);
		}
		//获取客户等级编号
		Object dj_no = this.getVal("qdj_no");
		if (this.isNotNull(dj_no)) {
			completedSql.append(" AND dj_no = ").append(dj_no);
			sql2.append(" AND dj_no = ?");
			values.add(dj_no);
		}
		//获取分页处理结果
		Map<String, Object> resultMap = this.paging(completedSql.toString(), sql2.toString(), values.toArray());
		List<Map<String, Object>> mapList = new ArrayList<>();
		mapList.add(resultMap);
		return mapList;
	}

	@Override
	public boolean update(String type) throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			return this.addCustomer();
		} else if ("modify".equalsIgnoreCase(type)) {
			return this.modify();
		} else if ("delete".equalsIgnoreCase(type)) {
			return this.deleteOneCustomer();
		}
		return false;
	}
	
	/**
	 * 添加客户
	 * @return
	 * @throws Exception
	 */
	public boolean addCustomer() throws Exception {
		StringBuilder sql = new StringBuilder()
		.append("insert into t_kh(")
				  //客户编号, 客户名称, 地区, 经理编号, 等级编号, 满意度编号
		.append("       kh_no,kh02, kh03, us_no, dj_no, px_no, ")
		         //  信用编号,  地址,  邮政编码, 电话,  传真,  网址,  营业注册执照号
		.append("       xy_no, kh08, kh09, kh10, kh11, kh12, kh13, ")
		        //   法人     	 注册资金  年营业额  开户银行  银行账号  地税登记号 国税登记号
		.append("       kh14, kh15, kh16, kh17, kh18, kh19, kh20)")
		.append("  values (seq_kh_no.nextval, ?, ?, ?, ?, ?,")
		.append("         ?, ?, ?, ?, ?, ?, ?,")
		.append("        ?, ?, ?, ?, ?, ?, ?)");
		Object[] args = {
				this.getVal("kh02"), this.getVal("kh03"), this.getVal("us_no"), this.getVal("dj_no"), this.getVal("px_no"), 
				this.getVal("xy_no"), this.getVal("kh08"), this.getVal("kh09"), this.getVal("kh10"), this.getVal("kh11"), this.getVal("kh12"), this.getVal("kh13"),  
				this.getVal("kh14"), this.getVal("kh15"), this.getVal("kh16"), this.getVal("kh17"), this.getVal("kh18"), this.getVal("kh19"), this.getVal("kh20"),  
		};
		return executeManipulation(sql.toString(), args);
	}

	/**
	 * 修改用户信息
	 * @return
	 * @throws Exception
	 */
	public boolean modify() throws Exception{
		StringBuilder sql = new StringBuilder()
		.append("UPDATE t_kh SET ")
		.append("kh02 = ?, kh03 = ?, us_no = ?, dj_no = ?, px_no = ?,")
		.append("xy_no = ?, kh08 = ?, kh09 = ?, kh10 = ?, kh11 = ?, ")
		.append("kh12 = ?, kh13 = ?, kh14 = ?, kh15 = ?, kh16 = ?,")
		.append("kh17 = ?, kh18 = ?,  kh19 = ?, kh20 = ?")
		.append("WHERE kh_no = ?");
		Object[] args = {
			this.getVal("kh02"), this.getVal("kh03"), this.getVal("us_no"), this.getVal("dj_no"), this.getVal("px_no"), 
			this.getVal("xy_no"), this.getVal("kh08"), this.getVal("kh09"), this.getVal("kh10"), this.getVal("kh11"), this.getVal("kh12"), this.getVal("kh13"),  
			this.getVal("kh14"), this.getVal("kh15"), this.getVal("kh16"), this.getVal("kh17"), this.getVal("kh18"), this.getVal("kh19"), this.getVal("kh20"),  
			this.getVal("kh_no")
		};
		return this.executeManipulation(sql.toString(), args);
	}
	
	/**
	 * 根据客户编号删除单个客户信息
	 * @return
	 * @throws Exception
	 */
	private boolean deleteOneCustomer() throws Exception {
		String sql = "UPDATE t_kh SET state = '0' WHERE kh_no = ?";
		return this.executeManipulation(sql, this.getVal("kh_no"));
	}
}
