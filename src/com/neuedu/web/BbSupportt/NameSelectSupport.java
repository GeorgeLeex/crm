package com.neuedu.web.BbSupportt;

import com.neuedu.services.bb.NameSelectService;
import com.neuedu.web.support.ControllerSupport;

public abstract class NameSelectSupport extends ControllerSupport {
	public NameSelectSupport() {
		this.baseService=new NameSelectService();
	}
}
