<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Hello World</title>
<style>
/* table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 50%;
} 

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}*/
</style>
</head>
<body>

<form:form method="POST" action="show" modelAttribute="cusForm2">

  輸入帳號：<input type="text" name="cusId" value="">
  輸入密碼：<input type="text" name="password" value="">
  <input type="submit" value="確定">
</form:form>
  
<!--   <table> -->

   
<%--    <form:form method="post" action="addCustomer" modelAttribute="cusForm">
	   輸入帳號：<input type="text" name="cusId" value="">
	   輸入姓名：<input type="text" name="cusName" value="">
	   購物車代號<input type="text" name="cartBase.cartId" value="">	 
	   <input type="submit" value="go"/>
   </form:form> --%>
   
   
<%--         <tr>
     <th>CusID帳號</th>
     <th>CusName姓名</th>
     <th>Password密碼</th>
   </tr>
   <tr>
     <td>${customer.cusId}</td>
     <td>${customer.cusName}</td>
     <td>${customer.password}</td>
   </tr>   --%>
   
<!--    </table> -->
   
   
   
  
</body>
</html>