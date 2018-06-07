package com.dy.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginClServlet extends HttpServlet {

	


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servletest?characterEncoding=utf-8","root","123456");
			ps=conn.prepareStatement("select * from users where id=? and password=?");
			ps.setObject(1, id);
			ps.setObject(2, password);
			rs=ps.executeQuery();
			
			if(rs.next()){
				request.getRequestDispatcher("/MainFrame").forward(request, response);
			}else{
				request.setAttribute("err","用户id 或   密码有误");
				request.getRequestDispatcher("/LoginServlet").forward(request, response);
			}
		} catch (Exception e) {
			//request.setAttribute("err","用户id输入错误");
			//request.getRequestDispatcher("/LoginServlet").forward(request, response);
			e.printStackTrace();
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
