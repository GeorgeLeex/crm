package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.YxSupportt.YxAddServiceSupport;

public class deleteOneServlet extends YxAddServiceSupport{

	/**
	 * ajax响应的数据
	 */
	@Override
	public String execute() throws Exception {
		String status = "NO";
		if (this.baseService.update("delete")) {
			status = "OK";
		}
		this.addResponseMessage("status", status);
		return "response:";
	}

}
