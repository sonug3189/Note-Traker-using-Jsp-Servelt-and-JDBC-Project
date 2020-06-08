<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Notes</title>
     <%@include file="all_js.jsp" %>
     <%@page import="com.entities.*,java.util.*,com.servlet.*" %>
</head>
<body>

<div class="container">
    
        <h1>this is update notes page </h1>
    
    
<form action="SaveNoteServlet" method="GET">
	<input type="hidden" name="command" value="UPDATE" />
  <div class="form-group">
      <label for="title">${THE_NOTE.id}</label>
  	<input type="hidden" name="id" value="${THE_NOTE.id}" />
  	<br>
    <label for="title">Title</label>
    <input type="text" name="title"
     class="form-control" 
     id="title"
     required 
     value="${THE_NOTE.title}"
     aria-describedby="emailHelp">
    </div>
  <div class="form-group">
    <label for="content">Content</label>
    <textarea name="content"  required id="content"  class="form-control"  style="height:300px">${THE_NOTE.content}</textarea>
  </div>
  
  <div class="container text-center">
  <button type="submit" class="btn btn-primary">Submit</button>
  </div>
  
</form>
    
    </div>
</body>
</html>