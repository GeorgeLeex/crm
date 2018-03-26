package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.CsService;
import com.neuedu.web.support.ControllerSupport;

public abstract class CsSupport extends ControllerSupport {
	public CsSupport() {
		this.baseService=new CsService();
	}
	
}
