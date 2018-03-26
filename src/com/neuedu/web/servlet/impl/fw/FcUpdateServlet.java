package com.neuedu.web.servlet.impl.fw;

import com.neuedu.web.FwSupport.Fw03TypeSupport;

public class FcUpdateServlet extends Fw03TypeSupport {

	@Override
	public String execute() throws Exception {
		
		if (this.baseService.update("update")) {
			this.simpleFindOne();
			return "fw03";
		}
		
		return null;
	}

}
