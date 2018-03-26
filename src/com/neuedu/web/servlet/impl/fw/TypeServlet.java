package com.neuedu.web.servlet.impl.fw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.services.FW.FwCsService;
import com.neuedu.web.FwSupport.TypeSupport;

public class TypeServlet extends  TypeSupport  {

	@Override
	public String execute() throws Exception {
		List<Map<String, Object>> list1=this.baseService.queryForList();
		FwCsService fc=new FwCsService(); 
		List<Map<String, Object>> list2=fc.queryForList();
		List<Object> list=new ArrayList<Object>();
		list.add(list1);
		list.add(list2);
		this.addAttribute("rows", list);
		return "fw01";
	}

}
