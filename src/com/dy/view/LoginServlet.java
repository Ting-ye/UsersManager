package com.dy.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<img src='images/8.gif' /><hr/><h1>�û���¼</h1>");
		out.println("<form action='/UsersManager2/LoginClServlet' method='post'>");
		out.println("�û�id:<input type='text' name='id' /><br/>");
		out.println("��    ��:<input type='password' name='password'/><br/>");
		out.println("<input type='submit' value='��¼'/><br/>");
		out.println("</form>");
		
		String errInfo=(String) request.getAttribute("err");
		if(errInfo!=null){
			out.println("<font color='red'>"+errInfo+"</font>");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
