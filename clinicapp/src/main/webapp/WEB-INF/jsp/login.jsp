<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ClinicApp | ${title}</title>

<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/sb-admin-2.css' />" rel="stylesheet">
</head>
<body>

	 <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Logueo</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="login.htm" method='POST'>
                            <fieldset>
                            	<c:if test="${param.error != null}">
					                <div class="alert alert-danger">
					                    Usuario o contraseña inválidos.
					                </div>
					            </c:if>
					            <c:if test="${param.logout != null}">
					                <div class="alert alert-success">
					                    Ha salido exitosamente.
					                </div>
					            </c:if>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Usuario" id="username" name="username" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Contraseña" id="password" name="password" type="password" value="">
                                </div>
                                <button type="submit" class="btn btn-default pull-right">Entrar</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>