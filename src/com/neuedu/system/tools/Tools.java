package com.neuedu.system.tools;

import com.neuedu.system.db.DBUtils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * 为整个系统提供服务的工具类
 * @author Administrator
 *
 */
public class Tools {
    private Tools() {
    }

    
    /*****************小数处理*******************/
    private final static int MATCH_SCALE=2;         //四舍五入默认小数位数
    /**
     * 以下四个方法为精度转换方法
     * @param dol double
     * @param scale int
     * @return String
     */
    public static double ObjToDouble(Object dol, int scale)
    {
        return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
    }
    public static double ObjToDouble(Object dol)
    {
        return Tools.ObjToDouble(dol, MATCH_SCALE);
    }

    public static String DoubleToStr(double dol, int scale)
    {
        return Tools.ObjectToBigDecimal(dol, scale).toString();
    }
    public static String DoubleToStr(double dol)
    {
        return Tools.DoubleToStr(dol, MATCH_SCALE);
    }

    public static double DoubleToDouble(double dol, int scale)
    {
        return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
    }
    public static double DoubleToDouble(double dol)
    {
        return Tools.DoubleToDouble(dol,  MATCH_SCALE);
    }

    public static double StrToDouble(String dol, int scale)
    {
        return Tools.ObjectToBigDecimal(dol, scale).doubleValue();
    }
    public static double StrToDouble(String dol)
    {
        return Tools.StrToDouble(dol, MATCH_SCALE);
    }

    public static String StrToStr(String dol, int scale)
    {
        return Tools.ObjectToBigDecimal(dol, scale).toString();
    }
    public static String StrToStr(String dol)
    {
        return Tools.StrToStr(dol,MATCH_SCALE);
    }

    /**
     * 四舍五入基础方法
     * @param dol    ---  数值
     * @param scale  ---  小数位数(精度)
     * @return
     */
    private static BigDecimal ObjectToBigDecimal(Object dol, int scale)
    {
        //定义货币类型变量
        BigDecimal decimal=null;
        //判断dol如果是空或null,返回0
        if(dol==null || dol.equals(""))
        {
            return new BigDecimal(0);
        }
        //基于dol完成BigDecimal的实例化
        decimal = new BigDecimal(dol.toString());
        //按照精度四舍五入
        decimal = decimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return decimal;
    }
    /*****************小数处理*******************/
    //默认MD5加密密码--- 0000
    public static final String defaultMD5Code = "4a7d1ed414474e4033ac29ccb8653d9b";

    /*******************MD5加密*******************/
    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b)
    {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
        {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 转换字节为16进制字符串
     * @param b byte
     * @return String
     */
    private static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 得到MD5的秘文密码
     * @param origin String
     * @throws Exception
     * @return String
     */
    public static String MD5Encode(String origin) throws Exception
    {
        String resultString = null;
        try
        {
            resultString=new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
            return resultString;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    /*******************MD5加密*******************/


    /**
     *  从sequence表中获取序列id
     * @param sequenceName 序列名
     * @throws Exception
     */
    public static int getSequenceId (String sequenceName) throws Exception {
        PreparedStatement p1 = null;
        PreparedStatement p2 = null;
        ResultSet rs = null;
        try {
            String sql1 = "SELECT svalue FROM sequence WHERE sname = ?";
            p1 = DBUtils.prepareStatement(sql1);
            p1.setObject(1, sequenceName);
            rs = p1.executeQuery();
            int sequenceId = 0;
            String sql2 = "";
            if (rs.next()) {
                sequenceId = rs.getInt(1);
                sql2 = "UPDATE sequence SET svalue = ? WHERE sname = ?";
            } else {
            	//MySQL
                //sql2 = "INSERT INTO sequence (svalue, sname, stime) VALUES(?, ?, CURRENT_DATE)";
            	//oracle
            	sql2 = "INSERT INTO sequence (sno, svalue, sname) VALUES(seq_sequence_sno.nextval,?, ?)";
            }
            p2 = DBUtils.prepareStatement(sql2);
            p2.setObject(1, ++sequenceId);
            p2.setObject(2, sequenceName);
            p2.executeUpdate();
            return sequenceId;
        } finally {
            DBUtils.Close(rs);
            DBUtils.Close(p2);
            DBUtils.Close(p1);
        }
    }
    
}
