package com.neuedu.web.BbSupportt;

import com.neuedu.services.bb.PxService;
import com.neuedu.services.bb.XyService;
import com.neuedu.web.support.ControllerSupport;

public abstract class XySupport extends ControllerSupport {
	public XySupport() {
		this.baseService=new XyService();
	}
}
