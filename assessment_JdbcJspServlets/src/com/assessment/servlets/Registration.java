package com.assessment.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/Registration")
public class Registration extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String fname=request.getParameter("fname");
    	String lname=request.getParameter("lname");
    	String phone=request.getParameter("phone");
    	String email=request.getParameter("email");
    	String[] languages=request.getParameterValues("x");
    	
    	
    	
    	String url="jdbc:mysql://localhost:3306/registration";
		String user="root";
		String pwd="root";
		String query="insert into register values(?,?,?,?)";
		Connection con=null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 con= DriverManager.getConnection(url,user,pwd);
			PreparedStatement p= con.prepareStatement(query);
			p.setString(1,fname);
			p.setString(2,lname);
			p.setString(3, phone);
			p.setString(4, email);
			p.execute();
			PreparedStatement p1=con.prepareStatement("insert into language values(?,?)");
			for(int i=0;i<languages.length;i++){
				p1.setString(1, email);
				p1.setString(2, languages[i]);
				p1.execute();
				
			}
			response.sendRedirect("Success.jsp");
			
			
			
			
		}
		catch(Exception e){
			System.out.println("error:"+e);
			e.printStackTrace();
			
		}
    	
    	
    	
		
	}

}
