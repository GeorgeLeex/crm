package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.DictionaryServiceSupport;
/**
 * 根据数据字典编号修改数据字典
 * @author Administrator
 *
 */
public class UpdateDictionary extends DictionaryServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("modify", "修改");
		this.simpleFindAllByPage();
		return "jc1";
	}

}
