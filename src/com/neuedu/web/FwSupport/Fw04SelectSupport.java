package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.Fw04SelectService;
import com.neuedu.web.support.ControllerSupport;

public abstract class Fw04SelectSupport extends ControllerSupport {

	public Fw04SelectSupport() {
		this.baseService=new Fw04SelectService();
	}
}
