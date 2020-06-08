<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


    <title>Hello, world!</title>
     <%@include file="all_js.jsp" %>
     
  </head>
  <body>
    
    <div class="container">
    
	    <%@include file="navbar.jsp" %>
	    <h1>this is home page </h1>
	    
	    <div class="card shadow bg-white py-5">
	    <img alt="" class="img-fluid mx-auto" style="width:400px" src="interface.png"/>
	    <h1 class="text-primary text-uppercase text-center mt-3">Start Taking Your Notes</h1>
	    
	    <div class="container text-center">
	        <a href="add_notes.jsp"><button class="btn btn-outline-primary text-center" >Start here</button></a>
	    </div>
	    
	    </div>
    </div>

     </body>
</html>