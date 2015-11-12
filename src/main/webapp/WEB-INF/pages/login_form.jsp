<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login Page</title>
	</head>
	<body>
		<h1>로그인</h1>
	    <form method="post" action="./Login">
        <table align="left">
            <tr>
            	<td>ID</td>
                <td><input type="text" name=id></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name=password></td>
            </tr>
            <tr>
            	<td></td>
                <td>
                	<input type="submit" value="로그인">
                	<!-- <input type="button" value="회원등록" onclick="location.href ='./signup_form.html'"> -->
                </td>
            </tr>
            <tr>
                <td colspan=2>
                	<b><%=request.getAttribute("login_message")%></b>
                </td>
            </tr>
        </table>
	    </form>
	</body>
</html>