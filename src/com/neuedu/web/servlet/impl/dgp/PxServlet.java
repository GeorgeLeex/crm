package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.BbSupportt.PxSupport;

public class PxServlet extends PxSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "bb02";
	}

}
