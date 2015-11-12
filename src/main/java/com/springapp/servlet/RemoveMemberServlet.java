package com.springapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import webDevProject.dto.MemberDTO;
 
public class RemoveMemberServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
 
    public RemoveMemberServlet() 
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
        HttpSession session = request.getSession();
 
        MemberDTO mem = (MemberDTO) session.getAttribute("login_session");
        RequestDispatcher dispatcher = null;
 
        if (mem != null) 
        {
            String sql = "delete from member where id = ?";
 
            ServletContext context = getServletContext();
            Connection connection = null;
            PreparedStatement preparedStatement = null;
             
            try 
            {
                connection = DriverManager.getConnection(
                        context.getInitParameter("url"),
                        context.getInitParameter("user"),
                        context.getInitParameter("password"));
                 
                preparedStatement = connection.prepareStatement(sql);
                 
                preparedStatement.setString(1, mem.getId());
                if(preparedStatement.executeUpdate()==1)
                {
                    session.invalidate();
                    dispatcher = request.getRequestDispatcher("./index.html");
                    dispatcher.forward(request, response);
                }
                 
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
                request.setAttribute("error_message", e.getMessage());
                 
                dispatcher = request.getRequestDispatcher("./res/error.jsp");
                dispatcher.forward(request, response);
            } 
            finally 
            {
                if(preparedStatement != null)
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
                if(connection != null)
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
        else 
        {
            request.setAttribute("login_message", "탈퇴 실패\n 로그인을 먼저 해주세요");
            dispatcher = request.getRequestDispatcher("./login_form.jsp");
            dispatcher.forward(request, response);
        }
    }
}