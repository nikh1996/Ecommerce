<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file = "header.jsp"  %>
<!DOCTYPE html>
<html>
<body>
<div class = "container">
<div class = "row text-center">
<h2> Products in the Category <c:out value="${category.name}" /></h2>
<h3> Sub-category is <c:out value="${category.subcategory}" /></h3>
<h4> <c:out value="${category.description}" /></h4>
<br><br>
</div>


<c:forEach items="${products}" var="product"  varStatus="loop">
<c:if test="${(loop.count) % 3 == 0}">
<br>
<div class = "row">
</c:if>


<div class = "col-sm-4">
<a href = "/Ecommerce/all_show_product?id=${product.id}"><img class = img-responsive src = "images\products\<c:out value="${product.id}" />.jpg" alt = "${product.name}"></a>
<h4 class = "text-center center"><c:out value="${product.name}" /></h4>
</div>



<c:if test="${(loop.count) % 3 == 0}">
</div>
<hr>
</c:if>
</c:forEach>
</div>



</body>
</html>
<%@include file = "footer.jsp"  %>