package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.CustomerLossService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService接口为CustomerLossService
 * @author Administrator
 *
 */
public abstract class CustomerLossServiceSupport extends ControllerSupport {

	public CustomerLossServiceSupport() {
		this.baseService = new CustomerLossService();
	}

}
