function unblock_user() {

	var paramUserId = $('#param_user_id').val();

	var $abc = {
			userIdTwo : paramUserId
		};


	if (paramUserId != null) {

		$.ajax({
			type : "POST",
			url : "/friend-request/unblock",
			data : JSON.stringify($abc),
			contentType : 'application/json',

			
			error : function() {

				
			}

		});

	}
}