package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.AccessServiceSupport;
/**
 * 删除某个二级菜单
 * @author Administrator
 *
 */
public class DeleteOneAccess extends AccessServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("delete", "删除");
		this.simpleFindAllByPage();
		return "us3";
	}

}
