package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.UserLoginServiceSupport;
/**
 * 用户登出
 * @author Administrator
 *
 */
public class UserLogoff extends UserLoginServiceSupport{

	@Override
	public String execute() throws Exception {
		return "invalidate";
	}

}
