<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory Management</title>
</head>
<body>
<br>
	<div class="container col-md-8">
	
		<div class="card">
		
			<div class="card-body">
				<c:if test="${item != null}">
					<form action="update" method="post">
				</c:if>
				
				<c:if test="${item == null}">
					<form action="insert" method="post">
				</c:if>
				
				<caption>
					<h2>
						<c:if test="${item != null}">
						Edit Item
						</c:if>
						<c:if test="${item == null}">
						Add New Item
						</c:if>
					</h2>
				</caption>
				
				<c:if test="${item != null}">
					<input type="hidden" name="id" value="<c:out value='${item.id}' />" />
				</c:if>
				
				<fieldset class="form-group">
				
					<label>Item Name</label>
					<input type="text" value="<c:out value='${item.name}' />" class="form-control" name="name" required="required">
				
				</fieldset>
				
				<fieldset class="form-group">
				
					<label>Item Price</label>
					<input type="text" value="<c:out value='${item.price}' />" class="form-control" name="price" required="required">
				
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				<a href="<%=request.getContextPath() %>/list" class="text-right">Items</a>
				
				</form>
				</form>
				
			</div>
		
		</div>
	
	</div>


</body>
</html>