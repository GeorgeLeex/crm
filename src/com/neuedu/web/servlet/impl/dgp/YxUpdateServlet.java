package com.neuedu.web.servlet.impl.dgp;

import java.util.Map;

import com.neuedu.web.YxSupportt.UpdateSelectServiceSupport;
import com.neuedu.web.YxSupportt.YxServiceSupport;
/**
 * 编辑开发计划
 */
public class YxUpdateServlet extends UpdateSelectServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("update", "修改");
		if (this.baseService.update("update")) {
			 Map<String, Object> map=this.baseService.queryForMap();
			this.addAttribute("row", map);
		}
		return "yx07";
	}

}
