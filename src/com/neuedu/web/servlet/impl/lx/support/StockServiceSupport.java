package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.StockService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 实例化BaseService为StockService
 * @author Administrator
 *
 */
public abstract class StockServiceSupport extends ControllerSupport {

	public StockServiceSupport() {
		this.baseService = new StockService();
	}

}
