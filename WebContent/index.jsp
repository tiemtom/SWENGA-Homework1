<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/bootstrapMeta.jsp" />
<title>Smartphones</title>
<jsp:include page="includes/bootstrapCss.jsp" />
</head>
<body>
	<div class="container" role="main">
 
		<div class="page-header">
        	<h1>Smartphone Management</h1>
      	</div>
 
		<!--  Messages  ----------------------------------------------------------- -->
 		<!--  Error message ----------------------------------------------------------- -->
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger" role="alert">${errorMessage}</div>
		</c:if>
		<!--  Error message ----------------------------------------------------------- -->
 
		<!--  Warning message ----------------------------------------------------------- -->
		<c:if test="${not empty warningMessage}">
			<div class="alert alert-warning" role="warning">${warningMessage}</div>
		</c:if>
		<!--  Warning message ----------------------------------------------------------- -->
 
		<!--  successful message ----------------------------------------------------------- -->
		<c:if test="${not empty message}">
			<div class="alert alert-success" role="warning">${message}</div>
		</c:if>
		<!--  Messages  ----------------------------------------------------------- -->
 
 
		<!--  Search bar ----------------------------------------------------------- -->
		<jsp:include page="includes/searchNav.jsp" />
		<!--  Search bar ----------------------------------------------------------- -->
 
		<!--  2 simple buttons ----------------------------------------------------------- -->
 		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<p>
					<a href="newSmartphone.jsp" class="btn btn-success">Add new Smartphone</a>
					<a href="fillSmartphoneList" class="btn btn-success">Fill List</a>
				</p>
			</div>
		</div>
		<!--  2 simple buttons ----------------------------------------------------------- -->
 
 
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
 
				<table data-toggle="table" class="table table-striped">
					<thead>
						<tr>
							<th data-sortable="true">ID</th>
							<th data-sortable="true">Name</th>
							<th data-sortable="true">Brand</th>
							<th data-sortable="true">Release Date</th>
							<th data-sortable="true">Price (€)</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
					<!--  list all smartphones ----------------------------------------------------------- -->
 					<c:forEach items="${smartphones}" var="smartphone">
						<tr>
							<td>${smartphone.id}</td>
							<td>${smartphone.name}</td>
							<td>${smartphone.brand}</td>
							<td><fmt:formatDate value="${smartphone.releaseDate}" pattern="dd.MM.yyyy"/></td>
							<td>${smartphone.price}</td>
 							<td>
								<a href="editSmartphone?id=${smartphone.id}" class="btn btn-xs btn-success">Edit</a> 
								<a href="deleteSmartphone?id=${smartphone.id}" class="btn btn-xs btn-danger">Delete</a>
							</td>
						</tr>
					</c:forEach>
					<!--  list all smartphones ----------------------------------------------------------- -->
					</tbody>
				</table>
			</div>
		</div>
 
 
	</div>	<!--  End of container -->
 
<!-- JS for Bootstrap -->
	<%@include file="includes/bootstrapJs.jsp"%>
<!-- JS for Bootstrap -->
 
</body>
</html>