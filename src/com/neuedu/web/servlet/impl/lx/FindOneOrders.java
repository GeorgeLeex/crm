package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.web.servlet.impl.lx.support.OrdersServiceSupport;
/**
 * 根据历史订单编号查询订单明细
 * @author Administrator
 *
 */
public class FindOneOrders extends OrdersServiceSupport {

	@Override
	public String execute() throws Exception {
		Map<String, Object> map = this.baseService.queryForMap();
		//获取订单信息
		Map<String, Object> ls = (Map<String, Object>) map.get("ls");
		//获取历史订单明细信息
		List<Map<String, Object>> dds = (List<Map<String, Object>>) map.get("dds");
		this.addAttribute("ls", ls);
		this.addAttribute("dds", dds);
		this.addAttribute("nowPage", this.getValueFromDto("nowPage"));
		return "dd1_2";
	}

}
