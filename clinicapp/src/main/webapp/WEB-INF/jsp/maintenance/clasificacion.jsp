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

	<a id="newMaintenance" href="#"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Nuevo Paciente</a>
	<a id="delMaintenance" href="#" style="margin-left:10px"><i class="fa fa-minus-square-o" aria-hidden="true"></i> Eliminar Paciente</a>
	<a id="updMaintenance" href="#" style="margin-left:10px"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Modificar Paciente</a>
	<table id="maintenanceTable" class="table table-striped table-bordered" width="100%">
		<thead>
			<tr>
				<th>Id</th>
				<th>Categoria</th>
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
	
	<form:form id="maintenanceForm" method="POST" commandName="clasificacionMainForm" action="">
	<div id="modalContent" style="display:none"> <!-- This div's content is gonna be placed inside modal -->
			<div style="display:none">
				<form:input path="id"/>
			</div>
			<div class="row">
				<div class="label col">
					<label>Categoria:</label>
				</div>
				<div class="input col">
					<form:input path="category" cssClass="text"/>
				</div>
			</div>
		</div> 
		
		<form:input path="action" cssStyle="display:none"/>
	</form:form>
<script src="<c:url value="/resources/js/datatables.min.js" />"></script>

	<script type="text/javascript">
$(document).ready(function(){
	var data =eval('${categoryList}');
	var table = $('#maintenanceTable').DataTable( {
		"aaData": data,
		
		"aoColumns": [
			{ "mData": "id"},
			{ "mData": "categoria"}
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
			},
			"search": "Buscar:"
		},
		"lengthChange": false
	
	});
});
			
	

var title = "Clasificacion";
var url = "clasificacion-ajax.htm";

// override
function loadData(dialog){
	var row = $("table.dataTable tbody tr.active");
	dialog.find("#id_temp").val(row.children("td:eq(0)").text());
	dialog.find("#category_temp").val(row.children("td:eq(1)").text());
	dialog.find("#description_temp").val(row.children("td:eq(2)").text());
}

//override
function validate(){
	var valid = true;
	
	if(!$("#myModal #category_temp").val()){
		$("#myModal #category_temp").addClass("error");
		valid = false;
	}
	if(!$("#myModal #description_temp").val()){
		$("#myModal #description_temp").addClass("error");
		valid = false;
	}
	
	return valid;
}
</script>
</body>
</html>