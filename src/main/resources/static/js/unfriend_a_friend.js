

function unfriend_a_friend() {

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
				$('#friend_button').css("background", "#600");
				$('#friend_button').html("Cancel Friend Request");
				document.getElementById("friend_button").disabled = true;
				
				
				alert("Unfriend");
				
			},
			error : function() {

				alert("Something Error. Post not saved.");
			}

		});

	}
}