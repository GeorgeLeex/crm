package com.neuedu.web.servlet.impl.lx;

import com.neuedu.web.servlet.impl.lx.support.StockServiceSupport;
/**
 * 分页查询所有的商品库存
 * @author Administrator
 *
 */
public class FindAllStock extends StockServiceSupport {

	@Override
	public String execute() throws Exception {
		this.simpleFindAllByPage();
		return "jc3";
	}

}
