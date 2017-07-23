<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>ClinicApp | <sitemesh:write property='title'/></title>
	<sitemesh:write property='head'/>
</head>
<body>
     <div id="sidebar" class="sidebar-nav navbar-collapse">
         <ul class="nav" id="side-menu">
             <li>
                 <a href="<c:url value="/index.htm" />"><i class="fa fa-home"></i> Inicio</a>
             </li>
             
             <sec:authorize access="hasAnyAuthority('ADM','TEC')">
	             <li>
	                 <a href="#"><i class="fa fa-file-o"></i> Ex&#225;menes<span class="fa arrow"></span></a>
	           		<ul class="nav nav-second-level">
	           		  	<li>
	                         <a href="<c:url value="/maintenance/examenes.htm"/>">Archivo</a>
	                     </li>
	           		  	<li>
	                         <a href="<c:url value="/maintenance/catalogoExamenes.htm"/>">Cat&#225;logos</a>
	                     </li>
	                     <li>
	                         <a href="<c:url value="/maintenance/clasificacion.htm"/>">Clasificaciones</a>
	                     </li>
	                 </ul>
	             </li>
             </sec:authorize>
           	
           	<sec:authorize access="hasAnyAuthority('ADM','OPE')">
	             <li>
	                 <a href="<c:url value="/maintenance/pacientes.htm" />"><i class="fa fa-users"></i> Pacientes</a>
	             </li>
	             <li>
	                 <a href="<c:url value="/maintenance/laboratorista.htm" />"><i class="fa fa-user-md"></i> Laboratoristas</a>
	             </li>
	             <li>
	                 <a href="<c:url value="/maintenance/citas.htm" />"><i class="fa fa-calendar-check-o"></i> Citas</a>
	             </li>
             </sec:authorize>
             <li>
                 <a href="#"><i class="fa fa-bar-chart"></i> Reportes <span class="fa arrow"></span></a>
           		<ul class="nav nav-second-level">
           		  	<li>
                         <a href="<c:url value="/maintenance/reportes.htm"/>">Pacientes</a>
                     </li>
           		  	<li>
                         <a href="<c:url value="/maintenance/Eventos.htm"/>">Eventos</a>
                     </li>
                 </ul>
             </li>
         </ul>
     </div>
     <!-- /.sidebar-collapse -->
    
    <div id="page-wrapper">
    	<div id="content">
	    	<div class="row">
	    		<div class="col-lg-12">
	    			<sitemesh:write property='body'/>
	    		</div>
	    	</div>
    	</div>
    </div>
</body>
</html>