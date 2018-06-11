package com.dy.controller;

import com.dy.domain.User;
import com.dy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginClServlet extends HttpServlet {

	


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("utf-8");

		String id=request.getParameter("id");
		String password=request.getParameter("password");

		//创建
		UserService userService=new UserService();
		User user=new User();
		user.setId(Integer.parseInt(id));
		user.setPassword(password);

		if(userService.checkUser(user)){
            try {
                request.getRequestDispatcher("/MainFrame").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }else{
				request.setAttribute("err","用户id 或   密码有误");
            try {
                request.getRequestDispatcher("/LoginServlet").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }


	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
