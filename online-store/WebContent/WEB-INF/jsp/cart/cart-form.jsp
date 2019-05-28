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
	<table class="table table-hover">
		<tr>
			<th>產品代號</th>
			<th>產品名稱</th>
			<th>規格</th>
			<th>單價</th>
			<th>數量</th>
			<th>金額</th>
		</tr>
		<c:forEach var="it" items="${cusCart}">
			<tr>				
				<td>${it.id.prodId}</td>
				<td>${it.product.prodName}</td>
				<td>${it.product.notes}</td>
				<td>${it.product.price}</td>
				<td>${it.qty}</td>				
				<td>${it.product.price * it.qty}</td>
			    <td><a href="<c:url value="/cart/delProd/"/>${it.id.prodId}">
			    <button class="btn btn-outline-danger">Delete</button></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>  