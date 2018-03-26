package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.Fw04SelectSupport;

public class Fw04AddServlet extends Fw04SelectSupport {

	@Override
	public String execute() throws Exception {
			this.simpleFindOne();
			return "fw04";
	}

}
