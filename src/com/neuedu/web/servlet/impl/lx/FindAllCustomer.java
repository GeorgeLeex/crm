package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CustomerServiceSupport;
/**
 * 查询所有客户信息
 * @author Administrator
 *	@time 2018年3月8日 下午12:10:23
 */
public class FindAllCustomer extends CustomerServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAllByPage();
		return "kh1_3";
	}

}
