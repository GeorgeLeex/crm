package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.UserServiceSupport;
/**
 * 根据用户编号修改某个用户的信息
 * @author Administrator
 *
 */
public class UpdateUser extends UserServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("modify", "修改");
		this.simpleFindAllByPage();
		return "us1";
	}

}
