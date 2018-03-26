package com.neuedu.web.YxSupportt;

import com.neuedu.services.yx.UpdateSelectService;
import com.neuedu.web.support.ControllerSupport;

public abstract class UpdateSelectServiceSupport extends ControllerSupport {

public UpdateSelectServiceSupport() {
	this.baseService=new UpdateSelectService();
}
}
