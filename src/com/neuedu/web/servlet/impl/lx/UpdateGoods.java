package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.GoodsServiceSupport;
/**
 * 根据商品编号修改商品信息
 * @author Administrator
 *
 */
public class UpdateGoods extends GoodsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("modify", "修改");
		this.simpleFindAllByPage();
		return "jc2";
	}

}
