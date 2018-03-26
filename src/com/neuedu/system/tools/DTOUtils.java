package com.neuedu.system.tools;
/**
 * 处理dto中的数据
 * @author Administrator
 *
 */
public class DTOUtils {
    private DTOUtils() {
    }
    /**
     * 把字符串数组转换成字符串
     * @param array
     * @return
     */
    public static String getStringFromArray(String[] array) {
        //如果数组为空或长度为0则返回 空字符串
        if (array == null || array.length == 0) {
            return "";
        } else {
            // 如果数组含有大于1个数据则将其转换为 以逗号为分隔符的字符串
            StringBuilder sb = new StringBuilder(array[0]);
            for (int i = 1; i < array.length; i++) {
                sb.append(",").append(array[i]);
            }
            return sb.toString();
        }
    }
}
