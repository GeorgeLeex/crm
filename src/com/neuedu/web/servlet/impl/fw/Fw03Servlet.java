package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.Fw03Support;

public class Fw03Servlet extends Fw03Support{

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "fw06";
	}

}
