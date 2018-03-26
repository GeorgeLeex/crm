package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.web.YxSupportt.MhServiceSupport;
/**
 * 模糊查询
 * @author Administrator
 *
 */
public class MhselectServlet extends MhServiceSupport{
	/**
	 * 销售计划  模糊查询
	 */
	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		return "yx01";
	}

}
