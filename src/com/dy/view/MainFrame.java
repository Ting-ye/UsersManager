package com.dy.view;

import com.dy.domain.User;

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

//		取出session验证 利用session防盗链
	    User u=(User)request.getSession().getAttribute("loginUser");
	    //获取登录的用户名
	    String username= (String) request.getSession().getAttribute("username");
	    //获取网站访问次数
		String nums=this.getServletContext().getAttribute("nums").toString();
	    if(u==null){
			try {
				request.setAttribute("err","请输入用户名和密码");
				request.getRequestDispatcher("/LoginServlet").forward(request,response);
				return;//这句话很重要import，不然会继续往下走，加载登录页面
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}

//		输入验证码 登录时显示用户名功能
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();


		out.println("<img src='images/8.gif' />  欢迎  "+username+"  登录  <a href='/UsersManager/LoginServlet'>返回登录界面</a> 该管理页面被访问了 "+nums+" 次<hr/>");
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
