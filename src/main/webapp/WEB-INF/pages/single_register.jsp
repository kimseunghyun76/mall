<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원등록</title>
	</head>
	<body>
		<h1>회원등록</h1>
	    <form method="post" action="./SingleRegister">
	        <table align="Center">
	            <tr>
	                <td>ID</td>
	                <td><input type="text" name=id></td>
	            </tr>
	            <tr>
	                <td>Password</td>
	                <td><input type="password" name=pw></td>
	            </tr>
	            <tr>
	                <td>이름</td>
	                <td><input type="text" name=name></td>
	            </tr>
	            <tr>
	                <td>조직명</td>
	                <td><input type="text" name=groupName></td>
	            </tr>
	            <tr>
	                <td>매장명</td>
	                <td><input type="text" name=shopName></td>
	            </tr>
	            <tr>
	                <td>전화번호</td>
	                <td><input type="text" name=tel></td>
	            </tr>
	            <tr>
	                <td>카테고리</td>
	                <td><input type="text" name=category></td>
	            </tr>
	            <tr>
	                <td>권한</td>
	                <td>
			            <select name=role>
			            	<option>선택</option>
				            <option value="chief">최고관리자</option>
				            <option value="admin">관리자</option>
				            <option value="user">사용자</option>
			            </select>
	                </td>
	            </tr>
	            <tr>
	            <td></td>
	                <td colspan=2 rowspan="4">
	                	<input type="submit" value="등록">
	                	<input type="reset" value="초기화">
	                	<input type="button" value="닫기" onclick="window.close()">
	                </td>
	            </tr>
	        </table>
	    </form>
	</body>
</html>