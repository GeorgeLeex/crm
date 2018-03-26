package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.BbSupportt.DjSupport;

public class DjServlet extends DjSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "bb02";
	}

}
