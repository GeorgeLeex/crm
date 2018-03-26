package com.neuedu.web.BbSupportt;

import com.neuedu.services.bb.FwSelectService;
import com.neuedu.web.support.ControllerSupport;

public abstract class FwSelectSupport extends ControllerSupport {

	public FwSelectSupport() {
		this.baseService=new FwSelectService();
	}
}
