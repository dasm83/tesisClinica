<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link href="<c:url value='/resources/css/datatables.min.css' />" rel="stylesheet">
</head>
<body>

	<a id="newMaintenance" href="#"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Laboratorista Paciente</a>
	<a id="delMaintenance" href="#" style="margin-left:10px"><i class="fa fa-minus-square-o" aria-hidden="true"></i> Eliminar Laboratorista</a>
	<a id="updMaintenance" href="#" style="margin-left:10px"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Modificar Laboratorista</a>
	<table id="maintenanceTable" class="table table-striped table-bordered" width="100%">
		<thead>
			<tr>
				<th>Nombres</th>
				<th>Apellidos</th>
				<th>profesion</th>
				<th>Edad</th>
				<th>DUI</th>
				<th>NIT</th>
			</tr>
		</thead>
	</table>

	<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title"></h4>
	      </div>
	      <div class="modal-body">
	      	<!-- we'll be placing our content here -->
	      	
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	        <button id="okBtn" type="button" class="btn btn-primary">Aceptar</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->


	<form:form id="maintenanceForm" method="POST" commandName="laboratoristaMainForm" action="">
		<div id="modalContent" style="display:none"> <!-- This div's content is gonna be placed inside modal -->
			<div style="display:none">
				<form:input path="id"/>
			</div>
			<div class="row">
				<div class="label col">
					<label>Nombres:</label>
				</div>
				<div class="input col">
					<form:input path="names" cssClass="text"/>
				</div>
			</div>
			<div class="row">
				<div class="label col">
					<label>Apellidos:</label>
				</div>
				<div class="input col">
					<form:input path="surnames" cssClass="text"/>
				</div>
			</div>
			<div class="row">
				<div class="label col">
					<label>Profesión:</label>
				</div>
				<div class="input col">
					<form:input path="job" cssClass="text"/>
				</div>
			</div>
			<div class="row">
				<div class="label col">
					<label>Edad:</label>
				</div>
				<div class="input col">
					<form:input path="age" cssClass="number"/>
				</div>
			</div>
			<div class="row">
				<div class="label col">
					<label>DUI:</label>
				</div>
				<div class="input col">
					<form:input path="dui" cssClass="number"/>
				</div>
			</div>
			<div class="row">
				<div class="label col">
					<label>NIT:</label>
				</div>
				<div class="input col">
					<form:input path="nit" cssClass="number"/>
				</div>
			</div>
			<div class="row">
				<div class="label col">
					<label>Junta de Vigilancia:</label>
				</div>
				<div class="input col">
					<form:input path="jvplc" cssClass="number"/>
				</div>
			</div>
		</div>
		
		<form:input path="action" cssStyle="display:none"/>
	</form:form>
<script src="<c:url value="/resources/js/datatables.min.js" />"></script>

<script type="text/javascript">
$(document).ready(function(){
	var table = $('#maintenanceTable').DataTable( {
		"serverSide": true,
	    "ajax":{
	    	"url": "lab-ajax.json",
	    	"type": "POST"
	    },
		
		"columns": [
			{ "data": "nombres"},
			{ "data": "apellidos"},
			{ "data": "profesion"},
			{ "data": "edad"},
			{ "data": "dui"},
			{ "data": "nit"}
		],
		"language":{
			"info": "Mostrando  _START_ a _END_ de _MAX_",
			"infoEmpty": "Mostrando  0 a 0 de _TOTAL_",
			"zeroRecords": "No se encontraron registros",
			"infoFiltered": " - filtrando de _MAX_ registros",
			"paginate": {
				"previous": "Anterior",
				"next": "Siguiente",
				"first": "Primera",
				"last": "Última"
			}
		},
		"searching": false,
		"lengthChange": false,
		"pageLength": 20
	});
});

var title = "Laboratorista";
var url = "lab.txt";

//override
function loadData(dialog){
	var row = $("table.dataTable tbody tr.active");
	dialog.find("#id_temp").val(row.children("td:eq(0)").text());
	dialog.find("#names_temp").val(row.children("td:eq(1)").text());
	dialog.find("#surnames_temp").val(row.children("td:eq(2)").text());
	dialog.find("#job_temp").val(row.children("td:eq(3)").text());
	dialog.find("#age_temp").val(row.children("td:eq(4)").text());
	dialog.find("#dui_temp").val(row.children("td:eq(5)").text());
	dialog.find("#nit_temp").val(row.children("td:eq(6)").text());
	dialog.find("#jvplc_temp").val(row.children("td:eq(7)").text());
}

//override
function validate(){
	var valid = true;
	
	if(!$("#myModal #names_temp").val()){
		$("#myModal #names_temp").addClass("error");
		valid = false;
	}
	if(!$("#myModal #surnames_temp").val()){
		$("#myModal #surnames_temp").addClass("error");
		valid = false;
	}
	if(!$("#myModal #dui_temp").val()){
		$("#myModal #dui_temp").addClass("error");
		valid = false;
	}
	if(!$("#myModal #nit_temp").val()){
		$("#myModal #nit_temp").addClass("error");
		valid = false;
	}
	if(!$("#myModal #jvplc_temp").val()){
		$("#myModal #jvplc_temp").addClass("error");
		valid = false;
	}
	
	return valid;
}
</script>
</body>
</html>