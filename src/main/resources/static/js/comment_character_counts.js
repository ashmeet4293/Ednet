
$('#commentbox').val('');

$("#commentbox").keyup(function() {

	var discription = $('#commentbox').val();
	var maximum = 300;
	var count = discription.length;

	$('.character_counter2').text(300 - count);

});

