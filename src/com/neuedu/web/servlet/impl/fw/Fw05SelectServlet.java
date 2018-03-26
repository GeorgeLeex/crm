package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.Fw05SelectSupport;

public class Fw05SelectServlet extends Fw05SelectSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "fw08";
	}

}
