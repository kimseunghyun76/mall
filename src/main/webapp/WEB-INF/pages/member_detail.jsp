<%@page import="webDevProject.dto.MemberDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Member Info Detail</title>
	</head>
	<body>
	    <%
	    	MemberDTO memDto = (MemberDTO) session.getAttribute("login_session");
	    %>
	    <table align="center" border="1" cellspacing="0" width="300" height="150">
	        <tr>
	            <td colspan=2 align="center"><b>회원 정보</b></td>
	        </tr>
	        <tr>
	            <td align="center">이름</td>
	            <td><%=memDto.getName()%></td>
	        </tr>
	        <tr>
	            <td align="center">ID</td>
	            <td><%=memDto.getId()%></td>
	        </tr>
	        <tr>
	            <td colspan=2 align="center">
	            	<input type="button" onclick="location.href = './ModifyForm'" value="정보수정">
	            </td>
	        </tr>
	        <tr>
	            <td colspan=2 align="center">
	            	<input type="button" onclick="location.href = './login_success.jsp'" value="나가기">
	            </td>
	        </tr>
	        <tr>
	            <td colspan=2 align="center"><input type="button"
	                onclick="location.href = './RemoveMember'" value="탈퇴"></td>
	        </tr>
	    </table>
	</body>
</html>