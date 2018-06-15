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

            System.out.println(id);
            //修改用户
            User user=new User(Integer.parseInt(id),username,email,grade,password);
            if(userService.updUser(user)){
                request.setAttribute("info","修改成功");
                request.getRequestDispatcher("/Ok").forward(request, response);
            }else{
                request.setAttribute("info","修改失败");
                request.getRequestDispatcher("/Err").forward(request, response);
            }

        }
    }
}
