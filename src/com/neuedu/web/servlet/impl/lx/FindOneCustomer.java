package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CustomerServiceSupport;
/**
 * 查询单个用户的信息
 * @author Administrator
 *
 */
public class FindOneCustomer extends CustomerServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		this.addAttribute("nowPage", this.getValueFromDto("nowPage"));
		return "kh1_4";
	}

}
