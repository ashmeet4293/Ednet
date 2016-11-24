$('#statusbox1').val('');

$("#statusbox1").keyup(function() {

	var discription = $('#statusbox1').val();
	var maximum = 300;
	var count = discription.length;

	$('.character_counter').text(300 - count);

});

function abc() {

	var privacytype = $('#type').val();
	var discription = $('#statusbox1').val();

	var $abc = {
		userPostDiscription : discription,
		privacy : privacytype
	};

	var maxLength = 300;

	if (discription == '') {

	} else if (discription.length > maxLength) {

		alert("Post Character has more than 300");

	} else {
		$.ajax({
			type : "POST",
			url : "/users/posts/create",
			data : JSON.stringify($abc),
			contentType : 'application/json',

			success : function(result) {
				$('#statusbox1').val("");
				$('.character_counter').text(300);
				$('#new_post_status').prepend(result);
			},
			error : function() {

				alert("Something Error. Post not saved.");
			}

		});

	}
}
