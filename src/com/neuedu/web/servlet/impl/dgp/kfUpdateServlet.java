package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.YxSupportt.kfUpdateSupport;
/**
 * 编辑开发计划
 * @author Administrator
 *
 */
public class kfUpdateServlet extends kfUpdateSupport{

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		return "yx04";
	}

}
