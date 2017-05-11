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
		<a id="newMaintenance" href="<c:url value='/maintenance/nuevoExamenCatalogo.htm'/>"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Nuevo Examen </a>
			<table id="mainTableOnView" class="table table-striped table-bordered" width="100%">
				<thead>
					<tr>
						<th style="width: 100px;">ID</th>
						<th>Nombre Examen</th>
						<th style="width: 150px;">Acci&#243;n</th>
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
	      	¿Está seguro que desea eliminar este tipo de Examen?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	        <button id="okBtn" type="button" class="btn btn-primary">Aceptar</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->		
	
	<form action="<c:url value='/maintenance/valoresReferencia.htm'/>" method="POST">	
	<div id="myModalOnView2" class="modal fade" tabindex="-1" role="dialog">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		      </div>
		      <div class="modal-body">	
		      <p><center>Modificar valores de Referencia</center></p>
		            <select id="type" name="type" class="form-control"></select>    
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		        <button type="submit" class="btn btn-primary">Modificar</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->				
		</form>
<script src="<c:url value="/resources/js/datatables.min.js" />"></script>
<script type="text/javascript">

var urlj = "exam-ajax.json";
var url = "exam.txt";
var urlDetail = "nuevoExamenCatalogo.htm";

$(document).ready(function(){
	var table = $('#mainTableOnView').DataTable( {
		"serverSide": true,
	    "ajax":{
	    	"url": "catalogoExam-ajax.json",
	    	"type": "POST"
	    },
		"columns": [
			{ "data": "DT_RowId"},
			{ "data": "nombre"},
			{
                "class": "tableAction",
                "orderable": false,
                "data": null,
                "defaultContent": "<a href='#' id='updMainOnViewCat' class='btnAct' title='Actualizar'><i class='fa fa-pencil-square-o' aria-hidden='true'></i></a>"+
                				"<a href='#' id='delMainOnView' class='btnAct' title='Eliminar'><i class='fa fa-minus-square-o' aria-hidden='true'></i></a>"+
                				"<a href='#' id='itemsMainOnView' class='btnAct' title='Items'><i class='fa fa-flask' aria-hidden='true'></i></a>"
            }
		],
		"language":{
			"url" : "<c:url value='/resources/locales/datatable-es.json' />"
		},
		"searching": false,
		"lengthChange": false,
		"pageLength": 20
	});
	
	$("body").on('click','#itemsMainOnView',function(){
		$("#myModalOnView2").modal({ /// configuring modal before launching
		  backdrop: 'static'
		});
		$("#myModalOnView2").modal('show');
	});
	
});
		
</script>

</body>
</html>