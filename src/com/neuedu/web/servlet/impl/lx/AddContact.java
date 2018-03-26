package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.ContactsServiceSupport;
/**
 * 添加客户的联系人
 * @author Administrator
 * @time 2018年3月10日 下午12:27:17
 */
public class AddContact extends ContactsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add", "添加");
		//添加后重查数据返回至联系人管理页面
		this.queryAfterUpdate();
		return "kh1_2";
	}

}
