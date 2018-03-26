package com.neuedu.system.tools;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConvertUtils {

	private ConvertUtils(){}
	
	/**
	 * 将List<Map<String,Object>> 转化成 key1:value1,key2:value2 的格式
	 * 适用于下拉菜单数据的转换, 将查询结果的两列转换为 key:value 的字符串格式
	 * @param mapList
	 * @param keyName
	 * @param valueName
	 * @return
	 */
	public static String convertMapListToString(List<Map<String, Object>> mapList, String keyName, String valueName){
		StringBuilder sb = new StringBuilder();
		if (mapList != null && mapList.size() > 0) {
			int size = mapList.size();
			for (int i = 0; i < size; i++) {
				Map<String, Object> map = mapList.get(i);
				sb.append(map.get(keyName));
				sb.append(":");
				sb.append(map.get(valueName));
				if (i == size - 1) {
					break;
				}
				sb.append(",");
			}
		}
		return sb.toString();
	}
}
