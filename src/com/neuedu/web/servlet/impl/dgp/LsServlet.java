package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.BbSupportt.LsSelectSupport;

public class LsServlet extends LsSelectSupport{

	@Override
	public String execute() throws Exception {
			this.simpleFindAll();
			return "bb04";
	}

}
