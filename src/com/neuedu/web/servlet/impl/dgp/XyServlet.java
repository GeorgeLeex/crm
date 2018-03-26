package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.BbSupportt.XySupport;

public class XyServlet extends XySupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "bb02";
	}

}
