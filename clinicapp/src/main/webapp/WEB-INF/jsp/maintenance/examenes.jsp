<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link href="<c:url value='/resources/css/datatables.min.css' />" rel="stylesheet">

<!-- datepicker -->
<script src="<c:url value="/resources/js/bootstrap-datepicker.min.js" />"></script>
<link href="<c:url value='/resources/css/bootstrap-datepicker3.css' />" rel="stylesheet">
</head>
<body>

	<a id="newExam" href="#"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Nuevo</a>
	<div style="float:right">
		<input id="search" name="search" class="form-control datepicker" data-provide="datepicker" data-date-format="dd/mm/yyyy" style="margin-bottom:5px"/>
		<button id="goSearch" type="button" class="btn btn-default">Buscar</button>
		<button id="cleanSearch" type="button" class="btn btn-default">Limpiar</button>
	</div>
	<table id="mainTableOnView" class="table table-striped table-bordered" width="100%">
		<thead>
			<tr>
				<th>Tipo</th>
				<th>Laboratorista</th>
				<th>Fecha</th>
				<th>Acci&#243;n</th>
			</tr>
		</thead>
	</table>
	
	<div id="myModalOnView" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	      	¿Est&#225; seguro que desea eliminar este registro?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	        <button id="okBtn" type="button" class="btn btn-primary">Aceptar</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

	<form action="<c:url value='/maintenance/detalleExamen.htm'/>" method="POST">	
		<div id="myModalOnView2" class="modal fade" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		      </div>
		      <div class="modal-body">
		      	
	      		<label>Tipo de ex&#225;men:</label>
		      	<select id="type" name="type">
					<c:forEach items="${examTypes}" var="type" varStatus="loop">
						<option value="${type.id}">${type.name}</option>
					</c:forEach>
				</select>
				
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		        <button type="submit" class="btn btn-primary">Aceptar</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</form>

<script src="<c:url value="/resources/js/datatables.min.js" />"></script>

<script type="text/javascript">

var dataTable = "#mainTableOnView";
var url = "examOp.txt";
var urlDetail = "detalleExamen.htm";
var urlJ = "exam-ajax.json";
var langUrl = "<c:url value='/resources/locales/datatable-es.json' />";
var cols = [
			{ "data": "tipo"},
			{ "data": "lab"},
			{ "data": "date"},
			{
                "class": "tableAction",
                "orderable": false,
                "data": null,
                "defaultContent": "<a href='#' id='updMainOnView' class='btnAct' title='Actualizar'><i class='fa fa-pencil-square-o' aria-hidden='true'></i></a>"
                				+"<a href='#' id='delMainOnView' class='btnAct' title='Eliminar'><i class='fa fa-minus-square-o' aria-hidden='true'></i></a>"
            }
		];

$(document).ready(function(){
	var table = $(dataTable).DataTable( {
		"serverSide": true,
	    "ajax":{
	    	"url": urlJ,
	    	"type": "POST"
	    },
		"columns": cols,
		"language":{
			"url" : langUrl
		},
		"searching": false,
		"lengthChange": false,
		"pageLength": 20
	});
	
	$("#newExam").click(function(){
		$("#myModalOnView2").modal({ /// configuring modal before launching
		  backdrop: 'static'
		});
		$("#myModalOnView2").modal('show');
	});
	
});
</script>

</body>
</html>