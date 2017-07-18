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
<!-- datepicker -->
<script src="<c:url value="/resources/js/bootstrap-datepicker.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datetimepicker.js" />"></script>
<link href="<c:url value='/resources/css/bootstrap-datepicker3.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-datetimepicker-standalone.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-datetimepicker.css'/>" rel="stylesheet">

<!-- datepicker -->
<script src="<c:url value="/resources/js/bootstrap-datepicker.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap-datetimepicker.js" />"></script>
<link href="<c:url value='/resources/css/bootstrap-datepicker3.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-datetimepicker.min.css.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-datetimepicker-standalone.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap-datetimepicker.css'/>" rel="stylesheet">
</head>
<body>

	 <form:form id="citaReportForm" class="form-horizontal" role="form" method="GET" commandName="citasReportForm" action="citasReportes.htm" target="_blank">			
			<div class="row">
					    <div class='col-lg-10'>
					      <div class="form-group">
					      <label class="col-lg-2 control-label">Fecha inicio:</label>
					        <div class='input-group date' id='datetimepicker8'> 
					          <form:input id="date1" path="fechaInicio" type='text' class="form-control" />
					          <span class="input-group-addon">
					            <span class="glyphicon glyphicon-calendar"></span>
					          </span>
					        </div>
					      </div>
					    </div>
					  </div>
					  				
		   <div class="row">
					    <div class='col-lg-10'>
					      <div class="form-group">
					      <label class="col-lg-2 control-label">Fecha inicio:</label>
					        <div class='input-group date' id='datetimepicker9'> 
					           <form:input id="date2" path="fechaFin" type='text' class="form-control" />
					          <span class="input-group-addon">
					            <span class="glyphicon glyphicon-calendar"></span>
					          </span>
					        </div>
					      </div>
					    </div>
					  </div>	
					 <br>
					  
		<div class="row">
			<div class="col-sm-4"></div>
			  	<div class="col-sm-4">
			 		<button id="okBtn" type="submit" type="button" class="btn btn-primary">Ver Reporte</button>
			    </div>
		    <div class="col-sm-4"></div> 
		</div>
	    
	    </form:form>
	    <br>

<script type="text/javascript">
$('#datetimepicker9').datetimepicker({
	  format: 'DD/MM/YYYY HH:mm'
});

$('#datetimepicker8').datetimepicker({
	  format: 'DD/MM/YYYY HH:mm'
});

</script>
</body>
</html>