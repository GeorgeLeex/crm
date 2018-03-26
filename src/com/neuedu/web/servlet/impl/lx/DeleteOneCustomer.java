package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CustomerServiceSupport;
/**
 * 根据客户编号删除单个客户信息
 * @author Administrator
 *
 */
public class DeleteOneCustomer extends CustomerServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("delete", "删除");
		this.simpleFindAllByPage();
		return "kh1_3";
	}

}
