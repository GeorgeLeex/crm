package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.Fw03TypeSupport;

public class Fw03TypeServlet extends Fw03TypeSupport{

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "fw06";
	}

}
