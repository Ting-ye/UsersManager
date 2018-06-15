package com.dy.view;


import com.dy.domain.User;
import com.dy.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Managerusers extends HttpServlet {
	
	


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();	
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function gotoPage(){" +
				"var pageNow=document.getElementById('pageNow');"+
		"window.open('/Managerusers?pageNow='+pageNow.value,'_self');}" +
				"function confirmOper(){ window.confirm('是否确认删除？');}");
		out.println("</script>");
		out.println("<h2>管理用户</h2>");

		int pageNow=1;//当前页面
		int pageCount=0;
		int pageSize=3;//每页数据量

		String sPageNow=request.getParameter("pageNow");
		if(sPageNow!=null){
		pageNow=Integer.parseInt(sPageNow);
		}

		try {
			UserService userService=new UserService();

			pageCount=userService.getPageCount(pageSize);
			ArrayList<User> al=userService.getUsersByPage(pageNow,pageSize);

			out.println("<img src='images/8.gif' />  欢迎  'u.getUsername()'  登录  <a href='/UsersManager/MainFrame'>返回主界面</a>  <a href='/UsersManager/MainFrame'>安全退出</a><hr/>");
			out.println("<table border=1 bordercolor=blue cellspacing=0 width=500px>");
			out.println("<tr><th>id</th><th>用户名</th><th>e-mail</th><th>级别</th><th>删除用户</th><th>修改用户</th></tr>");
			for(User u:al) {
				out.println("<tr><td>" + u.getId()
						+ "</td><td>" + u.getUsername()
						+ "</td> <td>" + u.getEmail()
						+ "</td> <td>" + u.getGrade()
						+ "</td><td><a  onClick='return confirmOper();' href='/UsersManager/UserClServlet?type=del&id="+u.getId()+"'>删除用户</a></td>"
						+"<td><a href='/UsersManager/UserClServlet?type=gotoUpView&id="+u.getId()+"'>修改用户</a></td></tr>");
			}
			out.println("</table>");
			if(pageNow>1){
			out.println("<a href='/UsersManager/Managerusers?pageNow="+(pageNow-1)+"'>上一页</a>");
			}
			for(int i=1;i<=pageCount;i++){	
				out.println("<a href='/UsersManager/Managerusers?pageNow="+i+"'><"+i+"></a>");
			}
			if(pageNow<pageCount){
			out.println("<a href='/UsersManager/Managerusers?pageNow="+(pageNow+1)+"'>下一页</a>");
				}
			out.println("&nbsp;&nbsp;&nbsp;当前页"+pageNow+"/总"+pageCount+"页"+"<br/>");
			out.println("跳转到第 <input type='text' name='pageNow' id='pageNow' style='width:40px'/>"+"页 <input type='button' value='跳转' onClick='gotoPage()'>");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
