<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<!-- autocomplete -->
<script src="<c:url value="/resources/js/jquery.autocomplete.js" />"></script>
<link href="<c:url value='/resources/css/autocomplete.css' />" rel="stylesheet">

<!-- datepicker -->
<script src="<c:url value="/resources/js/bootstrap-datepicker.min.js" />"></script>
<link href="<c:url value='/resources/css/bootstrap-datepicker3.css' />" rel="stylesheet">
</head>
<body>

<div class="row">

	<form:form id="detailForm" method="POST" commandName="ExamDetailForm" 
	action="examOp.txt?op=iou">
		<div class="row">
			<div class="col-lg-6">
				<form:label for="pacient" path="paciente">Paciente:</form:label>
				<form:input id="pacient" path="paciente" cssClass="form-control"/>
				<br>
				<form:label for="date" path="fecha">Fecha de procesamiento:</form:label>
				<form:input id="date" path="fecha" cssClass="form-control datepicker" data-provide="datepicker" data-date-format="dd/mm/yyyy"/>
				<br>
				<form:label for="lab" path="laboratorista">Laboratorista:</form:label>
				<form:input id="lab" path="laboratorista" cssClass="form-control"/>
				<br>
				<form:label for="obtn" path="observaciones">Observaciones:</form:label>
				<form:textarea id="obtn" path="observaciones" cssClass="form-control" rows="3"/>
			</div>
			<div class="col-lg-6">
				<c:forEach items="${ExamDetailForm.items}" var="item" varStatus="loop">
					<input type="hidden" name="items[${loop.index}].nombre" value="${item.nombre}"/>
					<form:label for="items[${loop.index}].valor" path="items[${loop.index}].nombre"><c:out value="${item.nombre}" />:</form:label>
					<input name="items[${loop.index}].valor" value="${item.valor}" class="form-control"/>
					<br>
				</c:forEach>
			</div>
		</div>
		<div class="row">
			<button id="cancelBtn" type="button" class="btn btn-primary pull-right" style="margin-top:40px; margin-right:15px">Cancelar</button>
			<button id="saveBtn" type="button" class="btn btn-primary pull-right" style="margin-top:40px; margin-right:15px">Guardar</button>
		</div>
		<form:input path="examId" cssStyle="display:none"/>
		<form:input path="examType" cssStyle="display:none"/>
	</form:form>

</div>

	<div id="myModalOnViewDet" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	      	
	      </div>
	      <div class="modal-footer">
	        <button id="okBtn" type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<script type="text/javascript">
var url = "examOp.txt";
var mainURL = "examenes.htm"

$( function() {
	$('#pacient').autocomplete({
		serviceUrl: 'auto.json?sugP',
		type: 'POST'
	});
	
	$('#lab').autocomplete({
		serviceUrl: 'auto.json?sugL',
		type: 'POST'
	});
});
</script>
</body>
</html>