package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.GoodsServiceSupport;

/**
 * 添加商品
 * @author Administrator
 *
 */
public class AddGoods extends GoodsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleUpdate("add","添加");
		this.simpleFindAllByPage();
		return "jc2";
	}

}
