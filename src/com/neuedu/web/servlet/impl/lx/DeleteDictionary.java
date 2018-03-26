package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.DictionaryServiceSupport;
/**
 * 根据数据字典编号删除数据字典条目
 * @author Administrator
 *
 */
public class DeleteDictionary extends DictionaryServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("delete","删除");
		this.simpleFindAllByPage();
		return "jc1";
	}

}
