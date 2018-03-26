package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.ContactsServiceSupport;
/**
 * 根据联系人编号删除单个联系人
 * @author Administrator
 * @time 2018年3月10日 下午2:44:00
 */
public class DeleteOneContact extends ContactsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("delete", "删除");
		this.queryAfterUpdate();
		return "kh1_2";
	}

}
