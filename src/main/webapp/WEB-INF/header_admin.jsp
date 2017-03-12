<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<!-- Head tag stores the title and the relevant libraries that are needed.-->
<head>
<title>Welcome to Thrift Shop!</title>
   
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<link  rel="stylesheet" type = "text/css" href="/Ecommerce/bootstrap/css/bootstrap.min.css" >
<script type="text/javascript" src="/Ecommerce/bootstrap/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="/Ecommerce/bootstrap/js/bootstrap.min.js"></script>

<!-- Start of style, These classes are for the carousel buttons -->
<style>
.horizontal {
    display: flex;
    justify-content: center;
}

.jumbotron {
background-image: url("images/Wood.jpg");
margin-bottom:0;
}

.navbar {
    margin-bottom: 0;
	background-image: url("images/Wood2.jpg");
    border: 0;
    opacity:0.9;
	padding: 20px,20px;
}   
#carouselButtons {
margin-left: 100px;
position: absolute;
bottom: 0px;
}
.carousel-caption {
position: relative;
left: auto;
right: auto;
color: black;
}
</style>
<!-- End of style -->      
</head>


<body>

<!-- This is the title message in the body that is to be displayed in all pages -->
<div class="jumbotron text-center">
<center><a href="/Ecommerce/all_landing"><img src="images/logo.png" class="img-responsive"></a></center><br>
<p>Hey ${currentUser}!
</p> 
<p color = "blue">${msg}</p>
</div>




<!-- ****************Start of the navigation bar**********************-->
<nav class="navbar navbar-default" role="navigation">
<div class="navbar-header">
  <button type="button" class="navbar-toggle" data-toggle="collapse"
	data-target="#example-navbar-collapse">
	<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
	<span class="icon-bar"></span> <span class="icon-bar"></span>
  </button>
	<a class="navbar-brand" href="/Ecommerce/all_landing">HOME</a>
	
</div>

<div class="collapse navbar-collapse" id="example-navbar-collapse">
<ul class="nav navbar-nav">
	<a class="navbar-brand" href="/Ecommerce/admin_add_category">CATEGORY</a>
</ul>
<ul class="nav navbar-nav">
	<a class="navbar-brand" href="/Ecommerce/admin_add_supplier">SUPPLIER</a>
</ul>
<ul class="nav navbar-nav">
	<a class="navbar-brand" href="/Ecommerce/admin_add_product">PRODUCT</a>
</ul>
    
<ul>
	<a class="navbar-brand pull-right" href="/Ecommerce/j_spring_security_logout">Logout</a>
</ul>
</div>
</nav>
<!-- ****************End of the navigation bar**********************-->


</body>
</html>