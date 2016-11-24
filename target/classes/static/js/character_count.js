$('#statusbox1').keyup(function() {
	var count =300;
	
	for(var i = count; i>0; i--){
		count--;
		$('.character_counter').html(count);
	}
	
});