package com.neuedu.web.servlet.impl.dgp;

import java.util.List;
import java.util.Map;

import com.neuedu.web.YxSupportt.UpdateSelectServiceSupport;

public class FpSelectServlet extends UpdateSelectServiceSupport{

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		List<Map<String, Object>> list=this.baseService.queryForList();
		this.addAttribute("rows", list); 
		return "yx03";
	}

}
