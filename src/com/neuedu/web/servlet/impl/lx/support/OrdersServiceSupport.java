package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.OrdersService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService为OrdersService
 * @author Administrator
 *
 */
public abstract class OrdersServiceSupport extends ControllerSupport {

	public OrdersServiceSupport() {
		this.baseService = new OrdersService();
	}

}
