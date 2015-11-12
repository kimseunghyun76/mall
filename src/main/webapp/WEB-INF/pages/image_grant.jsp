<%@page import="webDevProject.dto.MemberDTO"%>
<%@page import="webDevProject.dto.ImageDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>이미지승인</title>
		<script language="javascript">
		function temp()
		{
			window.open('','');	
		}
		</script>
	</head>
	<body>
		<%
			MemberDTO memDto = (MemberDTO) session.getAttribute("login_session");
		
			String optionStr = "";
			//if(!memDto.getRole().equals("chief"))
			//{
			//	optionStr = "disabled";	
			//} 
		%>
	
		<a href="../../../../../../../Desktop/오목눈이/webDevProject/WebContent/res/login_success.jsp">MENU</a>
		<a href="./Logout">로그아웃</a>
		<h1>이미지승인</h1>
		<form action="./ImageGrant">
		<table align="center" cellspacing="0" style="width:100%">
		    <tr>
		    	<td align="right" colspan=2>
			    	<input type="button" name="ok" value="승인" onclick="ok()" <%=optionStr%>>
			    	<input type="button" name="ng" value="반려 " onclick="ng()" <%=optionStr%>>
			    	<input type="submit" name="search" value="조회">
		    	</td>
		    </tr>
		</table>
		
		<table style="margin:10px;" align="center" cellspacing="0" border="0" style="width:100%">
		    <tr>
	            <td>카테고리</td>
	            <td><input type="text" name=category>
	            <input align="top" type="image" name="search1" width="22" height="22" src="../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png" onclick=""></td>
	        </tr>
	        <tr>
	            <td>조직명</td>
	            <td><input type="text" name=groupName>
	            <input align="top" type="image" name="search2" width="22" height="22" src="../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png" onclick=""></td>
	        </tr>
	        <tr>
	            <td>매장명</td>
	            <td><input type="text" name=shopName>
	            <input align="top" type="image" name="search3" width="22" height="22" src="../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png" onclick=""></td>
	        </tr>
	        <tr> 
	            <td>아이디</td>
	            <td><input type="text" name=userId>
	            <input align="top" type="image" name="search4" width="22" height="22" src="../../../../../../../Desktop/오목눈이/webDevProject/WebContent/img/search.png" onclick=""></td>
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
	            <td>이미지구분</td>
	            <td>
		            <select name=role>
		            	<option>선택</option>
			            <option value="value1">value1</option>
			            <option value="value2">value2</option>
			            <option value="value3">value3</option>
		            </select>
	            </td>
		    </tr>
		</table>
		
	    <table style="margin:10px;" align="center" border="1" cellpadding="3" cellspacing="3">
			<c:set var="incrPerPage" value="5" />
	        <c:if test="${currentPage >= 1}">
				<c:if test="${fn:length(imageList) > 0}">
			        <tr>
			            <th>No</th>
			            <th colspan="2">Info</th>
			        </tr>
				</c:if>
				<c:if test="${fn:length(imageList) <= 0}">
		            <table style="margin:10px;" align="center" cellspacing="0" border="0" style="width:100%">
			            <tr><td>조건에 맞는 이미지가 없습니다</td></tr>
		            </table>
				</c:if>
				
	        </c:if>
	        <c:forEach begin="1" end="${noOfPages}" var="page">
				<c:set var="count" value="0" />
		       	<c:forEach var="image" items="${imageList}">
					<c:set var="count" value="${count+1}" />
		       		<c:choose>
			       		<c:when test="${currentPage eq page}">
			            	<tr>
		                        <td align="center">${count + ((page - 1) * incrPerPage)}</td>
				                <td>${image.id} / ${image.name} / ${image.grpName} / ${image.shopName} / ${image.tel} / ${image.category} / ${image.role}</td>
				                <td><input type="button" name="memberInfoModify" value="정보수정" onclick="window.open('ImageForm?userId=${image.id}');" <%=optionStr%>></td>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</c:forEach>
	    </table>

	    <table align="center" cellpadding="5" cellspacing="5">
	        <tr>
		    <c:if test="${currentPage > 1}">
		        <td>
		        	<a href="ImageGrant?
                        search=${param.search}
                        &category=${param.category}
						&groupName=${param.groupName}
						&shopName=${param.shopName}
						&userId=${param.userId}
        				&page=${currentPage - 1}">Previous</a>
		        </td>
		    </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td>
                        	<a href="ImageGrant?
                                search=${param.search}
		                        &category=${param.category}
								&groupName=${param.groupName}
								&shopName=${param.shopName}
								&userId=${param.userId}
                        		&page=${i}">${i}</a>
                        </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
		    <c:if test="${currentPage lt noOfPages}">
		        <td>
		        	<a href="ImageGrant?
                        search=${param.search}
                        &category=${param.category}
						&groupName=${param.groupName}
						&shopName=${param.shopName}
						&userId=${param.userId}
        				&page=${currentPage + 1}">Next</a>
		        </td>
		    </c:if>
	        </tr>
	    </table>
		</form>
	</body>
</html>