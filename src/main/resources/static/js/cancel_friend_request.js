

function cancel_friend_request() {

	var paramUserId = $('#param_user_id').val();

	var $abc = {
			userIdTwo : paramUserId
		};


	if (paramUserId != null) {

		$.ajax({
			type : "POST",
			url : "/friend-request/remove",
			data : JSON.stringify($abc),
			contentType : 'application/json',

			success : function(result) {
				$('#cancel_button').css("background", "#600");
				$('#cancel_button').html("Cancel Friend Request");
				document.getElementById("cancel_button").disabled = true;
				
				
				alert("Friend Request is Cancel");
				
			},
			error : function() {

				alert("Something Error. Post not saved.");
			}

		});

	}
}