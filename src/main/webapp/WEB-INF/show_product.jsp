<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file = "header.jsp"  %>
<!DOCTYPE html>
<html>
<body>
<div class="container">
<div class="row">
<div class="col-sm-4">
<img class = img-responsive src = "images\products\<c:out value="${product.id}" />.jpg" alt = "${product.name}">
<table class = "table">
<tbody>
<tr>
<td><a href = "/Ecommerce/all_landing"><input type = "submit" value = "CONTINUE SHOPPING"></a></td>
<td><a href = "/Ecommerce/user_add_cart?productId=${product.id}"><input type = "submit" value = "ADD TO CART"></a> </td>
</tr>
</tbody>
</table>
</div>
<div class="col-sm-8">
<table class = "table table-striped">
<thead>
<tr>
<th class = "col-xs-4">
Detail
</th>
<th class = "col-xs-8">
Description
</th>
</tr>
</thead>
<tbody>

<tr>
<td>Product Name</td>
<td><c:out value="${product.name}" /></td>
</tr>

<tr>
<td>Product description</td>
<td><c:out value="${product.description}" /></td>
</tr>

<tr>
<td>Product MRP</td>
<td><c:out value="${product.mrp}" /></td>
</tr>

<tr>
<td>Product Offer Price</td>
<td><c:out value="${product.offerprice}" /></td>
</tr>

<tr>
<td>Product Category</td>
<td><c:out value="${productCategory.name}" /></td>
</tr>

<tr>
<td>Product Sub Category</td>
<td><c:out value="${productCategory.subcategory}" /></td>
</tr>

<tr>
<td>Product Supplied By</td>
<td><c:out value="${supplierCategory.name}" /></td>
</tr>



</tbody>
</table>
</div>
</div>
</div>
</body>
</html>
<%@include file = "footer.jsp"  %>