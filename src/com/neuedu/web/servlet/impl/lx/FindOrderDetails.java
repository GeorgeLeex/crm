package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.OrderServiceSupport;
/**
 * 根据订单编号查询该订单的信息以及订单明细信息
 * @author Administrator
 *
 */
public class FindOrderDetails extends OrderServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		return "kh8";
	}

}
