package com.neuedu.web.BbSupportt;

import com.neuedu.services.bb.PxService;
import com.neuedu.web.support.ControllerSupport;

public abstract class PxSupport extends ControllerSupport {
	public PxSupport() {
		this.baseService=new PxService();
	}
}
