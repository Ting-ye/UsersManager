package com.dy.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainFrame extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<img src='images/8.gif' />  ��ӭ  xx  ��¼  <a href='/UsersManager2/LoginServlet'>���ص�¼����</a><hr/>");
		
		out.println("<h3>��ѡ����Ҫ���еĲ���</h3>");
		out.println("<a href='/UsersManager2/Managerusers'>�����û�</a><br/>");
		out.println("<a href=''>����û�</a><br/>");
		out.println("<a href=''>�����û�</a><br/>");
		out.println("<a href=''>�˳�ϵͳ</a><br/>");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
