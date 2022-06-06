<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory Management</title>
</head>
<body>
	<div class="row">
	
	<div class="container">
	
		<h3 class="text-center">List of Items</h3>
		<hr>
		<div class="container text-left">
			<a href="<%=request.getContextPath() %>/list" class="nav-link">Items List</a>
			<a href="<%=request.getContextPath() %>/new" class="btn btn-success">Add New Item</a>
		</div>
		<br>
		<table class="table tabled-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="item" items="${listItem}">
					
					<tr>
						<td><c:out value="${item.id}" /></td>
						<td><c:out value="${item.name}" /></td>
						<td><c:out value="${item.price}" /></td>
						<td><a href="edit?id=<c:out value='${item.id}' />">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="delete?id=<c:out value='${item.id}' />">Delete</a>
						</td>
					</tr>
				</c:forEach>
			
			</tbody>
		
		
		</table>
	
	</div>
	
	</div>

</body>
</html>