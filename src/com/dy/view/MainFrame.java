package com.dy.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainFrame extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {


		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();


		out.println("<img src='images/8.gif' />  欢迎  xxx  登录  <a href='/UsersManager/LoginServlet'>返回登录界面</a><hr/>");
		out.println("<h3>请选择您要进行的操作</h3>");
		out.println("<a href='/UsersManager/Managerusers'>管理用户</a><br/>");
		out.println("<a href='/UsersManager/UserClServlet?type=gotoAddVie'>添加用户</a><br/>");
		out.println("<a href='/UsersManager/UserClServlet?type=gotoQueryVie'>查找用户</a><br/>");
		out.println("<a href=''>退出系统</a><br/>");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		this.doGet(request, response);
	}

}
