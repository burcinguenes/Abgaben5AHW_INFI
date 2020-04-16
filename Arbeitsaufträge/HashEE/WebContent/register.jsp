<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <title>Registration</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  
  <style>
    
    .jumbotron{
    background-color: #D1F2EB; }  
    
  </style>
  
</head>
<body>


<div class="container">

<div class="jumbotron text-center"> <h2>Register</h2> </div>

<tr>
	<td>
		<h3 style="color: red;">${message}</h3>
		<h3 style="color: green;">${successMessage}</h3>
	</td>
	
	<td></td>
</tr>

<form method="post" action="LoginRegister">
  
  <div class="form-group">
  	<label for="username">Username:</label>
  	<input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
  </div>

 
 <div class="form-group">
  	<label for="password">Password:</label>
  	<input type="password" class="form-control" id="password" placeholder="Enter password" name="pwd1">
  </div>
  
  
   <button type="submit" class="btn btn-primary" name="submit" value="register"> Register </button>
   <a href="login.jsp">Back to Login</a>
</form> 
</div>


</body>
</html>