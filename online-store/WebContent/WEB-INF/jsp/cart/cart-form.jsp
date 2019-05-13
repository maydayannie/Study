<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>產品代號</th>
			<th>產品名稱</th>
			<th>單價</th>
			<th>數量</th>
			<th>金額</th>
		</tr>
		<c:forEach var="it" items="${sessionScope.cart}">
			<tr>
				<td>${it.prodid}</td>
				<td>${it.prodname}</td>
				<td>${it.price}</td>
				<td>${it.quantity}</td>
				<td>${it.price * it.quantity}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>