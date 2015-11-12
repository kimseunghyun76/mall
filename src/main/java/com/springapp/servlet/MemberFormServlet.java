package com.springapp.servlet;

import java.io.IOException;
 
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import webDevProject.dao.MemberDAO;
import webDevProject.dto.MemberDTO;
 
public class MemberFormServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
        
    public MemberFormServlet() 
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
    	String userId = request.getParameter("userId");
    	
        MemberDAO dao = new MemberDAO();
        List<MemberDTO> memberInfo = dao.viewEachMember(userId);
        
        request.setAttribute("memberInfo", memberInfo);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("./modify_form.jsp");
        dispatcher.forward(request, response);
    }
}