package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.dgp.GoodsServiceSupport;
/**
 * 查询所有的商品
 * @author Administrator
 *
 */
public class FindAllGoods extends GoodsServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAllByPage();
		return "jc2";
	}

}
