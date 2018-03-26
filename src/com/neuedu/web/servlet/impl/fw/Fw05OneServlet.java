package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.Fw05Support;

public class Fw05OneServlet extends Fw05Support {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		return "fw05";
	}

}
