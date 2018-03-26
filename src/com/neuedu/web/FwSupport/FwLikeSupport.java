package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.FwLikeService;
import com.neuedu.web.support.ControllerSupport;

public abstract class FwLikeSupport extends ControllerSupport {
	public FwLikeSupport() {
		this.baseService=new FwLikeService();
	}

}
