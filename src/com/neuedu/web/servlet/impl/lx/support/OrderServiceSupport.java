package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.OrderService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService为OrderService
 * @author Administrator
 *
 */
public abstract class OrderServiceSupport extends ControllerSupport {

	public OrderServiceSupport() {
		this.baseService = new OrderService();
	}

}
