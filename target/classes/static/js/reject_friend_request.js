function reject_friend_request() {

	var paramUserId = $('#param_user_id').val();

	var $abc = {
			userIdTwo : paramUserId
		};

	if (paramUserId != null) {

		$.ajax({
			type : "POST",
			url : "/friend-request/reject",
			data : JSON.stringify($abc),
			contentType : 'application/json',
			

			success : function(result) {
				$('#reject_button').css("background", "#600");
				$('#reject_button').html("Declined Request");
				document.getElementById("reject_button").disabled = true;
				
				
				alert("You decline the Request");
				
			},
			error : function() {

				alert("Something Error. Post not saved.");
			}

		});

	}
}