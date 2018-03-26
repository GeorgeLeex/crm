package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.CustomerServiceSupport;
/**
 * 用户信息的更新
 * @author Administrator
 * @time 2018年3月9日 下午9:34:17
 */
public class UpdateCustomer extends CustomerServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("modify", "修改");;
		//更新操作后数据重查
		this.simpleFindAllByPage();
		return "kh1_3";
	}

}
