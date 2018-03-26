package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.AccessServiceSupport;
/**
 * 添加二级菜单
 * @author Administrator
 *
 */
public class AddAccess extends AccessServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add", "添加");
		this.simpleFindAllByPage();
		return "us3";
	}

}
