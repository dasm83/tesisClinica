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
						<th>ID</th>
						<th>Nombre Examen</th>
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
	      	¿Está seguro que desea eliminar este tipo de Examen?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	        <button id="okBtn" type="button" class="btn btn-primary">Aceptar</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->			

<script src="<c:url value="/resources/js/datatables.min.js" />"></script>
<script type="text/javascript">

var url = "catalogoExam.txt";
var urlDetail = "catalogoExamenNuevo.htm";

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
                				"<a href='#' id='delMainOnView' class='btnAct' title='Eliminar'><i class='fa fa-minus-square-o' aria-hidden='true'></i></a>"
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
var url = "exam.txt";
var urlDetail = "nuevoExamenCatalogo.htm";
</script>

</body>
</html>