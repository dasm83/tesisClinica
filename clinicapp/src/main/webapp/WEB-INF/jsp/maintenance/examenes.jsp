<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link href="<c:url value='/resources/css/datatables.min.css' />" rel="stylesheet">
</head>
<body>

	<a id="newExam" href="#"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Nuevo</a>
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
	      	¿Está seguro que desea eliminar este registro?
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

var url = "examOp.txt";
var urlDetail = "detalleExamen.htm";

$(document).ready(function(){
	var table = $('#mainTableOnView').DataTable( {
		"serverSide": true,
	    "ajax":{
	    	"url": "exam-ajax.json",
	    	"type": "POST"
	    },
		"columns": [
			{ "data": "tipo"},
			{ "data": "lab"},
			{ "data": "date"},
			{
                "class": "tableAction",
                "orderable": false,
                "data": null,
                "defaultContent": "<a href='#' id='updMainOnView' class='btnAct' title='Actualizar'><i class='fa fa-pencil-square-o' aria-hidden='true'></i></a>"+
                				"<a href='#' id='delMainOnView' class='btnAct' title='Eliminar'><i class='fa fa-minus-square-o' aria-hidden='true'></i></a>"
            }
		],
		"language":{
			"url" : "<c:url value='/resources/locales/datatable-es.json' />"
		},
		"searching": false,
		"ordering": true,
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