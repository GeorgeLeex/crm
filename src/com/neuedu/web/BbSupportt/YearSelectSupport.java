package com.neuedu.web.BbSupportt;

import com.neuedu.services.bb.YearSelectService;
import com.neuedu.web.support.ControllerSupport;

public abstract class YearSelectSupport extends ControllerSupport {

	public YearSelectSupport() {
		this.baseService=new YearSelectService();
	}
}
