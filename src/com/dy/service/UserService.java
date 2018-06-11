package com.dy.service;

import com.dy.domain.User;
import com.dy.util.SqlHelper;

import java.sql.*;

public class UserService {
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
