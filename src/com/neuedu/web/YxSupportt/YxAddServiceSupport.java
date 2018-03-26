package com.neuedu.web.YxSupportt;

import com.neuedu.services.yx.YxAddService;
import com.neuedu.web.support.ControllerSupport;

public abstract class YxAddServiceSupport extends ControllerSupport {
public YxAddServiceSupport() {
	this.baseService=new YxAddService();
	
}

}
