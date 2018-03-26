package com.neuedu.web.BbSupportt;

import com.neuedu.services.bb.BbSelectService;
import com.neuedu.web.support.ControllerSupport;

public abstract class BbSelectSupport extends ControllerSupport {
	public BbSelectSupport() {
		this.baseService=new BbSelectService();
	}


}
