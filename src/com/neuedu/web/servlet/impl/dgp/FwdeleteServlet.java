package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.FwSupport.FwLikeSupport;

public class FwdeleteServlet extends FwLikeSupport {

	@Override
	public String execute() throws Exception {
		String state="NO";
		if (this.baseService.update("delete")) {
			state="OK";
			this.addResponseMessage("state", state);
			return "response:";
		} 
		this.addResponseMessage("state", state);
		return "response:";
	}

}
