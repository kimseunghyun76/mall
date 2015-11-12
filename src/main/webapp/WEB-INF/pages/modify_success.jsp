<%@page import="webDevProject.dto.MemberDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원정보 변경완료</title>
	</head>
	<body>
	    <% MemberDTO memDto = (MemberDTO) session.getAttribute("login_session"); %>
	    <table align="center">
	        <tr>
	            <td>ID</td>
	            <td><%=memDto.getId()%></td>
	        </tr>
	        <tr>
	            <td>Password</td>
	            <td>
	            	<%=memDto.getPassword().substring(0, 4)%> 
		            <%for (int i = 4; i < memDto.getPassword().length(); i++)%>
		            <%{%>
		            	* 
		            <%}%>
	            </td>
	        </tr>
	        <tr>
	            <td>이름</td>
	            <td><%=memDto.getName()%></td>
	        </tr>
	        <tr>
	            <td colspan =2 align="center">
	            	<input type = "button" onclick="location.href = './res/member_detail.jsp'" value = "회원 정보로 돌아가기">
	            </td>
	        </tr>
	    </table>
	</body>
</html>