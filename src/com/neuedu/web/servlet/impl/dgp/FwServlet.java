package com.neuedu.web.servlet.impl.dgp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.services.bb.FwYearSelectService;
import com.neuedu.web.BbSupportt.FwSelectSupport;

public class FwServlet extends FwSelectSupport
{

	@Override
	public String execute() throws Exception {
		List<Object> list=new ArrayList<Object>();
		//全部数据 
		List<Map<String, Object>> list1=this.baseService.queryForList();
		 FwYearSelectService fw=new  FwYearSelectService();
		 //年份
		 List<Map<String, Object>> list2=fw.queryForList();
		 list.add(list1); 
		 list.add(list2);
		 this.addAttribute("rows", list);
		return "bb03";
	}

}
