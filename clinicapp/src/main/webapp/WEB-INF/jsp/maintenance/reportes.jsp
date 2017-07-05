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
 
    <form action="<c:url value='/maintenance/rportes.htm'/>" method="GET" id="ReportForm" commandName="reportForm">
 
        <table id="reptbl" width="350px" border="1">
        <tr>
        <td colspan="2"><form:errors path="noofYears" cssClass="error"/></td> </tr>
        <tr>
            <td>
                Enter Number of Years <input path="noofYears" id="noofYears"/>
                <input id="crearReporte3" type="submit"  value="Generate Employee List"/>
            </td>
 
        </tr>
 
         </table>  
 
    <form>
    
    <div id="myModalOnViewDet" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	      	
	      </div>
	      <div class="modal-footer">
	        <button id="okBtn" type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    
<script type="text/javascript">
var url = "rep";
</script>
</body>
</html>