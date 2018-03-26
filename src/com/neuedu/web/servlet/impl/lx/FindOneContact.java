package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.ContactsServiceSupport;
/**
 * 根据联系人编号查询出该联系人的所有信息
 * @author Administrator
 * @time 2018年3月10日 下午2:06:49
 */
public class FindOneContact extends ContactsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		this.addAttribute("kh_no", this.getValueFromDto("kh_no"));
		return "kh2";
	}

}
