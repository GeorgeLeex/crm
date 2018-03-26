package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.web.servlet.impl.lx.support.ContactsServiceSupport;
/**
 * 根据用户编号查找其下所有的联系人信息
 * @author Administrator
 * @time 2018年3月10日 上午11:51:58
 */
public class FindAllContacts extends ContactsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAll();
		/*//如果有数据,则将结果集合添加至request域
		if (list != null && list.size() > 0) {
			this.addAttribute("rows", list);
		} else {
		//如果没有数据,则获取查询出的客户信息
			Map<String, Object> map = list.get(0);
			this.addAttribute("kh_no", map.get("kh_no"));
			this.addAttribute("kh02", map.get("kh02"));
		}
		*/
		return "kh1_2";
	}

}
