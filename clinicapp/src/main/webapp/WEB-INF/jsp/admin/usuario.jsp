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

	<a id="newMaintenance" href="#"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Nuevo</a>
	<a id="delMaintenance" href="#" style="margin-left:10px"><i class="fa fa-minus-square-o" aria-hidden="true"></i> Eliminar</a>
	<a id="updMaintenance" href="#" style="margin-left:10px"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Modificar</a>
	<table id="maintenanceTable" class="table table-striped table-bordered" width="100%">
		<thead>
			<tr>
				<th>Usuario</th>
				<th>Rol</th>
				<th>Habilitado</th>
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


	<form:form id="maintenanceForm" method="POST" commandName="UsuariosMainForm" action="">
		<div id="modalContent" style="display:none"> <!-- This div's content is gonna be placed inside modal -->
			<div class="row">
				<div class="label col">
					<label>Usuario:</label>
				</div>
				<div class="input col">
					<form:input path="username" cssClass="text"/>
				</div>
				<div class="label col">
					<label>Contrase&#241;a:</label>
				</div>
				<div class="input col">
					<form:input path="password" cssClass="text"/>
				</div>
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
					<label>Rol:</label>
				</div>
				<div class="input col">
					<form:select path="role" cssClass="text"/>
				</div>
			</div>
			<div class="row">
				<div class="label col">
					<label>Habilitado:</label>
				</div>
				<div class="input col">
					<form:checkbox path="enabled"/>
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
	    	"url": "user-ajax.json",
	    	"type": "POST"
	    },
		"columns": [
			{ "data": "usuario"},
			{ "data": "rol"},
			{ "data": "habilitado"},
		],
		"columnDefs": [
		    {
		    	"render": function ( data, type, row ) {
		    		if(data == "true"){
		    			return "<i class='fa fa-check' aria-hidden='true'></i>";
		    		} else{
		    			return "<i class='fa fa-times' aria-hidden='true'></i>";
		    		}
                },
                "targets": 2
		    }
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
				"last": "&#218;ltima"
			}
		},
		"searching": false,
		"lengthChange": false,
		"pageLength": 20
	});
	
});

var title = "Usuario";
var url = "userOp.txt";

//override
function loadData(dialog){
	var row = $("table.dataTable tbody tr.active");	
	var table = $('#maintenanceTable').DataTable();
	var datarow= table.row(row);
	var data = datarow.data();
	var id = data.DT_RowId;
	dialog.find("#id_temp").val(id);
	dialog.find("#names_temp").val(data.nombres);
	dialog.find("#surnames_temp").val(data.apellidos);
	dialog.find("#job_temp").val(data.profesion);
	dialog.find("#age_temp").val(data.edad);
	dialog.find("#dui_temp").val(data.dui);
	dialog.find("#nit_temp").val(data.nit);
	dialog.find("#jvplc_temp").val(data.jvplc);
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