package com.neuedu.web.servlet.impl.dgp;

import java.util.List;
import java.util.Map;

import com.neuedu.web.YxSupportt.KhAddSupport;

public class FailServlet extends KhAddSupport {

	@Override
	public String execute() throws Exception {
		if (this.baseService.update("fail")) {
			 List<Map<String, Object>> list=this.baseService.queryForList();
			this.addAttribute("rows", list);
		}
			return "yx06";
	}

}
