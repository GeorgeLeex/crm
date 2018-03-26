package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.GoodsLocationService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService为GoodsLocationService
 * @author Administrator
 *
 */
public abstract class GoodsLocationServiceSupport extends ControllerSupport{

	public GoodsLocationServiceSupport() {
		this.baseService = new GoodsLocationService();
	}

}
