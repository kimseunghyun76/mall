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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webDevProject.dto.MemberDTO;

public class LoginServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() 
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
        String sql = "SELECT userid, userpw, username, role FROM testdb.member WHERE userid = ?";
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
         
        String url = null;
        RequestDispatcher dispatcher = null;
        
        String id = request.getParameter("id");
        String pw = request.getParameter("password");
 
        try 
        {
            connection = DriverManager.getConnection(
                    context.getInitParameter("url"),
                    context.getInitParameter("user"),
                    context.getInitParameter("password"));
 
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            
            resultSet = preparedStatement.executeQuery();
            
            String memberId = null;
            String memberPw = null;
            String memberName = null;
            
            String memberRole = null;
            
            if(resultSet.next())
            {
                memberId = resultSet.getString(1);
                memberPw = resultSet.getString(2);
                memberName = resultSet.getString(3);
                memberRole = resultSet.getString(4);
            }

            if (memberId != null) 
            {
                if (memberPw.equals(pw)) 
                {
                    HttpSession session = request.getSession();
                    session.setAttribute("login_session", new MemberDTO(memberId, memberPw, memberName, memberRole));
                    url = "./res/login_success.jsp";
                    response.sendRedirect(url);
                } 
                else 
                {
                    request.setAttribute("login_message", "패스워드를 확인해주세요");
                    url = "./login_form.jsp";
                    dispatcher = request.getRequestDispatcher(url);
                    dispatcher.forward(request, response);
                }
            }
            else
            {
            	request.setAttribute("login_message", "ID를 확인해주세요");
                url = "./login_form.jsp";
                dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
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