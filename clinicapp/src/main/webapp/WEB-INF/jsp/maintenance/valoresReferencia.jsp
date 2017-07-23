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
		<div class="row">
		<form:form id="detailFormCatVr" method="POST" commandName="CatalogoExamVrMainForm">
		<form:input path="itemId" cssStyle="display:none"/>
		<form:input path="nombre" cssStyle="display:none"/>
		<form:input path="unidad" cssStyle="display:none"/>
			 
			 <div id="contenedor">
			 <c:forEach items="${CatalogoExamVrMainForm.items}" var="item" varStatus="loop">		
			 <div class="row">		
			 <div class="panel panel-default">
			  <div class="panel-heading">Valores de Referencia
			  <div class="panel-title pull-right"><a id="delete" href="#" class="fa fa-trash fa-1x" aria-hidden="true"></a>
			  <input type="hidden" name="items[${loop.index}].estado" value="${item.estado}" />
			  </div>
			  </div>
			  <div class="panel-body">
			 	 <div class="form-group">
    				<label for="ejemplo_email_3" class="col-lg-1 control-label">Sexo :</label>
   						 <div class="col-lg-1">
   						   <select class="form-control input-sm" name="items[${loop.index}].sex"><option selected>${item.sex}</option><option>M</option><option>H</option><option></option></select>
   						   <input  type="hidden" class="form-control input-sm" id="idItem" name="items[${loop.index}].id" value="${item.id}"/>						
    					</div>
  				</div>
  				<br>
  				
 			 	<div class="form-group">
    				<label for="ejemplo_password_3" class="col-lg-2 control-label">Edad Minima :</label>
    					<div class="col-lg-2">
      						<input type="text" class="form-control input-sm" id="ageMin" name="items[${loop.index}].minAge" value="${item.minAge}" placeholder="Edad Minima"/>
    					</div>
    				<label for="ejemplo_password_3" class="col-lg-2 control-label">Edad Maxima :</label>
    				<div class="col-lg-2">
      						<input type="text" class="form-control input-sm" id="ageMax" name="items[${loop.index}].maxAge" value="${item.maxAge}" placeholder="Edad Maxima">
    					</div>
							    					 							
  				</div>
  				<br>
  				<div class="form-group">
  					<label for="ejemplo_password_3" class="col-lg-2 control-label">Valor de Referencia:</label>
    					<div class="col-lg-2">
      						<input type="text" class="form-control input-sm" id="ejemplo_password_3" name="items[${loop.index}].vRMin" value="${item.vRMin}" placeholder="V.R Minimo">
    					</div>
   						 <div class="col-lg-2">
   						   <select id="sel" class="form-control input-sm" name="items[${loop.index}].typeRango"><option selected>${item.typeRango}</option><option>-</option><option>></option><option><</option><option><=</option></select>							
    					</div>
       					<div class="col-lg-2">
      						<input type="text" class="form-control input-sm" id="ejemplo_password_3" name="items[${loop.index}].vRMax" value="${item.vRMax}" placeholder="V.R Maximo">
    					</div>
  				</div>
			  </div>
			</div>
		</div>
		</c:forEach>
		</div>	 		 
		<br>
		<a id="agregarCampo" class="fa fa-plus-square-o fa-1" href="#" title='Agregar valor de Referencia'></a>
		<br>
		<br>
			<div class="row">
			<div class="col-lg-6">
			<button id="cancelBtn" type="button" class="btn btn-primary pull-right" style="margin-top:40px; margin-right:15px">Cancelar</button>                                         
			<button id="saveBtnCatVr" type="button" class="btn btn-primary pull-right" style="margin-top:40px; margin-right:15px">Guardar</button>
			</div>
			</div>
		</form:form>
	  </div>
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
var url = "examCatVr.txt";
var mainURL = "catalogoExamenes.htm"
$(document).ready(function() {
	
	    var MaxInputs       = 120; //Número Maximo de Campos
	    var contenedor       = $("#contenedor"); //ID del contenedor
	    var AddButton       = $("#agregarCampo"); //ID del Botón Agregar
	   // var AnioMes			= $("#mesAnio");
	  //var x = número de campos existentes en el contenedor
	    var x = $("#contenedor div").length;
	  alert(x);
	    if(x==0){
		    var contador=x;
		    var FieldCount = x; //para el seguimiento de los campos
		  }else{
			  var contador=(x/14);
		  }
	    
	  $(AddButton).click(function (e) {
	        if(x <= MaxInputs) //max input box allowed
	        {
	            FieldCount++;
	            //agregar campo
$(contenedor).append('<div class="row"><div class="panel panel-default"><div class="panel-heading">Valores de Referencia<div class="panel-title pull-right"><a id="delete" href="#" class="fa fa-trash fa-1x" aria-hidden="true"></a><input type="hidden" name="items['+contador+'].estado"/></div></div><div class="panel-body"><div class="form-group"><label for="ejemplo_email_3" class="col-lg-1 control-label">Sexo :</label><div class="col-lg-1"><select path="vr" name="items['+contador+'].sex" class="form-control input-sm"><option selected>M</option><option>F</option><option></option></select><input type="hidden" id="idItem" path="items" name="items['+contador+'].id"/></div></div><br><div class="form-group"><label for="ejemplo_password_3" class="col-lg-2 control-label">Edad Minima :</label><div class="col-lg-2"><input type="text" class="form-control input-sm" id="edadMin" path="items" name="items['+contador+'].minAge" placeholder="Texto '+ contador +'"></div><label for="ejemplo_password_3" class="col-lg-2 control-label">Edad Maxima :</label><div class="col-lg-2"><input type="text" class="form-control input-sm" id="edadMax" path="items" name="items['+contador+'].maxAge" placeholder="Texto '+ contador +'"></div></div><br><div class="form-group"><label for="ejemplo_password_3" class="col-lg-2 control-label">Valor de Referencia:</label><div class="col-lg-2"><input type="text" class="form-control input-sm" id="ejemplo_password_2" path="items" name="items['+contador+'].vRMin" placeholder="Texto '+contador +'"></div><div class="col-lg-2"><select id="sel" path="items" name="items['+contador+'].typeRango" class="form-control input-sm" ><option selected>-</option><option><</option><option>></option><option><=</option></select></div><div class="col-lg-2"><input path="items" name="items['+contador+'].vRMax" type="text" class="form-control input-sm" id="ejemplo_password_3" placeholder="Texto '+ contador +'"></div></div></div></div></div>');
				x++; //text box increment
	            contador++;
	        }
	        return false;	  
	});
	 
	  $("body").on("click","#delete", function(e){ //click en eliminar campo
	        //if( x > 1 ) {
	            $(this).next().val("delete");
	        	//$(this).parent('div').parent('div').parent('div').hide(); //eliminar el campo
	        	$(this).parent('div').parent('div').parent('div').hide();
	            x--;
	       // }
	        return false;
	 });
	  
    $("body").on("change","#sel", function(e){ 
    	var v = $(this).val();
    	if((v==">") || (v=="<") || (v=="<=")){
    	$(this).parent().prevAll().contents("input[name$='vRMin']").val("V.N.");
    	$(this).parent().prevAll().contents("input[name$='vRMin']").attr('readonly','readonly');
    //	$(this).parent().prevAll().contents("input[name$='vRMin']").text("V.N.");
    //	$(this).parent().prevAll().contents("input[name$='vRMin']").attr("disabled", "true");
    	}else if(v=="-"){
    		$(this).parent().prevAll().contents("input[name$='vRMin']").val(" ");
    		$(this).parent().prevAll().contents("input[name$='vRMin']").attr("value"," ");
    		$(this).parent().prevAll().contents("input[name$='vRMin']").attr('readonly',false);
    	//	$(this).parent().prevAll().contents("input[name$='vRMin']").removeAttr('disabled');
    	//	$(this).parent().prevAll().contents("input[name$='vRMin']").prop("disabled", false);
    	}
    	
    }); 
});	   


function validate(){
	var arrayInputs = $('input[class $= input-sm]');
	    for(i = 0; i < arrayInputs.length; i++){
			if(!arrayInputs[i].value){
            	alert("campo vacio");
				return false;
            	break;
        }
    }
	    
	return true;
}
</script>
</body>
</html>