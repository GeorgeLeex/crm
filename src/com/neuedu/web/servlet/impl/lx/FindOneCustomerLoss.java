package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CustomerLossServiceSupport;
/**
 * 根据流失记录编号查询流失记录信息
 * @author Administrator
 *
 */
public class FindOneCustomerLoss extends CustomerLossServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		//获取操作标志flag,如果不为空则向页面添加数据通知页面接下来的操作为确认流失
		Object flag = this.getValueFromDto("flag");
		if (flag != null) {
			this.addAttribute("flag", flag);
		}
		this.addAttribute("nowPage", this.getValueFromDto("nowPage"));
		return "kh10";
	}


}
