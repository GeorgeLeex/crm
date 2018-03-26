package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.Fw04Support;

public class Fw04Servlet extends Fw04Support {

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "fw07";
	}

}
