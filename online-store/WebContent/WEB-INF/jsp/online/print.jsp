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
     <th>CusName姓名</th>
     <th>Password密碼</th>
   </tr>
   <tr>
     <td>${customer.cusId}</td>
     <td>${customer.cusName}</td>
     <td>${customer.password}</td>
    </tr>  
  </table>
  
  <input type ="button" onclick="history.back()" value="回到上一頁"></input>
</body>
</html>











