<%@page import="webDevProject.dto.MemberDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>이미지 등록</title>
		<script type="text/javascript">
		var count = 1;
		var addCount;
		 
		function addInputBox() 
		{
			for(var i = 1; i <= count; i++) 
			{
				if(!document.getElementsByName("fileSelect" + i)[0]) 
				{
		   			addCount = i;
		   			break;
		  		}
		  		else addCount = count;
		 	}
			
			var addStr = 
				"<tr>"
				+ "<td width=10><input type=checkbox name=checkList value=" + addCount + " size=40></td>"
				+ "<td width=30><label> 이미지" + addCount + " </label></td>"
				+ "<td width=140><input type=file name=fileSelect" + addCount + " size=40></td>"
				+ "</tr>";
			var table = document.getElementById("dynamic_table");
			var newRow = table.insertRow();
			var newCell = newRow.insertCell();
		
			newCell.innerHTML = addStr;
		 	count++;
		}
		 
		function subtractInputBox() 
		{
			var table = document.getElementById("dynamic_table");
		 	var rows = dynamic_table.rows.length;
		 	var chk = 0;

		 	if(rows > 1)
		 	{
		  		for (var i = 0; i < document.regForm.checkList.length; i++) 
		  		{
		   			if (document.regForm.checkList[i].checked == true) 
		   			{
		    			table.deleteRow(i);
		    			i--;
		    			count--;
		    			chk++;
		   			}
		  		}
		  		if(chk <= 0)
		  		{
		   			alert("삭제할 행을 체크해 주세요.");
		  		}
			}
		 	else
		 	{
		    	alert("더이상 삭제할 수 없습니다.");
			}
		}
		 
		function submitButton(index) 
		{
			var rForm = document.regForm;
		 	rForm.cnt.value = eval(count);
		 	if(index == 1)
		 	{
			 	window.open("about:blank","Image Preview","width=640,height=480,scrollbars=yes");
			 	rForm.target = "Image Preview";
		 		rForm.action = "./ImageFilePreview";	
		 	}
		 	if(index == 2)
		 	{
		 		rForm.action = "./ImageFileUpload";	
		 	}
		 	rForm.submit();
		 	return;
		}
		
	    function selectMenu()
	    {
		    var selectMenu = document.getElementById("menu").value;
		    var table = document.getElementById("htmlForm");
		    
		    if(selectMenu == "opt1")
		    {
			    table.innerHTML = 
			    	"<table><tr><td>상품정보</td><td><input type=\"text\" name=pdtInfo>"
			    	+"<input align=\"top\" type=\"image\" name=\"search3\" width=\"22\" height=\"22\" src=\"../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png\"></td></tr>"
			    	+"<tr><td>상품코드</td><td><input type=\"text\" name=pdtCode>"
			    	+"<input align=\"top\" type=\"image\" name=\"search3\" width=\"22\" height=\"22\" src=\"../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png\"></td></tr>"
			    	+"<tr><td>상품명</td><td><input type=\"text\" name=pdtName></td></tr></table>"; 
		    }
		    else
		    {
			    table.innerHTML = 
			    	"<tr>"
			    	+"<td>URL정보</td>"
			    	+"<td><input type=\"text\" name=urlInfo></td>"
			    	+"</tr>";
		    }
	    }
		</script>
	</head>
	<body>
		<a href="../../../../../../../Desktop/오목눈이/webDevProject/WebContent/res/login_success.jsp">MENU</a>
		<a href="./Logout">로그아웃</a>
		
		<%
			String message = (String)request.getAttribute("message");			
			MemberDTO memDto = (MemberDTO) session.getAttribute("imageRegister");
		%>
		<h3>이미지 등록</h3>
		<form name="regForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="cnt" >
		<table align="left" cellspacing="0" border="0" style="width:100%">
		    <tr>
	            <td>카테고리</td>
	            <td>
	            	<input type="text" name=category value="<%=memDto.getCategory()%>">
	            	<input align="top" type="image" name="search3" width="22" height="22" src="../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png">
	            </td>
	        </tr>
	        <tr>
	            <td>조직명</td>
	            <td>
	            	<input type="text" name=groupName value="<%=memDto.getGrpName()%>">
	            	<input align="top" type="image" name="search2" width="22" height="22" src="../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png">
	            </td>
	        </tr>
	        <tr>
	            <td>매장명</td>
	            <td>
	            	<input type="text" name=shopName value="<%=memDto.getShopName()%>">
	            	<input align="top" type="image" name="search3" width="22" height="22" src="../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png">
	            </td>
	        </tr>
	   	</table>
	   	<br/>
	   	<h3>이미지 정보</h3>
	   	<table cellspacing="0" style="width:100%">     
	        <tr>
	            <td>구분
		            <select name="imageMenu">
		            	<option value="" selected>-선택-</option>
			            <option value="logo">로고</option>
			            <option value="promotion">프로모션이미지</option>
			            <option value="picture">마네킹 촬영사진</option>
		            </select>
		   			<input type="button" value="+" onclick="javascript:addInputBox();">
					<input type="button" value="-" onclick="javascript:subtractInputBox();">
	            </td>
		    </tr>
			<table cellpadding=2 cellspacing=2 id="dynamic_table" border="0"></table>
	        <tr align="center">
	        	<td >
	        		<input type="button" value="미리보기" onclick="javascript:submitButton(1);">
	        	</td>
	        </tr>
		</table>
		
		<h3>연결정보</h3>
		<table align="left" border="1" cellspacing="0" style="width:100%">
			<tr>
	            <td>구분</td>
	            <td>
		            <select id="menu" onchange="selectMenu()">
			            <option value="" selected>-선택-</option>
			            <option value="opt1">상품</option>
			            <option value="opt2">URL</option>
		            </select>
	            </td>
	        </tr>
	        <tr>
	        	<td colspan=2>
					<p id="htmlForm"></p>
	        	</td>   
		    </tr>
		</table>
		
		<table cellspacing="0" style="width:100%">
		    <tr>
		    	<td align="center" colspan=2>
		    		<input type="button" value="저장" onclick="javascript:submitButton(2);">
			    	<!-- <input type="submit" name="save" value="저장" > -->
			    	<!-- <input type="button" name="close" value="닫기" > -->
		    	</td>
		    </tr>
		    <tr>
		    	<td>
		    		<% 
		    		if(message != null)
		    		{
		    		%>
		    			<%=message%>
		    		<%
		    			message = "";
		    		}
		    		%>
		    	</td>
		    </tr>
		</table>
		</form> 
	</body>
</html>