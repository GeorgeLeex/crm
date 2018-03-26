package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CustomerLossServiceSupport;
/**
 * 确认客户流失
 * @author Administrator
 *
 */
public class SureCustomerLoss extends CustomerLossServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("sure", "确认流失");
		//分页重查数据
		this.simpleFindAllByPage();
		return "kh9";
	}

}
