package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.UserServiceSupport;
/**
 * 根据用户编号查询用户信息
 * @author Administrator
 *
 */
public class FindOneUser extends UserServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		this.addAttribute("nowPage", this.getValueFromDto("nowPage"));
		return "us2";
	}

}
