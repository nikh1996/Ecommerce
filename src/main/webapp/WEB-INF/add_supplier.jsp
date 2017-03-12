
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file = "header_admin.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<!-- ****************Start of the container**********************-->

<div class="container">
<div class="row">


<!-- ****************Start of the form**********************-->
<div class="col-md-12">
	<h2>Add Supplier Data</h2>
	<form:form method="POST" action="/Ecommerce/admin_save_supplier">
<table>
<tr>
	<td><form:label path="id">Supplier ID:</form:label></td>
<td><c:out value="${supplier.id}" /></td>
<td><form:errors path="id" style="color:red"/><form:hidden path="id" value="${supplier.id}"/></td>
</tr>
<tr>
<td><form:label path="name">Supplier Name:</form:label></td>
<td><form:input path="name" value="${supplier.name}" /></td>
<td><form:errors path="name" style="color:red"/></td>
</tr>
<tr>
	<td><form:label path="description">Supplier Description:</form:label></td>
<td><form:input path="description" value="${supplier.description}" /></td>
<td><form:errors path="description" style="color:red"/></td>
</tr>
<tr>
	<td colspan="2"><input type="submit" value="Submit" /></td>
</tr>
</table>
</form:form>
</div>
<!-- ****************End of the form**********************-->
     
     
</div>
<hr />
<div class="row">


	  <!-- ****************Start of the Table to list**********************-->
<div class="col-sm-12">
	<c:if test="${!empty suppliers}">
<h2>List Suppliers</h2>
<table class = "table">
<tr>
  		<th>Supplier ID</th>
	<th>Supplier Name</th>
	<th>Supplier Description</th>
	<th>Action</th>
</tr>
<c:forEach items="${suppliers}" var="supplier">
<tr>
	<td><c:out value="${supplier.id}" /></td>
<td><c:out value="${supplier.name}" /></td>
<td><c:out value="${supplier.description}" /></td>
<td align="center"><a href="/Ecommerce/admin_edit_supplier?id=${supplier.id}">Edit</a>
| <a href="/Ecommerce/admin_delete_supplier?id=${supplier.id}">Delete</a></td>
</tr>
</c:forEach>
</table>
</c:if>
</div>
  <!-- ****************End of the table to list**********************-->
	  
	  
</div>
</div>
  <!-- ****************End of the container**********************-->
<hr />

 
</body>
</html>
<%@include file = "footer.jsp"  %>