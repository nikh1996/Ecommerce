<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file = "header_admin.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>


<script>
function validateform(){

	var productImage=document.getElementById("productImage").value;  
	document.getElementById("error_productImage").innerHTML = ""

	
	if (productImage==null || productImage==""){  
		  document.getElementById("error_productImage").innerHTML = "No File uploaded!";
		  return false;  
		  }
}

</script>


<!-- The body tag. This tag starts with the welcome message.-->
<body>


<!-- ****************Start of the container**********************-->

<div class="container">
<div class="row">


<!-- ****************Start of the form**********************-->
<div class="col-md-11">
<h2>Add Product Data</h2>
<form:form method="POST" action="/Ecommerce/admin_save_product"  enctype="multipart/form-data" onsubmit = "return validateform(this);">
<table>
<tr> 
<td><form:label path="id">Product ID:</form:label></td>
<td><c:out value="${product.id}" /></td>
<td><form:errors path="id" style="color:red"/><form:hidden path="id" value="${product.id}"/></td>
</tr>
<tr>
<td><form:label path="name">Product Name:</form:label></td>
<td><form:input path="name" value="${product.name}" /></td>
<td><form:errors path="name" style="color:red"/></td>
</tr>
<tr>
<td><form:label path="supplierid">Product Supplier:</form:label></td>
<td><form:select  path="supplierid" items="${suppliers}" itemValue="id" itemLabel="name" /></td>
<td><form:errors path="supplierid" style="color:red"/></td>
</tr>
<tr>
<td><form:label path="categoryid">Product Category:</form:label></td>
<td><form:select path="categoryid" items="${categories}" itemValue="id" itemLabel="categorySubcategory" /></td>
<td><form:errors path="categoryid" style="color:red"/></td>
</tr>
<tr>
<td><form:label path="description">Product Description:</form:label></td>
<td><form:input path="description" value="${product.description}" /></td>
<td><form:errors path="description" style="color:red"/></td>
</tr>
<tr>
<td><form:label path="mrp">Product MRP:</form:label></td>
<td><form:input path="mrp" value="${product.mrp}" /></td>
<td><form:errors path="mrp" style="color:red"/></td>
</tr>
<tr>
<td><form:label path="offerprice">Product Offer Price:</form:label></td>
<td><form:input path="offerprice" value="${product.offerprice}" /></td>
<td><form:errors path="offerprice" style="color:red"/></td>
</tr>
<tr>
<td><form:label path="quantity">Product Quantity:</form:label></td>
<td><form:input path="quantity" value="${product.quantity}" /></td>
<td><form:errors path="quantity" style="color:red"/></td>
</tr>
<tr>
<td><form:label path="imagepath">Image Upload</form:label></td>
<td><input name = "productImage" type = "file" id="productImage" path = "imagepath" /></td>
<td><label name ="error_productImage" id = "error_productImage" style="color:red" ></td>
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
<c:if test="${!empty products}">
<h2>List Products</h2>
<table class = "table">
<tr>
<th>Product ID</th>
<th>Product Name</th>
<th>Product SupplierId</th>
<th>Product CategoryId</th>
<th>Product Description</th>
<th>Product MRP</th>
<th>Product OfferPrice</th>
<th>Product Quantity</th>
<th>Action</th>
</tr>
<c:forEach items="${products}" var="product">
<tr>
<td><c:out value="${product.id}" /></td>
<td><c:out value="${product.name}" /></td>
<td><c:out value="${product.supplierid}" /></td>
<td><c:out value="${product.categoryid}" /></td>
<td><c:out value="${product.description}" /></td>
<td><c:out value="${product.mrp}" /></td>
<td><c:out value="${product.offerprice}" /></td>
<td><c:out value="${product.quantity}" /></td>
<td align="center"><a href="/Ecommerce/admin_edit_product?id=${product.id}">Edit</a>
| <a href="/Ecommerce/admin_delete_product?id=${product.id}">Delete</a></td>
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