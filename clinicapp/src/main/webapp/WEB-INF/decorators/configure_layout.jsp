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
             
             <li>
                 <a href="#"><i class="fa fa-users"></i> Usuarios</a>
             </li>
         </ul>
     </div>
     <!-- /.sidebar-collapse -->
    
    <div id="page-wrapper">
    	<div id="content">
	    	<div class="row">
	    		<div class="col-lg-12">
	    			<h1 class="page-header"><c:out value="${title}"></c:out></h1>
	    		</div>
	    	</div>
	    	<div class="row">
	    		<div class="col-lg-12">
	    			<sitemesh:write property='body'/>
	    		</div>
	    	</div>
    	</div>
    </div>
</body>
</html>