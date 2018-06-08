package com.dy.view;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		"window.open('/Managerusers?pageNow='+pageNow.value,'_self');}");
		out.println("</script>");
		out.println("<h2>管理用户</h2>");
		
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		
		
		int pageNow=1;//当前页面
		
		String sPageNow=request.getParameter("pageNow");
		if(sPageNow!=null){
		pageNow=Integer.parseInt(sPageNow);
		}
		
		int pageSize=3;//每页数据量
		int pageCount=1;//页数
		int rowCount=1;//数据总数
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servletest?characterEncoding=utf-8","root","123456");
			//得到页数
			ps=conn.prepareStatement(" select count(*) from users ");
			rs=ps.executeQuery();
			rs.next();
			rowCount=rs.getInt(1);
			pageCount= rowCount%pageSize==0?rowCount/pageSize:rowCount/pageSize+1;
			
			ps=conn.prepareStatement("select * from users limit "+pageSize*(pageNow-1)+","+ pageSize+"");
			rs=ps.executeQuery();
			out.println("<img src='images/8.gif' />  欢迎  xx  登录  <a href='/UsersManager/MainFrame'>返回主界面</a>  <a href='/UsersManager/MainFrame'>安全退出</a><hr/>");
			out.println("<table border=1 bordercolor=blue cellspacing=0 width=500px>");
			out.println("<tr><th>id</th><th>用户名</th><th>e-mail</th><th>级别</th></tr>");
			while(rs.next()){
				out.println("<tr><td>"+rs.getInt(1)
						+"</td><td>"+rs.getString(2)
						+"</td> <td>"+rs.getString(3)
						+"</td> <td>"+rs.getInt(4)
						+"</td></tr>");
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
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rs=null;
			}
				
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ps=null;
			}
			
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn=null;
			}
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
}
