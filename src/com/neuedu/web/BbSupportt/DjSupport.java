package com.neuedu.web.BbSupportt;

import com.neuedu.services.bb.DjService;
import com.neuedu.services.bb.PxService;
import com.neuedu.web.support.ControllerSupport;

public  abstract class DjSupport extends ControllerSupport {

	public DjSupport() {
		this.baseService=new DjService();
	}
}
