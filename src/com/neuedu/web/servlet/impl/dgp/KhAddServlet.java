package com.neuedu.web.servlet.impl.dgp;

import java.util.List;
import java.util.Map;

import com.neuedu.web.YxSupportt.KhAddSupport;
import com.neuedu.web.YxSupportt.kfUpdateSupport;

public class KhAddServlet extends KhAddSupport{

	public String execute() throws Exception {
		this.simpleFindAll();
		return "yx06";
	}

}
