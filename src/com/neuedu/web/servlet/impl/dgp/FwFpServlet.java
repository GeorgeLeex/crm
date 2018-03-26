package com.neuedu.web.servlet.impl.dgp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.services.FW.NameService;
import com.neuedu.web.FwSupport.FwSupport;

public class FwFpServlet extends  FwSupport{

	@Override
	public String execute() throws Exception {
		if (this.baseService.update("add")) {
			List<Object> list=new ArrayList<Object>();
			List<Map<String, Object>> list1=this.baseService.queryForList();
			NameService ns=new NameService();
			List<Map<String, Object>> list2=ns.queryForList();
			list.add(list1);
			list.add(list2);
			list.add(new HashMap().put("msg", "分配成功"));
			this.addAttribute("rows", list); 
		}
		return "fw02";
	}

}
