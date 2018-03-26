package com.neuedu.web.servlet.impl.dgp;

import java.util.Map;

import com.neuedu.web.YxSupportt.UpdateSelectServiceSupport;
import com.neuedu.web.YxSupportt.YxServiceSupport;

/**
 * 进入编辑页面 查询数据,相应编号对应的数据显示在页面
 * @author Administrator
 *
 */
public class UpdateSelectServlet extends UpdateSelectServiceSupport{
	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		return "yx07";
	}

}
