package com.neuedu.system.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
    /*private static String driver;
    private static String url;
    private static String user;
    private static String password;*/
    /*static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        driver = resourceBundle.getString("driver");
        url = resourceBundle.getString("url");
        user = resourceBundle.getString("user");
        password = resourceBundle.getString("password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
	
	/**
	 * 数据库连接池对象
	 */
	private static DataSource ds;
	
	/**
	 * 类加载时创建数据库连接池对象
	 */
	static{
		//通过传入在c3p0-config中的默认配置创建
		ds = new ComboPooledDataSource();
	}
	/**
	 * 本地线程变量
	 */
	private final static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	private DBUtils(){}

	/**
	 * 获取数据源
	 * @return
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 获取当前线程中的数据库连接
	 * @return
	 * @throws SQLException
	 */
    public static Connection getConnection() throws SQLException {
        //获取当前线程中的数据库连接
        Connection connection = threadLocal.get();
        //判断是否存在
        if (connection == null || connection.isClosed()) {
            //将数据库连接与当前线程绑定
            //connection = DriverManager.getConnection(url, user, password);
        	//通过数据库连接池获取Connection对象
            connection = ds.getConnection();
        	threadLocal.set(connection);
        }
        return connection;
    }
    
    /**
     * 解除Connection对象与当前线程的绑定关系, 并关闭Connection对象
     */
    public static void Close() {
        try {
            //获取当前线程绑定的连接对象
            Connection connection = getConnection();
            //判断连接是否存在
            if (connection != null && !connection.isClosed()) {
                //System.out.println(connection);
                //解除与当前线程的绑定关系
                threadLocal.remove();
            }
            //System.out.println(connection);
            //关闭连接
            if (connection != null && !connection.isClosed()) {
            	connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * 获取prepareStatement连接对象
     * @param sql
     * @return
     * @throws Exception
     */
    public static PreparedStatement prepareStatement(String sql)throws Exception {
        return getConnection().prepareStatement(sql);
    }
    
    /**
     * 关闭自动提交事物
     * @throws Exception
     */
    public static void startTransaction() throws Exception{
        getConnection().setAutoCommit(false);
    }
    
    /**
     * 开启自动提交事物
     */
    public static void stopTransaction() {
        try {
            getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 手动提交事物
     * @throws Exception
     */
    public static void commit() throws Exception {
        getConnection().commit();;
    }
    
    /**
     * 回滚事物
     */
    public static void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭PreparedStatement对象
     * @param preparedStatement
     */
    public static void Close(PreparedStatement preparedStatement){
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                preparedStatement = null;
            }
        }
    }
    
    /**
     * 关闭ResultSet对象
     * @param resultSet
     */
    public static void Close(ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                resultSet = null;
            }
        }
    }
}
