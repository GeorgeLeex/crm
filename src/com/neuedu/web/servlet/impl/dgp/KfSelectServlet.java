package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.services.yx.YxService;
import com.neuedu.web.YxSupportt.YxAddServiceSupport;
import com.neuedu.web.YxSupportt.YxServiceSupport;

public class KfSelectServlet extends YxAddServiceSupport{

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "yx06";
	}

}
