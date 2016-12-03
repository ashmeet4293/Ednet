$('#modal_pic_discription').val('');

$("#modal_pic_discription").keyup(function() {

	var discription = $('#modal_pic_discription').val();
	var maximum = 300;
	var count = discription.length;

	$('.pic_discription_character_counter').text(300 - count);

});

