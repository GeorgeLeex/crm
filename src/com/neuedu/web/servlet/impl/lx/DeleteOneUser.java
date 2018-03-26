package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.UserServiceSupport;
/**
 * 根据客户编号删除某个客户
 * @author Administrator
 *
 */
public class DeleteOneUser extends UserServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("delete", "删除");
		this.simpleFindAllByPage();
		return "us1";
	}

}
