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

<link href="<c:url value='/resources/css/datatables.min.css' />" rel="stylesheet">
</head>
<body>
	
<div class="input-group">
    <input type="text" id="search" class="form-control" placeholder="Buscar Paciente">
    <span class="input-group-btn" style="width:1;">
        <button id="buscarExamenes" class="btn btn-default" type="button">Buscar</button>
    </span>
</div>
<br>
	<table id="maintenanceTable" class="table table-striped table-bordered" width="100%">
		<thead>
			<tr>
				<th>ID</th>
				<th>Examen</th>
				<th>Categoria</th>
				
			</tr>
		</thead>
	</table>
	
<div class="row">
<div class="col-sm-4"></div>
  <div class="col-sm-4">
  <button id="verExamen" class="btn btn-default" type="button">Ver Examen</button> 
 <button id="verPerfil" class="btn btn-default" type="button">perfil Completo</button>
  </div>
  <div class="col-sm-4"></div> 
</div>

<form:form action="rportes.htm" id="report" class="form-horizontal" method="GET" commandName="reportForm" target="_blank">  					
		<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title"></h4>
		      </div>
		      <div class="modal-body">
			<div class="form-group">
			
			<form:input path="paciente" id="iDpaciente" cssStyle="display:none"/>
			<form:input path="examen" id="iDexamen" cssStyle="display:none"/>
			
			    <label for="pacient" class="col-lg-3 control-label">Paciente :</label>
			    	<div class="col-lg-9">
			    		<input disabled id="pacient"  class="form-control"/>
			        </div>
			</div>	
				
			<div class="form-group">
			    <label for="pacient" class="col-lg-3 control-label">Tipo Examen:</label>
			    	<div class="col-lg-9">
			    		<input disabled id="tipoExamen"  class="form-control"/>
			        </div>
			</div>	
			
			<div class="form-group">
			    <label for="pacient" class="col-lg-3 control-label">Examen:</label>
			    	<div class="col-lg-9">
			    		<input disabled id="nameExam"  class="form-control"/>
			        </div>
			</div>
		      </div>
		      <div class="modal-footer">
		  <!--        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button> -->
		        <button id="okBtn" type="submit" type="button" class="btn btn-primary" data-dismiss="modal" >Aceptar</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</form:form>
	
<script src="<c:url value="/resources/js/datatables.min.js" />"></script>
	     	    
<script type="text/javascript">
var dataTable = "#maintenanceTable";
var urlJ = "tipoExamen-ajax.json"
$.fn.dataTableExt.errMode = 'ignore';
var langUrl = "<c:url value='/resources/locales/datatable-es.json' />"
var cols = [
				{ "data": "tipo"},
				{ "data": "lab"},
				{ "data": "date"},

				];
				
var table = $(dataTable).DataTable( {
	"serverSide" : false,
	"columns": cols,
	"language":{
		"url" : langUrl
	},
	"lengthChange": false,
	"pageLength": 20,
	"searching" : false,
});

$( function() {
	$('#search').autocomplete({
		serviceUrl: 'auto.json?sugP',
		type: 'POST'
	});
 });

$("body").on('click','#verExamen',function(){  
	var row = $("table.dataTable tbody tr.active");
	var table = $('#maintenanceTable').DataTable();
	var datarow= table.row(row);
	var data = datarow.data();
	var id = data.DT_RowId;
	var personId = data.personid;
	document.getElementById("iDpaciente").value=personId;
	document.getElementById("iDexamen").value=id;
	document.getElementById("pacient").value=data.pacient;
	document.getElementById("tipoExamen").value=data.lab;
	document.getElementById("nameExam").value=data.tipo;
	$("#myModal").modal({ /// configuring modal before launching
	  backdrop: 'static'
	});
	$("#myModal").modal('show');
});

$("body").on('click','#verPerfil',function(){
	var row = $("table.dataTable tbody tr.active");
	var table = $('#maintenanceTable').DataTable();
	var datarow= table.row(row);
	var data = datarow.data();
	var id = data.personid;
	document.getElementById("iDpaciente").value=id;
	document.getElementById("iDexamen").value="completo";
	$("#myModal").modal({ /// configuring modal before launching
		  backdrop: 'static'
		});
    $("#myModal").modal('show');
    
});
</script>
</body>
</html>