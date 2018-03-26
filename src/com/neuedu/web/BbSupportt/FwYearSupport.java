package com.neuedu.web.BbSupportt;

import com.neuedu.services.bb.FwYearService;
import com.neuedu.web.support.ControllerSupport;

public abstract class FwYearSupport extends ControllerSupport {
	public FwYearSupport() {
	this.baseService =new  FwYearService();
	}
	
}
