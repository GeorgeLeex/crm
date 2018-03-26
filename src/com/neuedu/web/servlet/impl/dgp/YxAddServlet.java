package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.YxSupportt.YxAddServiceSupport;
/**
 * 新建销售机会
 * @author Administrator
 *
 */
public class YxAddServlet extends YxAddServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add", "添加");
		return "yx02";
	}

}
