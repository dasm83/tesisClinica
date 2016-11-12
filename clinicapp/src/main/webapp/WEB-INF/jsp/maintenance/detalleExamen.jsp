<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>

<div class="row">

	<form:form id="detailForm" method="POST" commandName="ExamDetailForm" action="">
		<div class="col-lg-6">
			<form:label for="pacient" path="paciente">Paciente:</form:label>
			<form:input id="pacient" path="paciente" cssClass="form-control"/>
			<br>
			<c:forEach items="${items}" var="item" varStatus="loop">
				<label>${item.nombre}:</label>
				<form:input id="item${loop.index}" path="${item[loop.index]}" cssClass="form-control"/>
				<br>
			</c:forEach>
		</div>
		<div class="col-lg-6">
			<form:label for="date" path="fecha">Fecha de procesamiento:</form:label>
			<form:input id="date" path="fecha" cssClass="form-control"/>
			<br>
			<form:label for="lab" path="laboratorista">Laboratorista:</form:label>
			<form:input id="lab" path="laboratorista" cssClass="form-control"/>
			<br>
			<form:label for="obtn" path="observaciones">Observaciones:</form:label>
			<form:textarea id="obtn" path="observaciones" cssClass="form-control" rows="3"/>
		</div>
	</form:form>

</div>

</body>
</html>