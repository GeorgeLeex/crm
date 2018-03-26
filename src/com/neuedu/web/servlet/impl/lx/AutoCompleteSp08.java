package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.services.impl.AutoCompleteSp08Service;
import com.neuedu.web.support.ControllerSupport;
/**
 * 查询商品名及编号
 * @author Administrator
 *
 */
public class AutoCompleteSp08 extends ControllerSupport{
	public AutoCompleteSp08() {
		this.baseService = new AutoCompleteSp08Service();
	}
	@Override
	public String execute() throws Exception {
		List<Map<String,Object>> list = this.baseService.queryForList();
		this.addResponseMessage("sps", list);
		return "response:";
	}

}
