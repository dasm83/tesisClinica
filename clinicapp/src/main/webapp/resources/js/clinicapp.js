/// we'll be calling validation function every time we click on "okBtn" on "myModal"; 
/// it's supposed to be overriden on each maintenance jsp;
/// if we don't override it, this function is used instead
function validate(){return true;} 

function openDialog(title){
	var modal = $("#myModal");
	modal.find('.modal-title').text(title);
	modal.find('.modal-body').html($("#modalContent").html());
	enaTempFields(modal); /// updating temp fields id
	modal.modal({ /// configuring modal before launching
	  backdrop: 'static'
	});
	modal.modal('show'); /// opening dialog
}

function enaTempFields(dialog){
	dialog.find("input,select").each(function(){ /// getting all fields on modal dialog
		var id = $(this).attr("id");
		$(this).attr("id",id+"_temp");
	});
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
	
	$("#newMaintenance").click(function(){
		$("#maintenanceForm #action").val("I"); /// flag action = I; meaning insert on database
		openDialog(action, title);
	});
	
	$("#myModal #okBtn").click(function(){
		if(!validate()){ /// if modal content is not valid, we exit this function
			return null;
		}
		
		/// copying data from dialog to form
		$("#myModal").find("input,select").each(function(){
			var idInputForm = $(this).attr("id").replace("_temp","");
			$("#maintenanceForm #"+idInputForm).val($(this).val());
		});
		
		sendData();
		
		$("#myModal").find(".modal-body").html("<div style='text-align:center'><i class='fa fa-spinner fa-pulse fa-3x fa-fw'></i><span class='sr-only'>Loading...</span></div>");
		$("#myModal").find(".modal-header").hide();
		$("#myModal").find(".modal-footer").hide();
	});
	
} )