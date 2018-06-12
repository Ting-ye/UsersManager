package com.dy.service;

import com.dy.domain.User;
import com.dy.util.SqlHelper;

import java.sql.*;
import java.util.ArrayList;

public class UserService {
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
