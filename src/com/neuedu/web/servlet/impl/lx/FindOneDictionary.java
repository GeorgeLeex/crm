package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.DictionaryServiceSupport;
/**
 * 根据数据字典编号查询单条数据字典的信息
 * @author Administrator
 *
 */
public class FindOneDictionary extends DictionaryServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		return "jc1_2";
	}

}
