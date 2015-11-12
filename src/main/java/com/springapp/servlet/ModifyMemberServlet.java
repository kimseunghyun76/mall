package com.springapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webDevProject.dao.MemberDAO;
import webDevProject.dto.MemberDTO;
 
public class ModifyMemberServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
 
    public ModifyMemberServlet() 
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
        MemberDTO memDto = (MemberDTO) session.getAttribute("login_session");
        if (memDto != null) 
        {
        	String userid = request.getParameter("userid");
        	String username = request.getParameter("username");
        	String groupname = request.getParameter("groupname");
        	String shopname = request.getParameter("shopname");
        	String tel = request.getParameter("tel");
        	String category = request.getParameter("category");
        	String role = request.getParameter("role");
        	
            RequestDispatcher dispatcher = null;
            if (memDto != null) 
            { 
        		if (memDto != null) 
                { 
                    ServletContext context = getServletContext();
                    Connection connection = null;
                    PreparedStatement preparedStatement = null;
                    String sql = "UPDATE member SET "
                    		+ "username = ?, groupname = ? , shopname = ?, "
                    		+ "tel = ?, category = ?, role = ? "
                    		+ "WHERE userid = ?";
                    String url = null;
                    System.out.println(sql);
                    try 
                    {
                        connection = DriverManager.getConnection(
                                context.getInitParameter("url"),
                                context.getInitParameter("user"),
                                context.getInitParameter("password"));
 
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, groupname);
                        preparedStatement.setString(3, shopname);
                        preparedStatement.setString(4, tel);
                        preparedStatement.setString(5, category);
                        preparedStatement.setString(6, role);
                        preparedStatement.setString(7, userid);
                        
                        if (preparedStatement.executeUpdate() == 1) 
                        {
                            MemberDAO dao = new MemberDAO();
                            List<MemberDTO> memberInfo = dao.viewEachMember(userid);
                            request.setAttribute("memberInfo", memberInfo);
                            request.setAttribute("message", "회원정보가 변경되었습니다.");
                            
                            dispatcher = request.getRequestDispatcher("./modify_form.jsp");
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
        } 
        else 
        {
            request.setAttribute("login_message", "정보 수정 실패\n 로그인을 먼저 해주세요");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./login_form.jsp");
            dispatcher.forward(request, response);
        }
    }
}