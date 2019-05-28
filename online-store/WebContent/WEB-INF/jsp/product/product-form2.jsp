<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Hello World</title>

</head>
<body>
	<table class="table table-hover">
		<tr>
			<th>產品代號</th>
			<th>產品名稱</th>
			<th>說明</th>
			<th>價錢</th>
			<th>管理動作</th>
		</tr>
		<c:forEach var="pp" items="${prod}">
			<tr>
				<td>${pp.prodId}</td>
				<td>${pp.prodName}</td>
				<td>${pp.notes}</td>
				<td>${pp.price}</td>
				<td>
				   <a href="<c:url value="/prod/addProd/"/>${pp.prodId}"><button class="btn btn-outline-primary">編輯</button></a>
				   <a href="<c:url value="/prod/addProd/"/>${pp.prodId}"><button class="btn btn-outline-primary">刪除</button></a>
			    </td>
			</tr>
		</c:forEach>
	</table>
	<a href="<c:url value="/cart/mycart/"/>"><button class="btn btn-success">My cart</button></a>
</body>
</html>











