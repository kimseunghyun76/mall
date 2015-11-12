<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>대량회원등록</title>
	</head>
	<body>
		<form method="post" action="group_register.jsp">
			<table>
				<tr>
					<td><a href=logRead.jsp>BACK</a></td>
					<td><input type="submit" name="groupRegister" value="Regist"></td>
				</tr>
			</table>
			<%
				String line = "";
				String token = ",";
				request.setCharacterEncoding("UTF-8");
				String temp = (String)request.getParameter("hiddenFilePath");
				System.out.println("temp: " + temp);
				FileReader objFr = new FileReader(temp);
				BufferedReader objBr = new BufferedReader(objFr);

				int first = 0;
				int count = 0;
				while((line = objBr.readLine()) != null)
				{
					if(first == 0)
					{
						out.println("<table border=\"1\">");
						out.println("<tr><th>ID</th><th>PW</th><th>NAME</th><th>GROUPNAME</th><th>SHOPNAME</th><th>TEL</th><th>CATEGORY</th><th>ROLE</th></tr>");
					}
					StringTokenizer objTkn = new StringTokenizer(line, token);
					
					out.println("<tr>");
					while(objTkn.hasMoreTokens())
					{
						out.println("<td nowrap>" + objTkn.nextToken() + "</td>");
					}
					out.println("</tr>");
					first = 1;
					count++;
				}
				objBr.close();
				if(first == 0)
				{
					out.println("<p>No DATA</p>");
				}
				if(first == 1)
				{
					out.println("</table>");
					out.println("data count: "+ count);
				}
			%>
			<br/>
		</form>	
	</body>
</html>