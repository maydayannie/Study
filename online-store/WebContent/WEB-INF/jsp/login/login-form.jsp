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
 <form:form method="POST" action="doLogin" modelAttribute="cusForm">
  <table> 
<%--    <tr>
     <th>CusID</th>
     <th>CusName</th>
     <th>CartID</th>
   </tr>
   <tr>
     <td>${ccc.cusId}</td>
     <td>${ccc.cusName}</td>
     <td>${ccc.cartBase.cartId}</td>    
   </tr>  --%>
   
   <tr>
     <th>CusID帳號</th>
     <th>Password密碼</th>
   </tr>
   <tr>
     <td><input type="text" name="cusId" value=""></td>
     <td><input type="text" name="password" value=""></td>
    </tr>  
  </table>
  <input type="submit" value="確定">
  </form:form>
</body>
</html>











