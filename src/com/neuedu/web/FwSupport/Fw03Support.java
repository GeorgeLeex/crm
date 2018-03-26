package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.Fw03Service;
import com.neuedu.web.support.ControllerSupport;

public abstract class Fw03Support extends ControllerSupport {

	public Fw03Support() {
		this.baseService=new Fw03Service();
	}
}
