package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.UserLoginService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService为UserLoginService
 * @author Administrator
 *
 */
public abstract class UserLoginServiceSupport extends ControllerSupport{
	public UserLoginServiceSupport() {
		this.baseService = new UserLoginService();
	}
}
