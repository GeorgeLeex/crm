package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CommuRecordServiceSupport;
/**
 * 根据交往记录编号查询交往记录信息
 * @author Administrator
 *
 */
public class FindOneCommuRecord extends CommuRecordServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		this.addAttribute("nowPage", this.getValueFromDto("nowPage"));
		return "kh5";
	}

}
