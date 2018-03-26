package com.neuedu.web.servlet.impl.dgp;

import java.util.List;
import java.util.Map;

import com.neuedu.web.YxSupportt.YxServiceSupport;

public class YxSelectServlet extends YxServiceSupport {
	
	/**
	 * 继承父类得到dto数据传输对象,而父类创建了BaseService的之类,共享了dto数据到JdbcSupport类,使JdbcSUPPORT
	 * 子类也可以获取到dto数据
	 */
	@Override
	public String execute() throws Exception {
		 this.simpleFindAll();
		 return "yx01";
	}

}
