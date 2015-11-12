<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>대량회원등록</title>
		<script type="text/javascript">
		function ShowFileName()
		{
			var filePath = document.getElementById("filePath");
			document.getElementById('hiddenFilePath').innerHTML = 
				"<td><input type=\"hidden\" id=\"hiddenFilePath\" name=\"hiddenFilePath\" value=\""+filePath.value+"\"></td>";
		}
		</script>
	</head>
	<body>
		<form method="post" name="form" action="view.jsp">
			<table>
				<tr>
					<td><input type="file" id="filePath" name="filePath"></td>
					<td><input type="button" value="tmpCheck" onClick="ShowFileName()"></td>
					<td><p id="hiddenFilePath"></p></td>
					<td><input type="submit" name="dataCheck" value="dataCheck"></td>
				</tr>
			</table>
		</form>	
	</body>
</html>