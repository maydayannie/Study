<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

  <form:form method="POST" action="/online-store/prod/doProd" modelAttribute="prodForm">
     <h1>${user.age}</h1>
     <h1>${user.name}</h1> 
     <input type ="button" onclick="history.back()" value="回到上一頁!!"></input>
     <a href="doLogout">logout</a>
     <input type ="submit"  value="所有商品"></input>
  </form:form>
  
</body>
</html>











