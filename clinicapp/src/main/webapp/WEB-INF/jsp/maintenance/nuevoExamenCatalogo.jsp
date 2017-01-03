<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>
<body>

	<div class="row">

	<form:form id="detailFormCat" method="POST" commandName="CatalogoExamDetail">
		<div class="row">
			<div class="col-lg-6">
				<form:label for="name" path="nombre">Nombre de Examen:</form:label>
				<form:input id="name" path="nombre" cssClass="form-control"/>
				<br>
				<form:label for="description" path="descripcion">Descripcion:</form:label>
				<form:input id="description" path="descripcion" cssClass="form-control"/>
				<br>
				<label>Tipo de ex&#225;men:</label>
		      	<form:select id="type" name="type" path="clasificacion">
					<c:forEach items="${examClasificacion}" var="type" varStatus="loop">
						<form:option path="clasificacion" value="${type.id}">${type.name}</form:option>
					</c:forEach>
				</form:select>
				<br>
				<br>
				<a id="agregarCampo" class="btn btn-info" href="#">Agregar Campo</a>
				<div id="contenedor">
    				<div class="added">
    						<div class="col-lg-6">
								<c:forEach items="${CatalogoExamDetail.items}" var="item" varStatus="loop">
							<div><input name="items[${loop.index}].nombre" value="${item.nombre}" class="form-control"/><a href="#" class="eliminar">Eliminar</a></div>
								<br>
								</c:forEach>
    						</div>
				</div>
			</div>
			<form:input path="examCatId" cssStyle="display:none"/>
			<div class="row">
			<button id="cancelBtn" type="button" class="btn btn-primary pull-right" style="margin-top:40px; margin-right:15px">Cancelar</button>
			<button id="saveBtnCat" type="button" class="btn btn-primary pull-right" style="margin-top:40px; margin-right:15px">Guardar</button>
			</div>
		</div>
	</form:form>
</div>

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
var url = "examCat.txt";
var mainURL = "examenes.htm"
$(document).ready(function() {

	 var MaxInputs       = 20; //Número Maximo de Campos
	    var contenedor       = $("#contenedor"); //ID del contenedor
	    var AddButton       = $("#agregarCampo"); //ID del Botón Agregar

	    //var x = número de campos existentes en el contenedor
	    var x = $("#contenedor div").length + 1;
	    var contador=x-3;
	    var FieldCount = x-1; //para el seguimiento de los campos

	    $(AddButton).click(function (e) {
	        if(x <= MaxInputs) //max input box allowed
	        {
	            FieldCount++;
	            //agregar campo
	            $(contenedor).append('<div><input type="text" path="items" name="items['+contador+'].nombre" cssClass="form-control" id="campo_'+ FieldCount +'" placeholder="Texto '+ contador +'"/><a href="#" class="eliminar">Eliminar</a></div>');
	            x++; //text box increment
	            contador++;
	        }
	        return false;
	    });

	    $("body").on("click",".eliminar", function(e){ //click en eliminar campo
	        if( x > 1 ) {
	            $(this).parent('div').remove(); //eliminar el campo
	            x--;
	        }
	        return false;
	 });
});    
</script>
</body>
</html>