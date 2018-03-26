package com.neuedu.web.FwSupport;

import com.neuedu.services.FW.TypeService;
import com.neuedu.web.support.ControllerSupport;

public abstract class TypeSupport extends ControllerSupport {
	 public TypeSupport() {
		 this.baseService=new TypeService();
	 }
}
