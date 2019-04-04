<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="includes/bootstrapMeta.jsp" />
<title>Smartphone</title>
<jsp:include page="includes/bootstrapCss.jsp" />
<link
	href="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css"
	rel="stylesheet">
</head>
<body>
	<div class="container" role="main">
 
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<form class="form-horizontal" method="post" action="changeSmartphone">
					<fieldset>
						<legend>Edit Smartphone ${smartphone.id}</legend>
 
						<!----------------  id ---------------- -->
						<div class="form-group">
							<label for="inputId" class="col-md-2 control-label">ID</label>
							<div class="col-md-10">
								<input class="form-control" id="inputId" type="text" name="id" 
								readonly value="<c:out value="${smartphone.id}"/>">
							</div>
						</div>
 
						<!----------------  name ---------------- -->
						<div class="form-group">
							<label for="inputName" class="col-md-2 control-label">Name</label>
							<div class="col-md-10">
								<input class="form-control" id="inputName" type="text" name="name" 
								value="<c:out value="${smartphone.name}"/>">
							</div>
						</div>
 
						<!----------------  brand ---------------- -->
						<div class="form-group">
							<label for="inputName" class="col-md-2 control-label">Brand</label>
							<div class="col-md-10">
								<input class="form-control" id="inputBrand" type="text" name="brand" 
								value="<c:out value="${smartphone.brand}"/>">
							</div>
						</div>
 
						<!----------------  release date ---------------- -->
						<div class="form-group">
							<label for="inputDate" class="col-md-2 control-label">Release Date</label>
							<div class="col-md-10">
								<input class="form_datetime" id="inputDate" placeholder="Date"
									type="text" readonly name="releaseDate"
									value="<fmt:formatDate value="${smartphone.releaseDate}" pattern="dd.MM.yyyy"/>">
							</div>
						</div>
						
						<!----------------  price ---------------- -->
						<div class="form-group">
							<label for="inputPrice" class="col-md-2 control-label">Price (€)</label>
							<div class="col-md-10">
								<input class="form-control" id="inputPrice" type="text" name="price" 
								value="<c:out value="${smartphone.price}"/>">
							</div>
						</div>
 
						<!----------------  buttons ---------------- -->
						<div class="form-group">
							<div class="col-md-10 col-md-offset-2">
								<button type="submit" class="btn btn-primary">Submit</button>
								<a href="listSmartphones" class="btn btn-default">Cancel</a>
							</div>
						</div>
 
					</fieldset>
				</form>
			</div>
		</div>
 
	</div>
	<!--  End of container -->
 
 
<!-- JS for Bootstrap -->
	<%@include file="includes/bootstrapJs.jsp"%>
<!-- JS for Bootstrap -->
 
 
<!-- JS for Datetime picker -->
 
	<script type="text/javascript"
		src="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
 
	<script>
		$(function() {
 
			$(".form_datetime").datetimepicker({
				format : "dd.mm.yyyy",
				autoclose : true,
				todayBtn : true,
				pickerPosition : "bottom-left",
				minView : 2
			});
 
		});
	</script>
 
</body>
</html>