<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<script src="<c:url value="/resources/js/jquery.eventCalendar.min.js"/>"></script>
<script src="<c:url value="/resources/js/moment.js"/>"></script>
<link href="<c:url value='/resources/css/datatables.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/eventCalendar.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/eventCalendar_theme_responsive.css'/>" rel="stylesheet">

</head>
<body>
<a id="" href="#"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Crear Cita</a>

<div id="myModalOnView2" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	      	<form:form id="citaForm" method="POST" commandName="citasMainForm" action="">
	     			<form:input path="idcita" id="cit" cssStyle="display:none"/>
					<div class="row">
								<div class="label col">
									<label>Paciente:</label>
								</div>
								<div class="input col">
									<form:input id="pacient" path="namePacient" cssClass="text"/>
								</div>
					</div>
					<div class="row">
								<div class="label col">
									<label>Descripcion:</label>
								</div>
								<div class="input col">
									<form:input id="descript" path="description" cssClass="text"/>
								</div>
					</div>
					<div class="row">
								<div class="label col">
									<label>Fecha:</label>
								</div>
								<div class="input col">
						<form:input id="date" path="date" cssClass="form-control datepicker" data-provide="datepicker" data-date-format="dd/mm/yyyy"/>
								</div>
					</div>
					
	     		</form:form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	        <button id="okBtn" type="button" class="btn btn-primary">Aceptar</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
<div class="container-fluid">
	<div class="row">		
		<div class="col-md-12">
			<div id="eventCalendarInline">
				
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var urlCitasj = "citas-ajax.json";
	var urlCita= "date-ajax.json";
	$(document).ready(function() {		
		$.ajax({
			url: urlCitasj,
			method: "POST",
			dataType : 'json',
			success : function(json) {	
				var eventsInline = json;
				$("#eventCalendarInline").eventCalendar({
					jsonDateFormat: 'human',
					jsonData: eventsInline
				}); 	
			},
			error: function(jqXHR, error, errorThrown){
				$("#myModalOnViewDet").find('.modal-body').html("<div style='text-align:center'><h3>Error</h3>"+jqXHR.responseText+"</div>");
				setTimeout(function(){
					$("#myModalOnViewDet").modal('hide');
				}, 4000);
			}
		});
		$("body").on('click','.bt',function(){
			var id = $(this).attr("id");
			$.ajax({
				url: urlCita,
				method: "POST",
				data: 'op=itms&id='+id,
				    dataType : 'json',
				
				    success : function(json) {	 	
		
					 var Jcita=json;
		
					$('#cit').val(Jcita[0].id);
					$('#pacient').val(Jcita[0].paciente);
					$('#descript').val(Jcita[0].descripcion);
					$('#date').val(Jcita[0].date);
					$("#myModalOnView2").modal({
						backdrop: 'static'
					});
					$("#myModalOnView2").modal('show');
				 }
		});
	});
});
</script>
</body>
</html>