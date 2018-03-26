package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.FwSelectService;
import com.neuedu.web.support.ControllerSupport;

public abstract class FwSupport extends ControllerSupport {

	public FwSupport() {
		this.baseService=new FwSelectService();
	}
}
