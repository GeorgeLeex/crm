package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.GoodsServiceSupport;
/**
 * 根据商品编号查询单个商品
 * @author Administrator
 *
 */
public class FindOneGoods extends GoodsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindOne();
		this.addAttribute("nowPage", this.getValueFromDto("nowPage"));
		return "jc2_1";
	}

}
