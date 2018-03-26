package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.UserServiceSupport;
/**
 * 添加用户
 * @author Administrator
 *
 */
public class AddUser extends UserServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add", "添加");
		this.simpleFindAllByPage();
		return "us1";
	}

}
