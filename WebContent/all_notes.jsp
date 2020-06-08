<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@include file="all_js.jsp" %>
<%@page import="com.entities.*,java.util.*,com.servlet.*" %>
</head>


<body>
<div class="container">
    
    <%@include file="navbar.jsp" %>
    <h1>this is show notes page </h1>
    
    	<div class="row">
    		<div class="col-12">
    		
    		<c:forEach var="tempnote" items="${NOTE_LIST}">
    		
    				<c:url var="updateLink" value="SaveNoteServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="noteId" value="${tempnote.getId()}" />
					</c:url>

				
					<c:url var="deleteLink" value="SaveNoteServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="noteId" value="${tempnote.getId()}" />
					</c:url>
					
								
			  <div class="card">
			 		<img class="card-img-top m-4 mx-auto" style="max-width:100px;" src="interface.png" alt="Card image cap">
			  		<div class="card-body">
			    		<h5 class="card-title">${tempnote.getTitle()}  </h5>
			    		<p class="card-text"> ${tempnote.getContent()}</p>
			    		
			    		<div class="container text-center">
							<a href="${updateLink}" class="btn btn-danger">Update</a>  
							<a href="${deleteLink}" class="btn btn-primary"
							onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">
							Delete</a>
						</div>	
			    		
			  		</div>
			</div>

				</c:forEach>
    			
    		</div>
    	</div>
   
								
    
  </div>
</body>
</html>