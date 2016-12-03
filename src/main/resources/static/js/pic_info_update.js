$('#modal_pic_discription').val('');

$("#modal_pic_discription").keyup(function() {

	var discription = $('#modal_pic_discription').val();
	var maximum = 300;
	var count = discription.length;

	$('.pic_discription_character_counter').text(300 - count);

});

function abc() {

	var privacytype = $('#privacy').val();
	var discription = $('#modal_pic_discription').val();

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
			url : "/users/pictures/update",
			data : JSON.stringify($abc),
			contentType : 'application/json',

			success : function(result) {
				$('#modal_pic_discription').val("");
				$('.pic_discription_character_counter').text(300);
				alert("Update Success");
			},
			error : function() {

				alert("Something Error. Picture not Update.");
			}

		});

	}
}
