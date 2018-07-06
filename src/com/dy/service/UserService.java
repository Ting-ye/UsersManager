package com.dy.service;

import com.dy.domain.User;
import com.dy.util.SqlHelper;

import java.sql.*;
import java.util.ArrayList;

public class UserService {
    //删除用户
    public boolean delUsers(String id) {
        boolean b = true;
        String sql = "delete from users where id=?";
        String parameters[] = {id};
        try {
            SqlHelper.executeUpdate(sql, parameters);
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    //获取数据总页数
    public int getPageCount(int pageSize) {

        int rowCount = 0;
        String sql = "select count(*) from users";
        ResultSet rs = SqlHelper.executeQuery(sql, null);
        try {
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return (rowCount - 1) / pageSize + 1;
    }

    //按照分页来获取用户
    public ArrayList getUsersByPage(int pageNow, int pageSize) {

        ArrayList<User> al = new ArrayList<User>();

        String sql = "select * from users limit " + pageSize * (pageNow - 1) + "," + pageSize;

        ResultSet rs = SqlHelper.executeQuery(sql, null);
        //二次封装
        try {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setGrade(rs.getString(5));
                al.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }

        return al;
    }

    //通过ID和密码验证用户
    public boolean checkUser(User user) {
        boolean b = false;

        String sql = "select * from users where id=? and password=?";
        String parameters[] = {user.getId() + "", user.getPassword()};
        ResultSet rs = SqlHelper.executeQuery(sql, parameters);
        try {
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return b;
    }

    //通过id获取用户数据
    public User getUserById(String id) {
        String sql = "select * from users where id=?";
        String parameters[] = {id};
        ResultSet rs = SqlHelper.executeQuery(sql, parameters);
        User user = new User();
        try {
            if (rs.next()) {

                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setGrade(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return user;
    }

    //修改用户
    public boolean updUser(User user) {
        boolean b = true;
        String sql = "update users set username=? , email=? , grade=? , password=? where id=?";
        String parameters[] = {user.getUsername(), user.getEmail(), user.getGrade(), user.getPassword(), user.getId() + ""};
        try {
            SqlHelper.executeUpdate(sql, parameters);
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    //添加用户
    public boolean addUser(User user) {
        boolean b = true;
        String sql = "insert into users(username,email,grade,password)values(?,?,?,?)";
        String parameters[] = {user.getUsername(), user.getEmail(), user.getGrade(), user.getPassword()};


        try {
            SqlHelper.executeUpdate(sql, parameters);
        } catch (Exception e) {
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    //精确查询
    public User exactQuery(String username) {

        String sql = "select * from users where username=?";
        String parameters[] = {username};
        ResultSet rs = SqlHelper.executeQuery(sql, parameters);
        User u = new User();
        //二次封装
        try {
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setGrade(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return u;
    }

    //模糊查询
    public ArrayList<User> fuzzyQuery(String username)  {
        ArrayList<User> al=new ArrayList<User>();
        String sql = "SELECT * FROM users WHERE username LIKE ?";
        String parameters[] = {"%"+username+"%"};
        ResultSet rs = SqlHelper.executeQuery(sql, parameters);
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setGrade(rs.getString(4));
                al.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
        }
        return al;
    }
}

























