package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CustomerLossServiceSupport;
/**
 * 查询所有状态为暂缓流失的流失纪录
 * @author Administrator
 *
 */
public class FindAllCustomerLoss extends CustomerLossServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAllByPage();
		return "kh9";
	}

}
