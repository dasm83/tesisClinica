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

	<div class="container-fluid">

		<form:form id="detailFormCat" method="POST" commandName="CatalogoExamDetail">
		<div class="row">
			<div class="col-lg-10">
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
			</div>
			</div>
			<div class="row">
				<br>
				<br>
				<div class="col-lg-6">
				<label>Determinacion</label>
				</div>
				<div class="col-lg-3">
				<label>Unidades</label>
				</div>
				<div class="col-lg-3">
				<label></label>
				</div>
				<br>
				<br>
				<div id="contenedor">
								<c:forEach items="${CatalogoExamDetail.items}" var="item" varStatus="loop">
								<div class="row">
								<div class="col-lg-6"><input name="items[${loop.index}].nombre" value="${item.nombre}" class="form-control"/>
									<input type="hidden" name="items[${loop.index}].oldId" value="${item.nombre}" />
									<input type="hidden" name="items[${loop.index}].id" value="${item.id}" />
								</div>
								<div class="col-lg-3">
								<select name="items[${loop.index}].unidad" class="form-control">
								<option selected>${item.unidad}</option>
								<c:forEach items="${unidadSelec}" var="type" varStatus="loop">
								<option>${type.name}</option>
								</c:forEach>
								</select>
								</div>
								<a id="delete" href="#" class="fa fa-trash fa-2x" aria-hidden="true"></a>	
								</div>	
								<br>
								</c:forEach>
								
    				
				</div>
			</div>
			<br>
			<a id="agregarCampo" class="fa fa-flask fa-2x" href="#"></a>
			<br>
			<form:input path="examCatId" cssStyle="display:none"/>
			<div class="row">
			<div class="col-lg-6">
			<button id="cancelBtn" type="button" class="btn btn-primary pull-left" style="margin-top:40px; margin-right:15px">Cancelar</button>                                         
			<button id="saveBtnCat" type="button" class="btn btn-primary pull-left" style="margin-top:40px; margin-right:15px">Guardar</button>
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

	 var MaxInputs       = 40; //Número Maximo de Campos
	    var contenedor       = $("#contenedor"); //ID del contenedor
	    var AddButton       = $("#agregarCampo"); //ID del Botón Agregar

	    //var x = número de campos existentes en el contenedor
	    var x = $("#contenedor div").length;
	  //  document.write(x);
	  if(x==0){
	    var contador=x;
	    var FieldCount = x; //para el seguimiento de los campos
	  }else if(x==1){
		  var contador=1; 
		  var FieldCount=1;
	  }else{
		  var contador=(x/3);
	  }
	    $(AddButton).click(function (e) {
	        if(x <= MaxInputs) //max input box allowed
	        {
	            FieldCount++;
	            //agregar campo
	       //     $(contenedor).append('<div class="row"><div class="col-lg-3"><input type="text" path="items" name="items['+contador+'].nombre" class="form-control" id="campo_'+ FieldCount +'" placeholder="Texto '+ contador +'"/></div><div class="col-lg-3"><input type="hidden" name="items['+contador+'].oldId" value="vacio" /><a href="#" class="eliminar">Eliminar</a></div></div>');
$(contenedor).append('<div class="row"><div class="col-lg-6"><input type="text" path="items" name="items['+contador+'].nombre" class="form-control" id="campo_'+ FieldCount +'" placeholder="Texto '+ contador +'"/><input path="items" type="hidden" name="items['+contador+'].oldId" value="vacio"/></div><div class="col-lg-3"><select path="items" name="items['+contador+'].unidad" class="form-control" size=1><option>${unidadSelec[0].name}</option><option>${unidadSelec[1].name}</option><option>${unidadSelec[2].name}</option><option>${unidadSelec[3].name}</option><option>${unidadSelec[4].name}</option><option>${unidadSelec[5].name}</option><option>${unidadSelec[6].name}</option><option>${unidadSelec[7].name}</option><option>${unidadSelec[8].name}</option><option>${unidadSelec[9].name}</option><option>${unidadSelec[10].name}</option><option>${unidadSelec[11].name}</option><option>${unidadSelec[1].name}</option></select></div><a id="delete" href="#" class="fa fa-trash fa-2x" aria-hidden="true"></a></div>');	          
	            x++; //text box increment
	            contador++;
	        }
	        return false;
	    });

	    $("body").on("click","#delete", function(e){ //click en eliminar campo
	        if( x > 1 ) {
	            $(this).parent('div').hide(); //eliminar el campo
	            $(this).prevAll().contents("input[name$='oldId']").val("delete");
	            x--;
	        }
	        return false;
	 });
});    
</script>
</body>
</html>