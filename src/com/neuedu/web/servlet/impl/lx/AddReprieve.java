package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CustomerLossServiceSupport;
/**
 * 根据客户流失编号对其追加暂缓措施
 * @author Administrator
 *
 */
public class AddReprieve extends CustomerLossServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add","暂缓措施追加");
		//追加暂缓措施之后分页重查数据
		this.simpleFindAllByPage();
		return "kh9";
	}


}
