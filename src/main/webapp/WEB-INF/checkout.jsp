<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file = "header.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<!-- ****************Start of the container**********************-->

<div class="container">
<div class="row">

<hr />

	  <!-- ****************Start of the Table to list**********************-->
<div class="col-sm-12">
<c:if test= "${!empty cartpdts}">
<h2>Product Cart for the User</h2>
<table class = "table">
<tr>
  	<th>Product</th>
	<th>Quantity</th>
	<th>Total Cost</th>
</tr>
<c:forEach items="${cartpdts}" var="cart_pdt">
<tr>
<td><c:out value="${cart_pdt.value.productName}" /></td>
<td><c:out value="${cart_pdt.value.quantity}" /></td>
<td><c:out value="${cart_pdt.value.totalCost}" /></td>
</tr>
</c:forEach>
</table>
</c:if>
</div>
</div>
<div class = "row">
  <!-- ****************End of the table to list**********************-->
<div class="col-md-6">
<h2>Add To Cart Data</h2>
<form:form method="POST" action="/Ecommerce/user_save_check_out">
<table>
<tr>
<td><form:label path="addressline1">User AddressLine1</form:label></td>
<td><form:input path="addressline1" value="${userDetails.addressline1}"></form:input></td>
<td><form:errors path="addressline1" style="color:red"/></td>

</tr>
<tr>
<td><form:label path="addressline2">User AddressLine2</form:label></td>
<td><form:input path="addressline2" value="${userDetails.addressline2}" /></td>
<td><form:errors path="addressline2" style="color:red"/></td>
</tr>
<tr>
<td><form:label path="state">State</form:label></td>
<td><form:input path="state" value="${userDetails.state}" /></td>
<td><form:errors path="state" style="color:red"/></td>
</tr>
<tr>
<td><form:label path="emailid">Email Id</form:label></td>
<td><form:input path="emailid" value="${userDetails.emailid}" /></td>
<td><form:errors path="emailid" style="color:red"/></td>
</tr>

<tr>
<td>
<a href="/Ecommerce/all_landing"><button class = "button">Cancel Purchase</button></a>
</td>
<td>
<a href="/Ecommerce/user_save_check_out"><button class = "button">Confirm and Checkout</button></a>
</td>
</tr>
</table>
</form:form>
</div>
	  
</div>


</div>

  <!-- ****************End of the container**********************-->
<hr />

 
</body>
</html>
<%@include file = "footer.jsp"  %>