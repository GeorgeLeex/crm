package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.web.servlet.impl.lx.support.CommuRecordServiceSupport;
/**
 * 修改交往记录
 * @author Administrator
 *
 */
public class UpdateCommuRecord extends CommuRecordServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("modify", "修改");
		this.simpleFindAllByPage();
		//重查
		/*List<Map<String,Object>> list = this.baseService.queryForList();
		//如果list的size为1,表示查询到了客户交往记录
		if (list != null && list.size() == 1) {
			Map<String, Object> map = list.get(0);
			this.addAttribute("rows", map.get("rows"));
			this.addAttribute("nowPage", map.get("nowPage"));
			this.addAttribute("pageCount", map.get("pageCount"));
			this.addAttribute("rowCount", map.get("rowCount"));
			this.addAttribute("numbers", map.get("numbers"));
			//如果list的size为2,则表示没有查询到的客户的交往记录,然后查询了客户信息
		} else if (list.size() == 2) {
			//添加客户编号与客户名称至request域
			Map<String, Object> map = list.get(0);
			this.addAttribute("kh_no", map.get("kh_no"));
			this.addAttribute("kh02", map.get("kh02"));
		}*/
		return "kh4";
	}

}
