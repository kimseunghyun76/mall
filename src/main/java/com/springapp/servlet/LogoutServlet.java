package com.springapp.servlet;

import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class LogoutServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
        
    public LogoutServlet() 
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
        RequestDispatcher dispatcher = null;
        
        if(session.getAttribute("login_session") != null)
        {
            session.invalidate();
            request.setAttribute("login_message", "로그아웃 되었습니다.");
            dispatcher = request.getRequestDispatcher("./login_form.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            request.setAttribute("login_message", "로그아웃 실패\n로그인을 해주세요");
            dispatcher = request.getRequestDispatcher("./login_form.jsp");
            dispatcher.forward(request, response);
        }
    }
}