package com.springapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
//import webDevProject.dao.MemberDAO;
//import webDevProject.dto.MemberDTO;
import webDevProject.dao.ImageDAO;
import webDevProject.dto.ImageDTO;
 
public class ImageGrantServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
 
    String[] data;
    
    public ImageGrantServlet() 
    {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException 
    {
    	doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException 
    {
        ServletContext context = getServletContext();
        Connection connection = null;
        try 
        {
            connection = DriverManager.getConnection(
                    context.getInitParameter("url"),
                    context.getInitParameter("user"),
                    context.getInitParameter("password"));

            int page = 1;
            int recordsPerPage = 5;
            if(request.getParameter("page") != null)
            {
            	page = Integer.parseInt(request.getParameter("page"));
            }

            String[] data = {
            		request.getParameter("groupName"),
            		request.getParameter("shopName"),
            		request.getParameter("username"), 
            		request.getParameter("userId"),
            		request.getParameter("tel"),
            		request.getParameter("section"),
            		request.getParameter("url"),
            	};
            
            ImageDAO dao = new ImageDAO();
            List<ImageDTO> lists = dao.viewAllImages((page-1) * recordsPerPage, recordsPerPage, data);
            
            int noOfRecords = dao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            
            request.setAttribute("groupName", data[0]);
            request.setAttribute("shopName", data[1]);
            request.setAttribute("username", data[2]);
            request.setAttribute("userId", data[3]);
            request.setAttribute("tel", data[4]);
            request.setAttribute("section", data[5]);
            request.setAttribute("url", data[6]);
            
            request.setAttribute("imageList", lists);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("./image_list.jsp");
            dispatcher.forward(request, response);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
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