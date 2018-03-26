package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.TypeSupport;

public class FwAddServlet extends TypeSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add", "创建"); 
		return "fw01";
	}

}
