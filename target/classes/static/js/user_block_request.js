function block_request() {

	var paramUserId = $('#param_user_id').val();

	var $abc = {
			userIdTwo : paramUserId
		};


	if (paramUserId != null) {

		$.ajax({
			type : "POST",
			url : "/friend-request/block",
			data : JSON.stringify($abc),
			contentType : 'application/json',
			

			
			error : function() {

			
			}

		});

	}
}