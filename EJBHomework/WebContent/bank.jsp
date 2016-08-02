<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="WEB-INF/template/header.jsp"/>

<form method="post" action="/EJBHomework/BankOperation">
Client ID: <input type="text" value="${client}" name="clientId"/> <br/>
Current amount: <input type="text" value="${balance}" name="currentAmount"/> <br/>
Operation:  <input type="radio" name="operation" value="Deposit" checked> Deposit
 			 <input type="radio" name="operation" value="Withdraw"> Withdraw<br/><br/>
Amount: <input type="text" value="" name="amount"/> <br/>
Currency: <input type="text" value="" name="currency"/> <br/><br/>
<input type="submit" value="Submit"/> <br/>
</form>
<jsp:include page="WEB-INF/template/footer.jsp"/>
</body>
</html>