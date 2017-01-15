<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">&#218;ltimos Ex&#225;menes</h3>
			  </div>
			  <table class="table">
			  	<tr>
			  		<th>Id</th>
			  		<th>Tipo</th>
			  		<th>Fecha</th>
			  	</tr>
				<c:forEach items="${exams}" var="ex" varStatus="loop">
					<tr>
						<td>${ex.id}</td>
						<td>${ex.catalogoExamen.nombre}</td>
						<td><fmt:formatDate value="${ex.fecha}" pattern="dd/MM/yyyy"/></td>
					</tr>
				</c:forEach>
			  </table>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="panel panel-default">
			  <div class="panel-heading">
			    <h3 class="panel-title">&#218;ltimos Pacientes</h3>
			  </div>
			  <div class="panel-body">
			    
			  </div>
			</div>
		</div>
	</div>
</body>
</html>