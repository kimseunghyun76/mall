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
 
import webDevProject.dao.MemberDAO;
import webDevProject.dto.MemberDTO;
 
public class GetMemberInfoServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
 
    String[] data;
    
    public GetMemberInfoServlet() 
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
//        PreparedStatement preparedStatement = null;
// 
//        String sql = "SELECT * FROM member";
// 
//        ResultSet resultSet = null;
//        ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
        try 
        {
            connection = DriverManager.getConnection(
                    context.getInitParameter("url"),
                    context.getInitParameter("user"),
                    context.getInitParameter("password"));
// 
//            preparedStatement = connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
 //------------------------------------------------------
            int page = 1;
            int recordsPerPage = 5;
            if(request.getParameter("page") != null)
            {
            	page = Integer.parseInt(request.getParameter("page"));
            }

            String[] data = {
            		request.getParameter("category"), 
            		request.getParameter("groupName"),
            		request.getParameter("shopName"),
            		request.getParameter("userId"),
            		request.getParameter("role")
            	};
            
            MemberDAO dao = new MemberDAO();
            List<MemberDTO> lists = dao.viewAllMembers((page-1) * recordsPerPage, recordsPerPage, data);
//            List<MemberDTO> lists = dao.viewAllMembers((page-1) * recordsPerPage, recordsPerPage);
            
            int noOfRecords = dao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            
            request.setAttribute("category", data[0]);
            request.setAttribute("groupName", data[1]);
            request.setAttribute("shopName", data[2]);
            request.setAttribute("userId", data[3]);
            request.setAttribute("role", data[4]);
            
            request.setAttribute("memberList", lists);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            
//            request.setAttribute("option", (String)request.getParameter("role"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("./member_list.jsp");
            dispatcher.forward(request, response);
 //------------------------------------------------------
//            while (resultSet.next()) 
//            {
//                list.add(new MemberDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
//                		resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
//            }
// 
//            request.setAttribute("memberList", list);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("./member_list.jsp");
//            dispatcher.forward(request, response);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
//            if (resultSet != null) 
//            {
//                try 
//                {
//                    resultSet.close();
//                } 
//                catch (SQLException e) 
//                {
//                    e.printStackTrace();
//                }
//            }
//            if (preparedStatement != null) 
//            {
//                try 
//                {
//                    preparedStatement.close();
//                } 
//                catch (SQLException e) 
//                {
//                    e.printStackTrace();
//                }
//            }
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