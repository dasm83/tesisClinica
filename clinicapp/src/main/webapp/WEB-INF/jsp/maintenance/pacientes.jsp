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
	<div style="float:right">
		<input id="search" class="text form-control" style="margin-bottom:5px"/>
		<button id="goSearch" type="button" class="btn btn-default">Buscar</button>
		<button id="cleanSearch" type="button" class="btn btn-default">Limpiar</button>
	</div>
	<table id="maintenanceTable" class="table table-striped table-bordered" width="100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombres</th>
				<th>Apellidos</th>
			</tr>
		</thead>
	</table>
		
	<form:form id="maintenanceForm" method="POST" commandName="pacientesMainForm" action="">
		<div class="col-md-6" id="modalContent" style="display:none" >
            <div class="panel with-nav-tabs panel-default">
                <div class="panel-heading">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1default" data-toggle="tab">Informaci&#243;n del Paciente</a></li>
                            <li><a href="#tab2default" data-toggle="tab">Contactos</a></li>                          
                        </ul>
                </div>
				<div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab1default">
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
									<label>Sexo:</label>
								</div>
								<div class="input col">
									<form:select path="sex">
										<form:option value="M">M</form:option>
										<form:option value="F">F</form:option>
									</form:select>
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
									<label>Profesi&#243;n:</label>
								</div>
								<div class="input col">
									<form:input path="job" cssClass="text"/>
								</div>
							</div>
							<div class="row">
								<div class="label col">
									<label>Nacionalidad:</label>
								</div>
								<div class="input col">
									<form:input path="nation" cssClass="text"/>
								</div>
							</div>
							<div class="row">
								<div class="label col">
									<label>Estado Civil:</label>
								</div>
								<div class="input col">
									<form:input path="maritalStatus" cssClass="text"/>
								</div>
							</div>
						</div>
			 <div class="tab-pane fade" id="tab2default">
			 				<div class="row">
								<div class="label col">
									<label>Departamento:</label>
								</div>
								<div class="input col">
									<form:input path="departament" cssClass="text"/>
								</div>
							</div>
			 				<div class="row">
								<div class="label col">
									<label>Municipio:</label>
								</div>
								<div class="input col">
									<form:input path="municipio" cssClass="text"/>
								</div>
							</div>
							<div class="row">
								<div class="label col">
									<label>Direcci&#243;n:</label>
								</div>
								<div class="input col">
									<form:input path="address" cssClass="text"/>
								</div>
							</div>
							<div class="row">
								<div class="label col">
									<label>Tel&#233;fono:</label>
								</div>
								<div class="input col">
									<form:input path="phone" cssClass="text"/>
								</div>
							</div>
							<div class="row">
								<div class="label col">
									<label>Email:</label>
								</div>
								<div class="input col">
									<form:input path="email" cssClass="text"/>
								</div>
							</div>	 
			 </div>
                    </div>
                </div>
            </div>
        </div>
		<form:input path="action" cssStyle="display:none"/>
		
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
	</form:form>
<script src="<c:url value="/resources/js/datatables.min.js" />"></script>

<script type="text/javascript">

var dataTable = "#maintenanceTable";
var title = "Paciente";
var url = "paciente.txt";
var urlJ = "paciente-ajax.json";
var langUrl = "<c:url value='/resources/locales/datatable-es.json' />"
var cols = [
			{ "data": "DT_RowId"},
			{ "data": "nombres"},
			{ "data": "apellidos"}
		];

$(document).ready(function(){
	var table = $(dataTable).DataTable( {
		"serverSide" : true,
		"ajax" : {
			"url" : urlJ,
			"type": "POST"
		},
		
		"columns": cols,
		"language":{
			"url" : langUrl
		},
		"lengthChange": false,
		"pageLength": 20,
		"searching" : false
	});
});

// override
function loadData(dialog){
	var row = $("table.dataTable tbody tr.active");
	var table = $('#maintenanceTable').DataTable();
	var datarow= table.row(row);
	var data = datarow.data();
	var id = data.DT_RowId;
	dialog.find("#id_temp").val(id);
	dialog.find("#names_temp").val(data.nombres);
	dialog.find("#surnames_temp").val(data.apellidos);
	dialog.find("#sex_temp").val(data.sexo);
	dialog.find("#age_temp").val(data.edad);
	dialog.find("#dui_temp").val(data.dui);
	dialog.find("#nit_temp").val(data.nit);
	dialog.find("#job_temp").val(data.profesion);
	dialog.find("#nation_temp").val(data.nacionalidad);
	dialog.find("#maritalStatus_temp").val(data.estCivil);
	dialog.find("#municipio_temp").val(data.municipio);
	dialog.find("#departament_temp").val(data.departamento);
	dialog.find("#address_temp").val(data.direccion);
	dialog.find("#phone_temp").val(data.telefono);
	dialog.find("#email_temp").val(data.email);
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
	
	return valid;
}
</script>
</body>
</html>