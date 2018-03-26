package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.Fw05Service;
import com.neuedu.web.support.ControllerSupport;

public abstract class Fw05Support extends ControllerSupport {
	 public Fw05Support() {
		 this.baseService=new Fw05Service();
	 }

}
