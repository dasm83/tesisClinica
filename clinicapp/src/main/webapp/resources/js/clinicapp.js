var id = 0;

//////////////// PER VIEW FUNCTIONS ////////////////
/// These functions are supposed to be overriden on each maintenance jsp;
/// if we don't override them, these are called instead

/// we'll be calling validation function every time we click on "okBtn" on "myModal"; 
function validate(){return true;}
/// this function takes a row data and copies it into dialog;
function loadData(dialog){}
/// It disables all fields inside the dialog
function disableAllFields(dialog){}
/// It disables id fields in dialog
function disableIdFields(dialog){}

//////////////// END OF PER VIEW FUNCTIONS ////////////////

function openDialog(title){
	var modal = $("#myModal");
	modal.find('.modal-title').text(title);
	modal.find('.modal-body').html($("#modalContent").html());
	enaTempFields(modal); /// updating temp fields id
	if($("#maintenanceForm #action").val() == 'D'){ /// if we are deleting
		disableAllFields(modal); /// user doesn't need to edit info
		loadData(modal);
	} else if($("#maintenanceForm #action").val() == 'U'){
		disableIdFields(modal); /// user is not allowed to change pks
		loadData(modal);
	}
	modal.modal({ /// configuring modal before launching
	  backdrop: 'static'
	});
	modal.modal('show'); /// opening dialog
}

function enaTempFields(dialog){
	dialog.find("input,select,textarea").each(function(){ /// getting all fields on modal dialog
		var id = $(this).attr("id");
		$(this).attr("id",id+"_temp");
	});
}

/// disables all dialog fields; useful for a delete operation when user only needs to see info
function disableAllFields(dialog){
	dialog.find("input,select,textarea").attr("disabled","true");
}

function sendData(){
	$.ajax({
		url: url,
		method: "POST",
		data: $("#maintenanceForm").serialize(),
		success: function(data){
			$("#myModal").find('.modal-body').html("<div style='text-align:center'>"+data+"</div>");
			setTimeout(function(){
				location.reload(true);
			}, 4000);
		},
		error: function(jqXHR, error, errorThrown){
			$("#myModal").find('.modal-body').html("<div style='text-align:center'>"+jqXHR.responseText+"</div>");
		}
	});
}

$( function() { /// jquery start point
	
	$("body").on("click","#newMaintenance",function(){
		$("#maintenanceForm #action").val("I"); /// flag action = I; meaning insert on database
		openDialog(title);
	});
	
	$("body").on("click","#delMaintenance",function(){
		$("#maintenanceForm #action").val("D"); /// flag action = D; meaning delete on database
		openDialog(title);
	});
	
	$("body").on("click","#updMaintenance",function(){
		$("#maintenanceForm #action").val("U"); /// flag action = U; meaning update on database
		openDialog(title);
	});
	
	$("body").on("click","#myModal #okBtn",function(){
		if(!validate()){ /// if modal content is not valid, we exit this function
			return null;
		}
		
		/// copying data from dialog to form
		$("#myModal").find("input,select,textarea").each(function(){
			var idInputForm = $(this).attr("id").replace("_temp","");
			$("#maintenanceForm #"+idInputForm).val($(this).val());
		});
		
		sendData();
		
		$("#myModal").find(".modal-body").html("<div style='text-align:center'><i class='fa fa-spinner fa-pulse fa-3x fa-fw'></i><span class='sr-only'>Loading...</span></div>");
		$("#myModal").find(".modal-header").hide();
		$("#myModal").find(".modal-footer").hide();
	});
	
	/// to select maintenances rows
	$('body').on( 'click', '#maintenanceTable tbody tr', function () {
        if ( $(this).hasClass('active') ) {
            $(this).removeClass('active');
        }
        else {
            $('#maintenanceTable tr.active').removeClass('active');
            $(this).addClass('active');
        }
    } );
	
	/// ----------------- these apply for big maintenances -----------------
	$("body").on('click','#delMainOnView',function(){
		var tr = $(this).closest('tr');
		id = tr.attr('id');
		$("#myModalOnView").modal({ /// configuring modal before launching
		  backdrop: 'static'
		});
		$("#myModalOnView").modal('show');
	});
		
	$("body").on("click","#updMainOnView",function(){
		var tr = $(this).closest('tr');
		id = tr.attr('id');
		window.location.href = urlDetail + "?id="+id;
	});
	
	$("body").on("click","#updMainOnViewCat",function(){
		var tr = $(this).closest('tr');
		id = tr.attr('id');
		window.location.href = urlDetail + "?id="+id;
	});
	
	$("body").on("click","#delDate", function(){//lanzando modal par las cias 
		  id = $(this).next().attr('id');
		   $("#myModalOnView3").modal({
			 backdrop: 'static'
			});
		$("#myModalOnView3").modal('show');
		 });
	
	
	/// delete operation
	$("#myModalOnView #okBtn").click(function(){
		$.ajax({
			url: url,
			method: "POST",
			data: 'op=del&id='+id,
			dataType: 'text',
			success: function(data){
				$("#myModalOnView").find('.modal-body').html("<div style='text-align:center'>"+data+"</div>");
				setTimeout(function(){
					location.reload(true);
				}, 4000);
			},
			error: function(jqXHR, error, errorThrown){
				$("#myModalOnView").find('.modal-body').html("<div style='text-align:center'>"+jqXHR.responseText+"</div>");
				
			}
		});
		$("#myModalOnView").find(".modal-body").html("<div style='text-align:center'><i class='fa fa-spinner fa-pulse fa-3x fa-fw'></i><span class='sr-only'>Loading...</span></div>");
		$("#myModalOnView").find(".modal-header").hide();
		$("#myModalOnView").find(".modal-footer").hide();
	});
		
	//delete operation for dates
	$("#myModalOnView3 #okDelBtndate").click(function(){
		$.ajax({
			url: url,
			method: "POST",
			data: 'op=del&id='+id,
			dataType: 'text',
			success: function(data){
				$("#myModalOnView3").find('.modal-body').html("<div style='text-align:center'>"+data+"</div>");
				setTimeout(function(){
					location.reload(true);
				}, 4000);
			},
			error: function(jqXHR, error, errorThrown){
				$("#myModalOnView3").find('.modal-body').html("<div style='text-align:center'>"+jqXHR.responseText+"</div>");
				
			}
		});
		$("#myModalOnView3").find(".modal-body").html("<div style='text-align:center'><i class='fa fa-spinner fa-pulse fa-3x fa-fw'></i><span class='sr-only'>Loading...</span></div>");
		$("#myModalOnView3").find(".modal-header").hide();
		$("#myModalOnView3").find(".modal-footer").hide();
	});
	
		//New date
	$("#myModalOnView2 #okBtndate").click(function(){
		
		var data = $('#citaForm').serialize();
		
		$.ajax({
			url: url,
			method: "POST",
			data: 'op=iou&'+data,
			dataType: 'text',
			success: function(data){
				$("#myModalOnView2").find('.modal-body').html("<div style='text-align:center'>"+data+"</div>");
				setTimeout(function(){
					location.reload(true);
				}, 4000);
			},
			error: function(jqXHR, error, errorThrown){
				$("#myModalOnView2").find('.modal-body').html("<div style='text-align:center'>"+jqXHR.responseText+"</div>");
				
			}
		});
		$("#myModalOnView2").find(".modal-body").html("<div style='text-align:center'><i class='fa fa-spinner fa-pulse fa-3x fa-fw'></i><span class='sr-only'>Loading...</span></div>");
		$("#myModalOnView2").find(".modal-header").hide();
		$("#myModalOnView2").find(".modal-footer").hide();
	});
	
	$("#cancelBtn").click(function(){
		window.location.replace(mainURL);
	});
	
	/// insert or update
	$("#saveBtn").click(function(){
		if(!validate()){
			return;
		}
		console.log($('#pacient').val());
		console.log($('#detailForm').serialize());
		
		var data = $('#detailForm').serialize();
		
		$("#myModalOnViewDet").modal({ /// configuring modal before launching
			  backdrop: 'static'
		});
		$("#myModalOnViewDet").find(".modal-body").html("<div style='text-align:center'><i class='fa fa-spinner fa-pulse fa-3x fa-fw'></i><span class='sr-only'>Loading...</span></div>");
		$("#myModalOnViewDet").find(".modal-header").hide();
		$("#myModalOnViewDet").find(".modal-footer").hide();
		$("#myModalOnViewDet").modal('show');
		
		$.ajax({
			url: url,
			method: "POST",
			data: 'op=iou&'+data,
			dataType: 'text',
			success: function(data){
				$("#myModalOnViewDet").find('.modal-body').html("<div style='text-align:center'>"+data+"</div>");
				setTimeout(function(){
					window.location.replace(mainURL);
				}, 4000);
			},
			error: function(jqXHR, error, errorThrown){
				$("#myModalOnViewDet").find('.modal-body').html("<div style='text-align:center'><h3>Error</h3>"+jqXHR.responseText+"</div>");
				setTimeout(function(){
					$("#myModalOnViewDet").modal('hide');
				}, 4000);
			}
		});
		
	});
	
	//insert nuevoCatalogoExamen
	
	$("#saveBtnCat").click(function(){
		
		var data = $('#detailFormCat').serialize();
		
		$("#myModalOnViewDet").modal({ /// configuring modal before launching
			  backdrop: 'static'
		});
		
		$.ajax({
			url: url,
			method: "POST",
			data: data,
			dataType: 'text',
			success: function(data){
				$("#myModalOnViewDet").find('.modal-body').html("<div style='text-align:center'>"+data+"</div>");
				setTimeout(function(){
					window.location.replace(mainURL);
				}, 4000);
			},
			error: function(jqXHR, error, errorThrown){
				$("#myModalOnViewDet").find('.modal-body').html("<div style='text-align:center'><h3>Error</h3>"+jqXHR.responseText+"</div>");
				setTimeout(function(){
					$("#myModalOnViewDet").modal('hide');
				}, 4000);
			}
		});
	});	
	
	//Insert para valores de referencia

$("#saveBtnCatVr").click(function(){
		
		var data = $('#detailFormCatVr').serialize();
		
		$("#myModalOnViewDet").modal({ /// configuring modal before launching
			  backdrop: 'static'
		});
		
		$.ajax({
			url: url,
			method: "POST",
			data: data,
			dataType: 'text',
			success: function(data){
				$("#myModalOnViewDet").find('.modal-body').html("<div style='text-align:center'>"+data+"</div>");
				setTimeout(function(){
					window.location.replace(mainURL);
				}, 4000);
			},
			error: function(jqXHR, error, errorThrown){
				$("#myModalOnViewDet").find('.modal-body').html("<div style='text-align:center'><h3>Error</h3>"+jqXHR.responseText+"</div>");
				setTimeout(function(){
					$("#myModalOnViewDet").modal('hide');
				}, 4000);
			}
		});
	});	
	
	
	//cargar el select: items de examenes
	
$("body").on("click","#itemsMainOnView",function(){
		var tr = $(this).closest('tr');
		id = tr.attr('id');

	$.ajax({
			url: urlj,
			method: "POST",
			data: 'op=itms&id='+id,
			dataType: 'json',
			success: function(data){
				 cargarSelect(data); 
			},
			 error : function(jqXHR, error, errorThrown) {
				 $("#myModalOnView2").find('.modal-body').html("<div style='text-align:center'><h3>Error</h3>"+jqXHR.responseText+"</div>");
					setTimeout(function(){
						$("#myModalOnView2").modal('hide');
					}, 4000);
				}
		});
	
	function cargarSelect(data) {
		$('#type').empty();
        for ( var i = 0, len = data.length; i < len; ++i) {
            var itms = data[i];
            $('#type').append("<option value=\"" +itms.id + "\">" + itms.nombre+ "</option>");
        }
    } 
	
	
});
	
	/// ----------------- style and format of form elements -----------------
	$('body').on( 'focusin', 'input,select', function () {
		if($(this).hasClass("error")){
			$(this).removeClass("error");
		}
	});
	
	$('body').on( 'keyup', 'input.text', function () {
		$(this).val($(this).val().replace(/[^a-zA-Z\s]/g,""));
	});
	
	$('body').on( 'keyup', 'input.number', function () {
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	});
	
} )