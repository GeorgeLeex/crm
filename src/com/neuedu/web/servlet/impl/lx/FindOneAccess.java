package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.AccessServiceSupport;
/**
 * 根据二级菜单编号查询指定二级菜单的信息
 * @author Administrator
 *
 */
public class FindOneAccess extends AccessServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		this.addAttribute("nowPage", this.getValueFromDto("nowPage"));
		return "us4";
	}

}
