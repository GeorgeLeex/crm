package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.UserServiceSupport;
/**
 * 查询所有用户
 * @author Administrator
 *
 */
public class FindAllUser extends UserServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAllByPage();
		return "us1";
	}

}
