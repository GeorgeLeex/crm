package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CustomerServiceSupport;
/**
 * 添加客户
 * @author 李宣
 * @time 2018年3月8日 下午8:23:05
 */
public class AddCustomer extends CustomerServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add", "添加");
		this.simpleFindAllByPage();
		return "kh1_3";
	}

}
