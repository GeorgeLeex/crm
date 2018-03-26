package com.neuedu.web.YxSupportt;

import com.neuedu.services.yx.YxService;
import com.neuedu.web.servlet.impl.dgp.YxSelectServlet;
import com.neuedu.web.support.ControllerSupport;

public abstract class YxServiceSupport  extends ControllerSupport{
	public YxServiceSupport() {
		this.baseService=new YxService();
	}
/**自上而下的继承关系
 * baseController  interface                      BaseService interface
 * ControllerSupport   创建了BaseService对象             JDBCSupport 封装了c3p0,解析dto,事物托管之类的方法
 * YxServicesSupport   实例化了BaseService的实现类   XxxService   与数据库交互的服务层
 * XxxServlet         返回url路径
 * 
 */
}
