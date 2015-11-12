<%@page import="webDevProject.dto.MemberDTO"%>
<%@page import="webDevProject.dto.ImageDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>이미지 정보</title>
		<script type="text/javascript">
		    function selectMenu()
		    {
			    var selectMenu = document.getElementById("menu").value;
			    if(selectMenu == "opt1")
			    {
				    document.getElementById("htmlForm").innerHTML = 
				    	"<table><tr><td>상품정보</td><td><input type=\"text\" name=pdtInfo>"
				    	+"<input align=\"top\" type=\"image\" name=\"search3\" width=\"22\" height=\"22\" src=\"../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png\"></td></tr>"
				    	+"<tr><td>상품코드</td><td><input type=\"text\" name=pdtCode>"
				    	+"<input align=\"top\" type=\"image\" name=\"search3\" width=\"22\" height=\"22\" src=\"../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png\"></td></tr>"
				    	+"<tr><td>상품명</td><td><input type=\"text\" name=pdtName></td></tr></table>"; 
			    }
			    else
			    {
				    document.getElementById("htmlForm").innerHTML = 
				    	"<tr>"
				    	+"<td>URL정보</td>"
				    	+"<td><input type=\"text\" name=urlInfo></td>"
				    	+"</tr>";
			    }
		    }
		</script>
	</head>
	<body>
		<%MemberDTO memDto = (MemberDTO) session.getAttribute("imageRegister");%>
		<h3>이미지 정보</h3>
		<form method="post" action="ImageGrant" enctype="multipart/form-data">
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
	   
	   <h3>이미지 정보</h3>
	   <table cellspacing="0" style="width:100%">     
	        <tr>
	            <td>구분</td>
	            <td>
		            <select name="imageMenu">
		            	<option value="" selected>-선택-</option>
			            <option value="opt1">로고</option>
			            <option value="opt2">프로모션이미지</option>
			            <option value="opt3">마네킹 촬영사진</option>
		            </select>
	            </td>
		    </tr>
		    <tr>
	            <td>이미지1</td>
	            <td>
					<input type="file" name="imageFile" id="fileSelect"/>
	            </td>
	        </tr>
	        <tr>
	        	<td colspan=5>
			        .....<br/>
		            (버튼 클릭에 의한 이미지 파일 입력란 동적 생성?)<br/>
		            .....<br/>
	        	</td>
	        </tr>
	        <tr></tr>
	        <tr align="center">
	        	<td >
	        		<input type="button" name="preview" value="미리보기" disabled>
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
			    	<input type="submit" name="ok" value="승인" >
			    	<input type="button" name="ng" value="반려" >
		    	</td>
		    </tr>
		</table>
		</form> 
	</body>
</html>