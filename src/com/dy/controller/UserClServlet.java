package com.dy.controller;

import com.dy.domain.User;
import com.dy.service.UserService;
import org.omg.CORBA.UserException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserClServlet")
public class UserClServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String type=request.getParameter("type");
        UserService userService=new UserService();

        if("del".equals(type)) {
            String id=request.getParameter("id");
            if (new UserService().delUsers(id)) {
                request.setAttribute("info","删除成功");
                request.getRequestDispatcher("/Ok").forward(request, response);
            } else {
                request.setAttribute("info","删除失败");
                request.getRequestDispatcher("/Err").forward(request, response);
            }
        }else if("gotoUpView".equals(type)){
            String id=request.getParameter("id");
            User user=userService.getUserById(id);
            //为了让写一个servlet收到user对象，把user对象放入request对象中
            request.setAttribute("userInfo",user);
            request.getRequestDispatcher("/UpdateUserVie").forward(request,response);
        }else if("update".equals(type))
        {
            String id=request.getParameter("id");
            String username=request.getParameter("username");
            String email=request.getParameter("email");
            String grade=request.getParameter("grade");
            String password=request.getParameter("password");

            //修改用户
            User user=new User(Integer.parseInt(id),username,email,grade,password);
            if(userService.updUser(user)){
                request.setAttribute("info","修改成功");
                request.getRequestDispatcher("/Ok").forward(request, response);
            }else{
                request.setAttribute("info","修改失败");
                request.getRequestDispatcher("/Err").forward(request, response);
            }
        }else if("gotoAddVie".equals(type)){
            request.getRequestDispatcher("/AddUsersView").forward(request,response);
        }else if("add".equals(type)){

            String username=request.getParameter("username");
            String email=request.getParameter("email");
            String grade=request.getParameter("grade");
            String password=request.getParameter("password");
            User user=new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setGrade(grade);
            user.setPassword(password);

            if(userService.addUser(user)){
                request.setAttribute("info","添加成功");
                request.getRequestDispatcher("/Ok").forward(request, response);
            }else{
                request.setAttribute("info","添加失败");
                request.getRequestDispatcher("/Err").forward(request, response);
            }
        }else if("gotoQueryVie".equals(type)){
            request.getRequestDispatcher("/QueryUsersView").forward(request,response);
        }else if("query".equals(type)){
            String queryWay=request.getParameter("queryWay");
            String findName=request.getParameter("findName");
            if(queryWay.equals("exactQuery")){
                User user=userService.exactQuery(findName);
                request.setAttribute("queryUserInfo",user);
                request.getRequestDispatcher("/QueryUsersView").forward(request,response);
            }else if(queryWay.equals("exactQuery")){

            }else{
                out.println("请选择查询方式！！！");
            }
        }
    }
}
