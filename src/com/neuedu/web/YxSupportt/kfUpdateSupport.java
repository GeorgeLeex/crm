package com.neuedu.web.YxSupportt;

import com.neuedu.services.yx.kfUpdateService;
import com.neuedu.web.support.ControllerSupport;

public abstract class kfUpdateSupport extends ControllerSupport {
	
	public kfUpdateSupport() {
		this.baseService=new kfUpdateService();
	}

}
