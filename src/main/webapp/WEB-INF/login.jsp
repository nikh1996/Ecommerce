
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file = "header.jsp"  %>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<script>
function validateform(){

	var productImage=document.getElementById("username").value;  
	document.getElementById("error_productImage").innerHTML = ""

	
	if (productImage==null || productImage==""){  
		  document.getElementById("error_username").innerHTML = "Username Cannot be empty.";
		  return false;  
		  }
}

</script>

<body style="background-image: url(images/wood16.jpg); background-size: cover;">
  
<div style="text-align:center">
<font color = "red"> ${msg} </font>
</div>
<br>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
<font color="red">
Your login attempt was not successful due to <br/><br/>
<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
<label name ="error_username" id = "error_username" style="color:red" >
</font>
</c:if>

<div class="container text-center">
<div class="thumbnail">
<br><br><br>
  <form class="form-horizontal" action='/Ecommerce/j_spring_security_check' method="POST">
  <div class="row">
  <div class="col-sm-4"></div>
  <div class="col-sm-4">
 
<img src="images/login.png" alt="Logosmall" width="400px"><br>
    <div class="form-group">
      <label class="control-label" for="user">Username:</label>
             <input type="text" class="form-control" name="username" id="username" placeholder="Enter your username" required>
         </div>
    <div class="form-group">
      <label class="control-label" for="pwd">Password:</label>
              <input type="password" class="form-control" name= "password" id="password" placeholder="Enter password" required>
      </div>
    <div class="form-group">
     <button type="submit" class="btn btn-default" value="Submit">Submit</button>  
    </div>
	</div>
	</div>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
<font color="red">
Your login attempt was not successful due to <br/><br/>
<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
</font>
</c:if>
  </form>
  	</div>
</div>


</body>
</html>
<%@include file = "footer.jsp"  %>