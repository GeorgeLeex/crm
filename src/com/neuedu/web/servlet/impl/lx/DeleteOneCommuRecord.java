package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CommuRecordServiceSupport;
/**
 * 根据交往记录编号删除单个交往记录
 * @author Administrator
 * @time 2018年3月10日 下午6:38:54
 */
public class DeleteOneCommuRecord extends CommuRecordServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("delete", "删除");
		this.simpleFindAllByPage();
		return "kh4";
	}

}
