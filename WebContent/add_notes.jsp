<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Notes</title>
     <%@include file="all_js.jsp" %>
</head>
<body>

<div class="container">
    
    <%@include file="navbar.jsp" %>
    <h1>this is add notes page </h1>
        
		<form action="SaveNoteServlet" method="get">
			<input type="hidden" name="command" value="ADD" />
			  <div class="form-group">
			    <label for="title">Note Title</label>
			    <input type="text" name="title"
			     class="form-control" 
			     id="title"
			     required 
			     aria-describedby="emailHelp"
			      placeholder="Enter here">
			    </div>
			  <div class="form-group">
			    <label for="content">Note Content</label>
			    <textarea name="content" required id="content" placeholder="enter content here" class="form-control" style="height:300px"></textarea>
			  </div>
			  <div class="container text-center">
			  <button type="submit" class="btn btn-primary">Submit</button>
			  </div>
		</form>
		    
    </div>
</body>
</html>