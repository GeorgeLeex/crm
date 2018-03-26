package com.neuedu.web.YxSupportt;

import com.neuedu.services.yx.MhService;
import com.neuedu.web.support.ControllerSupport;

public abstract class MhServiceSupport extends ControllerSupport {

	public MhServiceSupport() {
		this.baseService=new MhService();
	}
	
}
