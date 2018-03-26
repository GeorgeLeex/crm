package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.GoodsServiceSupport;
/**
 * 删除商品
 * @author Administrator
 *
 */
public class DeleteGoods extends GoodsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("delete", "删除");
		this.simpleFindAllByPage();
		return "jc2";
	}

}
