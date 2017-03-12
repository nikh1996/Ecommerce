<!DOCTYPE html>
<html lang="en">
<head>
<title>Contact</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"></script>
  <script src="https://use.fontawesome.com/64a59fcb64.js"></script>

  <style>
 
.h2 {
padding: 35px 8px 35px 20px;
}

.thumbnail {
padding: 35px 8px 0px 20px;
height: 165px;
}
  </style>
  
  </head>
<%@ include file="../header.jsp" %>  
<body style="background-image: url(images/paper.jpg);">

<div id="contact" class="container-fluid">
<div class="container-fluid bg-grey text-center">
<center><img src="images/contact.png" class="img-responsive text-center horizontal" height ="198px" width="280px"><br><br><br></center>
<div class="row">
<div class="col-sm-2">
<div class="thumbnail">
<img src="images/logosmall.png" alt="Logosmall" width="500px" height="500px">
</div>
</div>
<div class="col-sm-8">
<div class="row">
<div class="col-sm-6 form-group">
<input class="form-control" id="name" name="name" Placeholder="Name" type="text" required>
</div>
<div class="col-sm-6 form-group">
<input class="form-control" id="email" name="email" Placeholder="Email" type="email" required>
</div>
</div>
<textarea class="form-control" id="comment" name="comment" placeholder="Write your comment here!" rows="5"></textarea><br>
<div class="row">
<div class="form-group col-sm-12">
<button class="btn btn-default pull-right" type="submit">Submit</button>
</div>
</div>
</div>
</div>
</div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>