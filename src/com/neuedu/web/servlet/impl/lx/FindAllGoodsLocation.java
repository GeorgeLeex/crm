package com.neuedu.web.servlet.impl.lx;

import java.util.List;
import java.util.Map;

import com.neuedu.web.servlet.impl.lx.support.GoodsLocationServiceSupport;
/**
 * 查询所有的货位
 * @author Administrator
 *
 */
public class FindAllGoodsLocation extends GoodsLocationServiceSupport {

	@Override
	public String execute() throws Exception {
		List<Map<String,Object>> list = this.baseService.queryForList();
		this.addResponseMessage("hws", list);
		return "response:";
	}

}
