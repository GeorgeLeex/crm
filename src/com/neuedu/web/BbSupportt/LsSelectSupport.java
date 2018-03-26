package com.neuedu.web.BbSupportt;

import com.neuedu.services.bb.LsService;
import com.neuedu.web.support.ControllerSupport;

public abstract class LsSelectSupport extends ControllerSupport {
	public LsSelectSupport() {
		this.baseService=new LsService();
	}

}
