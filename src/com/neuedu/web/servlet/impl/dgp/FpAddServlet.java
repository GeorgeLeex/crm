package com.neuedu.web.servlet.impl.dgp;

import java.util.Map;

import com.neuedu.web.YxSupportt.YxServiceSupport;
/**
 * 指派销售机会给客户经理
 * @author Administrator
 *
 */
public class FpAddServlet extends YxServiceSupport {
/**
 * 如果指派成功,那么重查数据
 */
	@Override
	public String execute() throws Exception {
		if (this.baseService.update("update")) {
			Map<String, Object> map=this.baseService.queryForMap();
			this.addAttribute("row", map);
		}
		return "yx03";
	}

}
