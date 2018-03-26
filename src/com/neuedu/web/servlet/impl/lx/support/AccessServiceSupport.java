package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.AccessService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService为AccessService
 * @author Administrator
 *
 */
public abstract class AccessServiceSupport extends ControllerSupport {

	public AccessServiceSupport() {
		this.baseService = new AccessService();
	}

}
