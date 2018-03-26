package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CommuRecordServiceSupport;
/**
 * 添加交往记录
 * @author Administrator
 *
 */
public class AddCommuRecord extends CommuRecordServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add", "添加");
		this.simpleFindAllByPage();
		return "kh4";
	}

}
