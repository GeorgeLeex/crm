package com.neuedu.web.servlet.impl.dgp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.web.YxSupportt.MhServiceSupport;
import com.neuedu.web.YxSupportt.kfUpdateSupport;
/**
 * 执行开发计划
 * @author Administrator
 *
 */
public class ZXUpdateServlet extends kfUpdateSupport{

	@Override
	public String execute() throws Exception {
		Map<String, Object> map=this.baseService.queryForMap();
		List<Map<String, Object>> list=this.baseService.queryForList();
		List<Object> li=new ArrayList<Object>(); 
		li.add(map);//Map  0
		li.add(list);//List  1
		this.addAttribute("rows", li);
		return "yx05";
	}

}
