package com.dy.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SqlHelper {
    //定义需要的变量
    private static Connection ct=null;
    private static ResultSet rs=null;
    private static PreparedStatement ps=null;
    //CallableStatement接口主要用于调用存储过程
    private static CallableStatement cs=null;

    //连接数据库参数
    private static String url="";
    private static String username="";
    private static String password="";
    private static String driver="";

    //读取配置文件
    private static Properties pp=null;
    private static InputStream fis=null;

    static{
        try{
            pp=new Properties();
            //fis=new FileInputStream("dbinfo.properties");这样写配置文件要放在tomcat 的bin目录下
            //文件在src下用类加载器来读取
            fis=SqlHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
            pp.load(fis);

            url=pp.getProperty("url");
            username=pp.getProperty("username");
            password=pp.getProperty("password");
            driver=pp.getProperty("driver");
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fis=null;
        }
    }

    public static Connection getConnection(){
        try {
            ct= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println("数据库链接失败！");
            e.printStackTrace();
        }
        return ct;
    }

    public static void close(ResultSet rs,Statement ps, Connection con){

        if(rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static ResultSet
    executeQuery(String sql,String[] parameters)
    {
        try
        {
            ct=getConnection();
            ps=ct.prepareStatement(sql);
            if(parameters!=null)
            {
                for(int i=0;i<parameters.length;i++)
                {
                    ps.setString(i+1,parameters[i]);
                }
            }
            rs = ps.executeQuery();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        finally
        {

        }
        return rs;
    }

    //先写一个update、delete、insert
    //sql格式：update 表名 set 字段名 =？where 字段=？
    //parameter神应该是（”abc“,23）
    public static void executeUpdate(String sql,String[] parameters)
    {
        try
        {
            ct=getConnection();
            ps = ct.prepareStatement(sql);
            if(parameters!=null)
            {
                for(int i=0;i<parameters.length;i++)
                {
                    ps.setString(i+1,parameters[i]);
                }

            }
           ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();//开发阶段
            //抛出异常
            //可以处理，也可以不处理
            throw new RuntimeException(e.getMessage());
        }
        finally
        {
            close(rs,ps,ct);
        }
    }

    public static Connection getCt()
    {
        return ct;
    }

    public static PreparedStatement getPs()
    {
        return ps;
    }

    public static ResultSet getRs()
    {
        return rs;
    }


}


























