<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file = "header.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function validateform(){
	console.log("validateform");
	console.log(document);
	console.log(document.userAddressForm);
	var addressLine1=document.getElementById("addressline1").value;  
	var addressLine2=document.getElementById("addressline2").value;  
	var emailId=document.getElementById("emailid").value;
	var state=document.getElementById("state").value;
	document.getElementById("error_addressline1").innerHTML = ""
	document.getElementById("error_addressline2").innerHTML = ""
	document.getElementById("error_state").innerHTML = ""
	document.getElementById("error_emailid").innerHTML = ""
	
	if (addressLine1==null || addressLine1==""){  
		  document.getElementById("error_addressline1").innerHTML = "Address Line 1 should be Present and be alphanumeric!";
		  return false;  
		  }
	 if (addressLine1.length < 5  || addressLine1.length > 20){
		 document.getElementById("error_addressline1").innerHTML = "Address Line 1 should be 5-20 Characters.!";
		  return false;  
		 
	 }
	 
	 if (addressLine2==null || addressLine2=="" ){  
		  document.getElementById("error_addressline2").innerHTML = "Address Line 2 should be Present and be alphanumeric!";
		  return false;  
		  }
	 if (addressLine1.length < 5  || addressLine1.length > 20){
		 document.getElementById("error_addressline2").innerHTML = "Address Line 2 should be 5-20 Characters.!";
		  return false;  
		 
	 }
	 
	 
	 if (state==null || state=="" || /[^a-zA-Z]/.test( state )){  
		  document.getElementById("error_state").innerHTML = "State should be Present and be alphabets!";
		  return false;  
		  }
	 
	 if (state.length < 3  || state.length > 10){
		 document.getElementById("error_state").innerHTML = "State should be 3-10 Characters.!";
		  return false;  
		 
	 }
	 
	 
	 if (!(/^\w+([\.-]?\ w+)*@\w+([\.-]?\ w+)*(\.\w{2,3})+$/.test(emailId))){
		 document.getElementById("error_emailid").innerHTML = "Please Enter a Valid email Address.!";
		  return false; 
		 
	 }
	
	

	
}

</script>


</head>

<body>
<!-- ****************Start of the container**********************-->

<div class="container">
<div class="row">

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
<tr>
</table>
</c:if>
</div>
</div>
<div class = "row">
  <!-- ****************End of the table to list**********************-->
<div class="col-md-12">
<h2>Add To Cart Data</h2>
<form:form name = "userAddressForm" method="POST" modelAttribute = "modelbind" action = "${flowExecutionUrl}&_eventId=user_save_check_out" onsubmit = "return validateform(this);" >
<table>
<tr>
<td><form:label path="userDetails">User AddressLine1</form:label></td>
<td><form:input name ="addressline1" path="userDetails.addressline1" id = "addressline1" value="${addressline1}"></form:input></td>
<td><label name ="error_addressline1" id = "error_addressline1" style="color:red" ></label></td>
</tr>
<tr>
<td><form:label path="userDetails">User AddressLine2</form:label></td>
<td><form:input name ="addressline2" path="userDetails.addressline2" id = "addressline2" value="${addressline2}" /></td>
<td><label name ="error_addressline2" id = "error_addressline2" style="color:red" ></label></td>

</tr>
<tr>
<td><form:label path="userDetails">State</form:label></td>
<td><form:input  name ="state" path="userDetails.state"  id = "state" value="${state}" /></td>
<td><label name ="error_state" id = "error_state" style="color:red" ></label></td>
</tr>
<tr>
<td><form:label path="userDetails">Email Id</form:label></td>
<td><form:input  name ="emailid" path="userDetails.emailid"  id = "emailid" value="${emailid}" /></td>
<td><label name ="error_emailid" id = "error_emailid" style="color:red" ></label></td>
</tr>

<tr>
<td colspan = "2">
<input type="submit" value="Confirm">
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