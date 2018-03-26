package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.services.impl.AutoCompleteKh02Service;
import com.neuedu.web.support.ControllerSupport;
/**
 * 查询客户名及编号
 * @author Administrator
 *
 */
public class AutoCompleteKh02 extends ControllerSupport{
	public AutoCompleteKh02() {
		this.baseService = new AutoCompleteKh02Service();
	}
	@Override
	public String execute() throws Exception {
		List<Map<String,Object>> list = this.baseService.queryForList();
		this.addResponseMessage("khs", list);
		return "response:";
	}

}
