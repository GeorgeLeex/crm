package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.Fw04Service;
import com.neuedu.web.support.ControllerSupport;

public abstract class Fw04Support extends ControllerSupport {
	 public Fw04Support() {
		 this.baseService=new Fw04Service();
	 } 
	
	

}
