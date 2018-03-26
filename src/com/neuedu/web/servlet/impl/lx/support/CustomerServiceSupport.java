package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.CustomerService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 为BaseService接口实例化
 * @author 李宣
 * @time 2018年3月8日 下午12:10:51
 */
public abstract class CustomerServiceSupport extends ControllerSupport {

	public CustomerServiceSupport() {
		this.baseService = new CustomerService();
	}

}
