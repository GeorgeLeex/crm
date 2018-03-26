package com.neuedu.web.servlet.impl.dgp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.web.BbSupportt.YearSelectSupport;

public class YearSelectServlet extends YearSelectSupport
{

	@Override
	public String execute() throws Exception {
		BbYearService bb=new BbYearService();
		List<Map<String, Object>> list1=this.baseService.queryForList();
		List<Object> list=new ArrayList<Object>();
		list.add(bb.queryForList());
		list.add(list1);
		this.addAttribute("rows", list);
		return "bb01";
	}

}
