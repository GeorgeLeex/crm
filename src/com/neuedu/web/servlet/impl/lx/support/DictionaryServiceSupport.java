package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.DictionaryService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService接口为DictionaryService
 * @author Administrator
 *
 */
public abstract class DictionaryServiceSupport extends ControllerSupport {

	public DictionaryServiceSupport() {
		this.baseService = new DictionaryService();
	}

}
