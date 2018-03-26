package com.neuedu.web.servlet.impl.dgp;

import java.util.Map;

import com.neuedu.services.yx.UpdateSelectService;
import com.neuedu.web.YxSupportt.MhServiceSupport;
/**
 * 添加开发计划项
 * @author Administrator
 *
 */
public class jhAddServlet extends MhServiceSupport {

	@Override
	public String execute() throws Exception {
		if (this.baseService.update("update")) {
			Map<String, Object> map = this.baseService.queryForMap();
			this.addAttribute("row", map);
		}
		return "yx04";
	}

}
