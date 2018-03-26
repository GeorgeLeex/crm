package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.OrdersServiceSupport;
/**
 * 给指定用户添加订单记录
 * @author Administrator
 *
 */
public class AddOrders extends OrdersServiceSupport {

	@Override
	public String execute() throws Exception {
		boolean flag = this.baseService.update("add");
		if(!flag){
			this.addAttribute("msg", "此次购买的商品数量超出了该商品的最大数量");
			return "dd1_1";
		} else {
			this.addAttribute("msg", "添加成功!");
		}
		this.simpleFindAllByPage();
		return "dd1";
	}

}
