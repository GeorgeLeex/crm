package com.neuedu.web.servlet.impl.dgp;

import com.neuedu.services.impl.GoodsService;
import com.neuedu.web.support.ControllerSupport;

public abstract class GoodsServiceSupport extends ControllerSupport {

	public GoodsServiceSupport() {
		this.baseService = new GoodsService();
	}
}
