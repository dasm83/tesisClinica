<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/metisMenu.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/sb-admin-2.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/clinicapp.css' />" rel="stylesheet">

<!-- JQUERY HAS TO BE PRESENT BEFORE ANY OTHER JS FILE -->
<script src="<c:url value="/resources/js/jquery-2.2.3.min.js" />"></script>
<!-- CALLING THIS BEFORE VIEW DRAWING, SO WE CAN OVERRIDE FUNCTIONS -->
<script src="<c:url value="/resources/js/clinicapp.js" />"></script>

<sitemesh:write property='head'/>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom:0">
	      <div class="container-fluid">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="#">Laboratorio Clinico LCB <i class="fa fa-flask fa-lg" aria-hidden="true"></i> </a>
	          
	        </div>
	        <div id="navbar" class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	            <li><a class="fa fa-user fa-lg" aria-hidden="true" href="#">  ${pageContext.request.userPrincipal.name}</a></li>
	            
	            <sec:authorize access="hasAuthority('ADM')">
	            	<li><a class="fa fa-cog fa-lg" aria-hidden="true" href="<c:url value="/admin/usuario.htm" />"></a></li>
	            </sec:authorize>
	            
	            <li><a class="fa fa-sign-out fa-lg" aria-hidden="true" href="<c:url value="/logout" />"></a></li>
	          </ul>
	        </div>
	      </div>
	      <div class="navbar-default sidebar" role="navigation">
	      	<div class="sidebar-nav navbar-collapse">
		    	<sitemesh:write property='div.sidebar'/>
		    </div>
		    <!-- /.sidebar-collapse -->
		  </div>
	    </nav>
    
	    <div id="page-wrapper">
	    	<sitemesh:write property='div.content'/>
	    </div>
	</div>


	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/metisMenu.min.js" />"></script>
	<script src="<c:url value="/resources/js/sb-admin-2.js" />"></script>
</body>
</html>