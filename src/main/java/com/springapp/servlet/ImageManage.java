package com.springapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webDevProject.dto.MemberDTO;

@WebServlet("/ImageManage")
public class ImageManage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ImageManage() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
        ServletContext context = getServletContext();
        String sql = "SELECT category, groupName, shopName FROM testdb.member WHERE userid = ?";
        System.out.println(sql);
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
         
        String url = null;
        RequestDispatcher dispatcher = null;
        
        String id = request.getParameter("id");
 
        try 
        {
            connection = DriverManager.getConnection(
                    context.getInitParameter("url"),
                    context.getInitParameter("user"),
                    context.getInitParameter("password"));
 
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            
            resultSet = preparedStatement.executeQuery();

            String memberCategory = null;
            String memberGrpName = null;
            String memberShopName = null;
            
            if(resultSet.next())
            {
            	memberCategory = resultSet.getString(1);
            	memberGrpName = resultSet.getString(2);
            	memberShopName = resultSet.getString(3);
            }

            HttpSession session = request.getSession();
            session.setAttribute("imageRegister", new MemberDTO(memberCategory, memberGrpName, memberShopName));
            response.sendRedirect("./image_register.jsp");
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            request.setAttribute("error_message", e.getMessage());
            url = "./res/error.jsp";
            
            dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } 
        finally 
        {
            if(resultSet != null)
            {
                try 
                {
                    resultSet.close();
                } 
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }
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
	}
}
