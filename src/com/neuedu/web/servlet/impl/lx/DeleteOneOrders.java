package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.OrdersServiceSupport;
/**
 * 根据历史订单编号删除某个订单
 * @author Administrator
 *
 */
public class DeleteOneOrders extends OrdersServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("delete","删除");
		this.simpleFindAllByPage();
		return "dd1";
	}

}
