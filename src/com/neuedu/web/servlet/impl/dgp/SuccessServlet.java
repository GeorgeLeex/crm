package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.YxSupportt.kfUpdateSupport;
/**
 * 计划执行成功 跳转到
 * @author Administrator
 *
 */
public class SuccessServlet extends  kfUpdateSupport{

	@Override
	public String execute() throws Exception {
		if (this.baseService.update("success")) {
			this.simpleFindOne();
		}
		return "kh11";
	}

}
