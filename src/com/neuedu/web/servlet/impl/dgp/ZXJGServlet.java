package com.neuedu.web.servlet.impl.dgp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.web.YxSupportt.kfUpdateSupport;
/**
 * 添加执行计划的结果
 * @author Administrator
 *
 */
public class ZXJGServlet extends kfUpdateSupport {

	@Override
	public String execute() throws Exception {
		if (this.baseService.update("update")) {
			Map<String, Object> map=this.baseService.queryForMap();
			List<Map<String, Object>> list=this.baseService.queryForList();
			List<Object> li=new ArrayList<Object>(); 
			li.add(map);//Map  0
			li.add(list);//List  1
			this.addAttribute("rows", li);
		}
		return "yx05";
	}

}
