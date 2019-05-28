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
	
	<a href="<c:url value="/cart/mycart/"/>"><button class="btn btn-success">管理帳號</button></a>
	<a href="<c:url value="/admin/allProduct"/>"><button class="btn btn-success">管理商品</button></a>
</body>
</html>











