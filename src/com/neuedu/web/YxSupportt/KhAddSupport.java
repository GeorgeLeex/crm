package com.neuedu.web.YxSupportt;

import com.neuedu.services.yx.KhAddService;
import com.neuedu.web.support.ControllerSupport;

public abstract class KhAddSupport extends ControllerSupport {
	public KhAddSupport() {
		this.baseService=new KhAddService();
	}

}
