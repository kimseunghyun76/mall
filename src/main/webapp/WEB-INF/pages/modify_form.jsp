<%@page import="webDevProject.dto.MemberDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원정보 변경</title>
		<%
			request.getAttribute("memberInfo");
			String message = (String)request.getAttribute("message");
			MemberDTO memDto = (MemberDTO) session.getAttribute("login_session");
		%>
	</head>
	<body>
	    <h1>회원정보수정</h1>
	    <form method="post" action="./ModifyMember">
	        <c:forEach var="member" items="${memberInfo}">
	        <table align="center">
	            <tr>
	                <td>ID</td>
	                <td><input type=text name=userid  value="${member.id}"></td>
	            </tr>
	            <tr>
	                <td>이름</td>
	                <td><input type=text name=username value="${member.name}"></td>
	            </tr>
	            <tr>
	                <td>조직명</td>
	                <td><input type=text name=groupname value="${member.grpName}">
	            </tr>
	            <tr>
	                <td>매장명</td>
	                <td><input type=text name=shopname value="${member.shopName}">
	            </tr>
	            <tr>
	                <td>전화번호</td>
	                <td><input type="text" name=tel value="${member.tel}"></td>
	            </tr>
	            <tr>
	                <td>카테고리</td>
	                <td><input type=text name=category value="${member.category}">
	            </tr>
	            <tr>
	            	<td>권한</td>
		            <td>
			        	<select name=role>
		            	<c:set var="roles" value="${member.role}"/>
				            <%
				            	String optionStr1 = "";
					            String optionStr2 = "";
					            String optionStr3 = "";
				            	String roleStr = (String) pageContext.getAttribute("roles");
				            	if(roleStr.equals("chief"))
				            	{
									optionStr1 = "selected";			            	
				            	}
				            	else if(roleStr.equals("admin"))
				            	{
				            		optionStr2 = "selected";
				            	}
				            	else
				            	{
				            		optionStr3 = "selected";
				            	}
				            %>
				            <option value="chief" <%=optionStr1%>>최고관리자</option>
				            <option value="admin" <%=optionStr2%>>관리자</option>
				            <option value="user" <%=optionStr3%>>사용자</option>
			            </select>
		            </td>
	            </tr>
	            <tr>
	                <td><input type=submit value=정보수정></td>
	                <td><input type="button" value="닫기" onclick="window.close()"></td>
	                <!-- <td><input type=button onclick="location.href='./member_detail.jsp'" value=취소></td> -->
	            </tr>
	            <tr>
	                <td colspan = 2>
		                <%
			                if(message != null)
			                {
			            %>
				                <%=message%>
			            <%
			                }
		                %>
	                </td>
	            </tr>
	        </table>
	        </c:forEach>
	    </form>
	</body>
</html>