package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.OrdersServiceSupport;
/**
 * 查询所有的订单
 * @author Administrator
 *
 */
public class FindAllOrders extends OrdersServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAllByPage();
		return "dd1";
	}


}
