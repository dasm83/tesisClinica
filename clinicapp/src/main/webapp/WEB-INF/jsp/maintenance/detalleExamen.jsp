<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<script src="<c:url value="/resources/js/jquery.autocomplete.js" />"></script>
<link href="<c:url value='/resources/css/autocomplete.css' />" rel="stylesheet">
</head>
<body>

<div class="row">

	<form:form id="detailForm" method="POST" commandName="ExamDetailForm" 
	action="<c:url value='/maintenance/examOp.txt?op=iou'/>">
		<div class="row">
			<div class="col-lg-6">
				<form:label for="pacient" path="paciente">Paciente:</form:label>
				<form:input id="pacient" path="paciente" cssClass="form-control"/>
				<br>
				<form:label for="date" path="fecha">Fecha de procesamiento:</form:label>
				<form:input id="date" path="fecha" cssClass="form-control"/>
				<br>
				<form:label for="lab" path="laboratorista">Laboratorista:</form:label>
				<form:input id="lab" path="laboratorista" cssClass="form-control"/>
				<br>
				<form:label for="obtn" path="observaciones">Observaciones:</form:label>
				<form:textarea id="obtn" path="observaciones" cssClass="form-control" rows="3"/>
			</div>
			<div class="col-lg-6">
				<c:forEach items="${ExamDetailForm.items}" var="item" varStatus="loop">
					<label><c:out value="${item.nombre}" />:</label>
					<input name="ExamDetailForm[${loop.index}].valor" value="${item.valor}" class="form-control"/>
					<br>
				</c:forEach>
			</div>
		</div>
		<div class="row">
			<button type="button" class="btn btn-primary pull-right" style="margin-top:40px; margin-right:15px">Cancelar</button>
			<button id="saveBtn" type="submit" class="btn btn-primary pull-right" style="margin-top:40px; margin-right:15px">Guardar</button>
		</div>
		<form:input path="examId" cssStyle="display:none"/>
	</form:form>

</div>
<script type="text/javascript">
$( function() {
	$('#pacient').autocomplete({
		serviceUrl: 'exam-ajax.json?sug',
		type: 'POST'
	});
});
</script>
</body>
</html>