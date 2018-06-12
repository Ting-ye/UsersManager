package com.dy.service;

import com.dy.domain.User;
import com.dy.util.SqlHelper;

import java.sql.*;
import java.util.ArrayList;

public class UserService {
    //删除用户
    public boolean delUsers(String id){
        boolean b=true;
        String sql="delete from users where id=?";
        String parameters[]={id};
        try{
            SqlHelper.executeUpdate(sql,parameters);
        }catch (Exception e){
            b=false;
            e.printStackTrace();
        }
        return b;
    }

    //获取数据总页数
    public int getPageCount(int pageSize){

        int rowCount=0;
        String sql="select count(*) from users";
        ResultSet rs=SqlHelper.executeQuery(sql,null);
        try {
            rs.next();
            rowCount=rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
        }
        return (rowCount-1)/pageSize+1;
    }

    //按照分页来获取用户
    public ArrayList getUsersByPage(int pageNow ,int pageSize){

        ArrayList<User> al=new ArrayList<User>();

        String sql="select * from users limit "+pageSize*(pageNow-1)+","+ pageSize;

        ResultSet rs=SqlHelper.executeQuery(sql,null);
        //二次封装
        try{
            while(rs.next()){
                User u=new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setGrade(rs.getString(5));
                al.add(u);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
        }

        return al;
    }


    public boolean checkUser(User user){
        boolean b=false;

        String sql="select * from users where id=? and password=?";
        String parameters[]={user.getId()+"",user.getPassword()};
        ResultSet rs=SqlHelper.executeQuery(sql,parameters);
        try {
            if(rs.next()){
                b=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
        }
        return  b;
    }
}
