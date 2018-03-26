package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.GoodsService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService为GoodsService
 * @author Administrator
 *
 */
public abstract class GoodsServiceSupport extends ControllerSupport {

	public GoodsServiceSupport() {
		this.baseService = new GoodsService();
	}
}
