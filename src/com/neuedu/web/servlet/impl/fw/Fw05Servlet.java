package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.Fw05Support;

public class Fw05Servlet extends Fw05Support{

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "fw08";
	}

}
