package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.Fw03TypeSupport;

public class FcSelectServlet extends Fw03TypeSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		return "fw03";
	}

}
