package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.UserService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService为UserService
 * @author Administrator
 *
 */
public abstract class UserServiceSupport extends ControllerSupport {

	public UserServiceSupport() {
		this.baseService = new UserService();
	}

}
