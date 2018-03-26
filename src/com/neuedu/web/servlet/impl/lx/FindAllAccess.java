package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.web.servlet.impl.lx.support.AccessServiceSupport;
/**
 * 查询所有的一二级菜单信息
 * @author Administrator
 *
 */
public class FindAllAccess extends AccessServiceSupport {

	@Override
	public String execute() throws Exception {
		List<Map<String, Object>> all = this.baseService.queryForList();
    	Map<String, Object> map = all.get(0);
		if (map != null && map.size() > 0) {
			this.addAttribute("rows", map.get("rows"));
			this.addAttribute("nowPage", map.get("nowPage"));
			this.addAttribute("pageCount", map.get("pageCount"));
			this.addAttribute("rowCount", map.get("rowCount"));
			this.addAttribute("numbers", map.get("numbers"));
			this.putSessionAttributes("fs", map.get("fs"));
		}
		return "us3";
	}

}
