package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.ContactsService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 为ControllerSupport中的成员变量BaseService通过实现类ContactsService进行实例化
 * @author Administrator
 * @time 2018年3月10日 上午11:51:07
 */
public abstract class ContactsServiceSupport extends ControllerSupport {

	public ContactsServiceSupport() {
		this.baseService = new ContactsService();
	}
}
