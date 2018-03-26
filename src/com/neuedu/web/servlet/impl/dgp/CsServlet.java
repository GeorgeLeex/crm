package com.neuedu.web.servlet.impl.dgp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.web.FwSupport.CsSupport;

public class CsServlet extends CsSupport {

	@Override
	public String execute() throws Exception {
		List<Object> list=new ArrayList<Object>();
		List<Map<String, Object>> list1=this.baseService.queryForList();
		list.add(list1);
		this.addAttribute("rows", list);
		return "fw02";
	}

}
