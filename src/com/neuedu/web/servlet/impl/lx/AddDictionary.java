package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.DictionaryServiceSupport;
/**
 * 添加数据字典条目
 * @author Administrator
 *
 */
public class AddDictionary extends DictionaryServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add","添加");
		this.simpleFindAllByPage();
		return "jc1";
	}

}
