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
<!-- datepicker -->
<script src="<c:url value="/resources/js/bootstrap-datepicker.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datetimepicker.js" />"></script>
<link href="<c:url value='/resources/css/bootstrap-datepicker3.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-datetimepicker-standalone.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-datetimepicker.css'/>" rel="stylesheet">

</head>
<body>
<a id="newDate" href="#"><i class="fa fa-plus-square-o" aria-hidden="true"></i> Crear Cita</a>

<div id="myModalOnView2" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	      	<form:form id="citaForm" class="form-horizontal" role="form" method="POST" commandName="citasMainForm" action="">
	     	<form:input path="idcita" id="cit" cssStyle="display:none"/>			
	     			
			<div class="form-group">
			    <label for="pacient" class="col-lg-2 control-label">Paciente :</label>
			    	<div class="col-lg-10">
			    		<form:input id="pacient" path="namePacient" class="form-control"/>
			        </div>
			</div>		
			<div class="form-group">
				<label for="descript" class="col-lg-2 control-label">Examen :</label>
				<div class="col-lg-10">
				<form:input id="descript" path="description" class="form-control"/>
				</div>	
			</div>
			<div class="row">
					    <div class='col-lg-10'>
					      <div class="form-group">
					      <label class="col-lg-2 control-label">Fecha   :</label>
					        <div class='input-group date' id='datetimepicker1'> 
					          <form:input id="date" path="date" type='text' class="form-control" />
					          <span class="input-group-addon">
					            <span class="glyphicon glyphicon-calendar"></span>
					          </span>
					        </div>
					      </div>
					    </div>
					  </div>	
	    
	    </form:form>	    
	   </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	        <button id="okBtndate" type="button" class="btn btn-primary">Guardar</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id="myModalOnView3" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
			¿Desea eliminar Cita programada?
	   </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
	        <button id="okDelBtndate" type="button" class="btn btn-primary">Eliminar</button>
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
	var url = "citasMainteanace.txt"
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
					jsonData: eventsInline,
					  locales: {
						    locale: "es",
							monthNames: [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
								"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" ],
							dayNames: [ 'Domingo','Lunes','Martes','Miércoles',
								'Jueves','Viernes','Sabado' ],
							dayNamesShort: [ 'Dom','Lun','Mar','Mie', 'Jue','Vie','Sab' ],
							txt_noEvents: "No hay eventos para este periodo",
							txt_SpecificEvents_prev: "",
							txt_SpecificEvents_after: "eventos:",
							txt_next: "siguiente",
							txt_prev: "anterior",
							txt_NextEvents: "Próximos eventos:",
							txt_GoToEventUrl: "Ir al evento",
							moment: {
						        "months" : [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
						                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" ],
						        "monthsShort" : [ "Ene", "Feb", "Mar", "Abr", "May", "Jun",
						                "Julio", "Ago", "Sep", "Oct", "Nov", "Dic" ],
						        "weekdays" : [ "Domingo","Lunes","Martes","Miércoles",
						                "Jueves","Viernes","Sabado" ],
						        "weekdaysShort" : [ "Dom","Lun","Mar","Mie",
						                "Jue","Vie","Sab" ],
						        "weekdaysMin" : [ "Do","Lu","Ma","Mi","Ju","Vi","Sa" ],
						        "longDateFormat" : {
						            "LT" : "H:mm",
						            "LTS" : "LT:ss",
						            "L" : "DD/MM/YYYY",
						            "LL" : "D [de] MMMM [de] YYYY",
						            "LLL" : "D [de] MMMM [de] YYYY LT",
						            "LLLL" : "dddd, D [de] MMMM [de] YYYY LT"
						        },
						        "week" : {
						            "dow" : 1,
						            "doy" : 4
						        }
						    }
						  }
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
		  $('#datetimepicker1').datetimepicker({
			  format: 'DD/MM/YYYY HH:mm'
		  });
		  
		  $("body").on("click","#newDate", function(e){ 
			  $('#pacient').val(" ");
			  $('#descript').val(" ");
			  $('#date').val(" ");
				  $("#myModalOnView2").modal({
					backdrop: 'static'
					});
				$("#myModalOnView2").modal('show');
		        return false;
		 });
});	  
	
function validate(){
		
	var valid = true;
		
		if(!$("#pacient").val()){
		 	valid= false;
	 		}
		if(!$("#descript").val()){
		 	valid= false;
	 		}
		if(!$("#date").val()){
		 	valid= false;
	 		}
	    
	return valid;
}
</script>
</body>
</html>