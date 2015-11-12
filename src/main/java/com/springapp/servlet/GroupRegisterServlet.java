package com.springapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webDevProject.dto.MemberDTO;

@WebServlet("/GroupRegisterServlet")
public class GroupRegisterServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public GroupRegisterServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		ServletContext context = getServletContext();
		 
        System.out.println(context.getInitParameter("url"));
        request.setCharacterEncoding("UTF-8");
 
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String groupName = request.getParameter("groupName");
        String shopName = request.getParameter("shopName");
        String tel = request.getParameter("tel");
        String category = request.getParameter("category");
        String role = request.getParameter("role");
 
        System.out.println(id + " " + pw + " " + name);
 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String resUrl = null;
        RequestDispatcher dispatcher = null;
        try 
        {
            connection = DriverManager.getConnection(
                    context.getInitParameter("url"),
                    context.getInitParameter("user"),
                    context.getInitParameter("password"));

            String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pw);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, groupName);
            preparedStatement.setString(5, shopName);
            preparedStatement.setString(6, tel);
            preparedStatement.setString(7, category);
            preparedStatement.setString(8, role);
 
            preparedStatement.executeUpdate();
 
            request.setAttribute("member", new MemberDTO(id, pw, name, groupName, shopName, tel, category, role));
            resUrl = "./res/regist_success.jsp";
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            request.setAttribute("error_message", e.getMessage());
            resUrl = "./res/error.jsp";
        } 
        finally 
        {
            if (preparedStatement != null) 
            {
                try 
                {
                    preparedStatement.close();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }
            if (connection != null) 
            {
                try 
                {
                    connection.close();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        dispatcher = request.getRequestDispatcher(resUrl);
        dispatcher.forward(request, response);
	}
}