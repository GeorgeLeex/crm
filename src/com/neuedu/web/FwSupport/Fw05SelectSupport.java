package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.Fw05SelectService;
import com.neuedu.web.support.ControllerSupport;

public abstract class Fw05SelectSupport extends ControllerSupport {
	public Fw05SelectSupport() {
		this.baseService=new Fw05SelectService();
	}
}
