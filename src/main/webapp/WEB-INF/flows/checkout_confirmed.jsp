<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file = "header.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<!-- ****************Start of the container**********************-->

<div class="container">
<div class="row">
<h2>Thanks for trying out Thrift Shop!
<br/>You will recieve your product within 7-10 days on COD payment mode!</h2>
<hr />

	  <!-- ****************Start of the Table to list**********************-->
<div class="col-sm-12">
<c:if test= "${!empty modelbind.model.cartpdts}">
<h2>Product Cart for the User</h2>
<table class = "table">
<tr>
  	<th>Product</th>
	<th>Quantity</th>
	<th>Total Cost</th>
</tr>
<c:forEach items="${modelbind.model.cartpdts}" var="cart_pdt">
<tr>
<td><c:out value="${cart_pdt.value.productName}" /></td>
<td><c:out value="${cart_pdt.value.quantity}" /></td>
<td><c:out value="${cart_pdt.value.totalCost}" /></td>
</tr>
</c:forEach>
<tr>
	<td><c:out value="Total" /></td>
	<td><c:out value="${modelbind.model.cartpdts_qty}" /></td>
    <td><c:out value="${modelbind.model.cartpdts_cost}" /></td>
    </tr>
</table>
</c:if>
<c:if test= "${!empty modelbind.userDetails}">
<h2>Delivery Address for the User</h2>
<table class = "table">
<tr>
  	<th>UserName</th>
	<th>Address Line 1</th>
	<th>Address Line 2</th>
	<th>State</th>
	<th>Email ID</th>
	<th>Phone Number</th>
</tr>
<tr>
<td><c:out value="${modelbind.userDetails.uname}" /></td>
<td><c:out value="${modelbind.userDetails.addressline1}" /></td>
<td><c:out value="${modelbind.userDetails.addressline2}" /></td>
<td><c:out value="${modelbind.userDetails.state}" /></td>
<td><c:out value="${modelbind.userDetails.emailid}" /></td>
<td><c:out value="${modelbind.userDetails.personalphone}" /></td>

</tr>
</table>
</c:if>

</div>
</div>
	  
</div>

  <!-- ****************End of the container**********************-->
<hr />

 
</body>
</html>
<%@include file = "footer.jsp"  %>