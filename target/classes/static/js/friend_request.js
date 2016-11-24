

function friend_request() {

	var paramUserId = $('#param_user_id').val();

	
	var $abc = {
			userIdTwo : paramUserId
		};


	if (paramUserId != null) {
		$.ajax({
			type : "POST",
			url : "/friend-request/add",
			data : JSON.stringify($abc),
			contentType : 'application/json',

			success : function(result) {
				$('#friend_button').css("background", "#600");
				$('#friend_button').html("Request Sent");
				document.getElementById("friend_button").disabled = true;
				
				
				alert("Request Sent");
				
			},
			error : function() {

				alert("Something Error. Post not saved.");
			}

		});

	}
}