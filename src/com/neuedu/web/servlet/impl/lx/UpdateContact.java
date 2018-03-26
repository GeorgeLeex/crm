package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.ContactsServiceSupport;
/**
 * 修改联系人信息
 * @author Administrator
 * @time 2018年3月10日 下午2:30:57
 */
public class UpdateContact extends ContactsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("modify","修改");
		this.queryAfterUpdate();
		return "kh1_2";
	}

}
