package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.services.impl.AutoCompleteZdClassService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 查询数据字典条目类别
 * @author Administrator
 *
 */
public class AutoCompleteZdClass extends ControllerSupport{
	public AutoCompleteZdClass() {
		this.baseService = new AutoCompleteZdClassService();
	}

	@Override
	public String execute() throws Exception {
		List<Map<String,Object>> list = this.baseService.queryForList();
		System.out.println(list);
		this.addResponseMessage("zds", list);
		return "response:";
	}
	
	
}
