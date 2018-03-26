package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.web.servlet.impl.lx.support.OrderServiceSupport;
/**
 * 根据客户编号查询该客户的所有订单
 * @author Administrator
 *
 */
public class FindAllOrder extends OrderServiceSupport {

	@Override
	public String execute() throws Exception {
		List<Map<String,Object>> list = this.baseService.queryForList();
		//如果list的size为1,表示查询到了客户历史订单
		if (list != null && list.size() == 1) {
			Map<String, Object> map = list.get(0);
			this.addAttribute("rows", map.get("rows"));
			this.addAttribute("nowPage", map.get("nowPage"));
			this.addAttribute("pageCount", map.get("pageCount"));
			this.addAttribute("rowCount", map.get("rowCount"));
			this.addAttribute("numbers", map.get("numbers"));
			//如果list的size为2,则表示没有查询到的客户的历史订单,然后查询了客户信息
		} else if (list.size() == 2) {
			//添加客户编号与客户名称至request域
			Map<String, Object> map = list.get(0);
			this.addAttribute("kh_no", map.get("kh_no"));
			this.addAttribute("kh02", map.get("kh02"));
		}
		return "kh7";
	}

}
