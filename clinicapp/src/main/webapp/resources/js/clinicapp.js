/// we'll be calling this every time we click on "okBtn" on "myModal"; 
/// it's supposed to be overriden on each maintenance jsp;
/// if we don't override it, this empty function is used instead
function validate(){} 

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
			var inputForm = "";
		});
		
		sendData();
	});
	
} )