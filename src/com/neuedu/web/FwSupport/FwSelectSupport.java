package com.neuedu.web.FwSupport;

import com.neuedu.services.bb.BbSelectService;
import com.neuedu.web.support.ControllerSupport;

public abstract class FwSelectSupport extends ControllerSupport {
	public FwSelectSupport() {
		this.baseService=new BbSelectService();
	}


}
