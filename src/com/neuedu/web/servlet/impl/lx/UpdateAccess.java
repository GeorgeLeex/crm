package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.AccessServiceSupport;
/**
 * 修改二级菜单
 * @author Administrator
 *
 */
public class UpdateAccess extends AccessServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("modify", "修改");
		this.simpleFindAllByPage();
		return "us3";
	}

}
