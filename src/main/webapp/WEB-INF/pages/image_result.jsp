 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"v	w"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>미리보기</title>
        <script type="text/javascript">
        var count = 1;
        
        function submitButton() 
		{
			var rForm = document.regForm;
		 	rForm.submit();
		 	return;
		}
        </script>
    </head>
    <body>
    	<form name="regForm" method="post" action="ImageFileUpload" enctype="multipart/form-data">
	    	<% 
	    		//String imageFile = "./upload/" + (String)request.getAttribute("image");
	    		int count = Integer.parseInt(request.getParameter("cnt"));
	    	%>
	    	미리보기 이미지 수: <%=count%>
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
							<img src="<%=request.getParameter(fileName)%>" alt="image<%=i%>" width="64" height="64" /><br/>
				<%		
						}     
					}
				%>
	            
	            <br/><br/>and [1] [2] .... Paging?<br/>
	            <input type="button" value="등록" onclick="javascript:submitButton();"> 
	            <input type="button" value="닫기" onclick="window.close()">
	        </div>
	    </form>
    </body>
</html>