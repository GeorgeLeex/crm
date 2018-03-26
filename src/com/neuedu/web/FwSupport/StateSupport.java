package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.StateService;
import com.neuedu.web.support.ControllerSupport;

public abstract class StateSupport extends ControllerSupport {
	public StateSupport() {
		this.baseService=new StateService();
	}
}
