function accept_friend_request() {

	var paramUserId = $('#param_user_id').val();

	var $abc = {
			userIdTwo : paramUserId
		};


	if (paramUserId != null) {

		$.ajax({
			type : "POST",
			url : "/friend-request/accept",
			data : JSON.stringify($abc),
			contentType : 'application/json',

			success : function(result) {
				$('#accept_button').css("background", "#600");
				$('#accept_button').html("Friend");
				document.getElementById("accept_button").disabled = true;
				
				
				alert("He became your Friend");
				
			},
			error : function() {

				alert("Something Error. Post not saved.");
			}

		});

	}
}