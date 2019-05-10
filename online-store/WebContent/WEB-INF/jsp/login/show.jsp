<%@ page contentType="text/html; charset=UTF-8" %>
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

<%--   <h1>${user.age}</h1>
   <h1>${user.name}</h1> --%>
    <h1>${user.cusId}</h1>
    <h1>${user.cusName}</h1>
   
  <input type ="button" onclick="history.back()" value="回到上一頁"></input>
  <a href="doLogout">logout</a>
</body>
</html>











