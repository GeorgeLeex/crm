package com.neuedu.web.servlet.impl.lx.support;

import com.neuedu.services.impl.CommuRecordService;
import com.neuedu.web.support.ControllerSupport;
/**
 * 初始化BaseService为CommuRecordService
 * @author Administrator
 *
 */
public abstract class CommuRecordServiceSupport extends ControllerSupport {

	public CommuRecordServiceSupport() {
		this.baseService = new CommuRecordService();
	}

}
