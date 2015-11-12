 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"v	w"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>미리보기</title>
    </head>
    <body>
    	<form name="regForm" method="post" action="ImageFileUpload" enctype="multipart/form-data">
	    	<% 
	    		int count = Integer.parseInt(request.getParameter("cnt"));
	    	%>
	    	미리보기 이미지 수: <%=count-1%>
	        <div id="result">
	            <h4>미리보기</h4>
				<%
					for(int i = 1; i <= count; i++)
					{
					    String fileName = "fileName" + i;
					    String fileLength = "fileLength" + i;
			
					    if(request.getParameter(fileName) != null)
					    {
				%>	    
							File Name : <%=request.getParameter(fileName)%><br/>
							File Size : <%=request.getParameter(fileLength)%>KB<br/>
							<img src="./upload/temp/<%=request.getParameter(fileName)%>" alt="image<%=i%>" width="64" height="64" /><br/>
				<%		
						}     
					}
				%>
	            
	            <br/><br/>and [1] [2] .... Paging?<br/>
	            <!-- <input type="button" value="등록" onclick="javascript:submitButton();"> --> 
	            <input type="button" value="닫기" onclick="window.close()">
	        </div>
	    </form>
    </body>
</html>