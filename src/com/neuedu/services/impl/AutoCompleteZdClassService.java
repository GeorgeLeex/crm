package com.neuedu.services.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.services.support.JdbcSupport;
/**
 * 自动补全字典数据类别
 * @author Administrator
 *
 */
public class AutoCompleteZdClassService extends JdbcSupport {

	@Override
	public Map<String, Object> queryForMap() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList() throws Exception {
		return this.multipleQuery("SELECT distinct zd_class FROM t_zd WHERE zd_class LIKE ?", "%" + this.getVal("zd_class") + "%");
	}

	@Override
	public boolean update(String type) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
