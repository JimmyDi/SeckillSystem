<%@ page language="java" contentType = "text/html; charset = URF-8"
	pageEncoding = "UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv = "Content-Type" content = "text/html; charset=UTF-8">
	<title>Search Items List</title>
</head>
<body>
	<form action = "${pageContext.request.contextPath}/items/queryItem.action" method = "post">
		<table width = "100%" border=1>
			<tr>
				<td><input type ="submit" value="Search"/></td>
			</tr>
		</table>
		<table width="100%" border=1>
			<tr>
				<td>Item Name</td>
				<td>Item Price</td>
				<td>Item Made Date</td>
				<td>Item Description</td>
				<td>Operation</td>		
			</tr>
		<c:forEach items="${itmesList}" var="item">
		<tr>
			<td>${item.name}</td>
			<td>${item.price}</td>
			<td><fmt:formatDate value="${item.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${item.detail}</td>
			<td><a href="${pageContext.request.contextPath }/items/editItems.action?id=${item.id}">modify</a></td>
		</tr>
		</c:forEach>
		</table>
	</form>
</body>

</html>
	