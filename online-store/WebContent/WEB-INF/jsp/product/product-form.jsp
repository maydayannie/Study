<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Hello World</title>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 50%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}
</style>
</head>
<body>
	<table>
		<tr>
			<th>產品代號</th>
			<th>產品名稱</th>
			<th>說明</th>
			<th>價錢</th>
			<th></th>
		</tr>
		<c:forEach var="pp" items="${prod}">
			<tr>
				<td>${pp.prodid}</td>
				<td>${pp.prodname}</td>
				<td>${pp.notes}</td>
				<td>${pp.price}</td>
				<td><a href="<c:url value="/prod/addProd?prodId="/>${pp.prodid}"><button class="btn btn-outline-primary">Add</button></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>











