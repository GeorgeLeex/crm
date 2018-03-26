package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.DictionaryServiceSupport;
/**
 * 查询所有的数据字典
 * @author Administrator
 *
 */
public class FindAllDictionary extends DictionaryServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAllByPage();
		return "jc1";
	}

}
