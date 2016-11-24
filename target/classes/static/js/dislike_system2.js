


$(function() {

	$.fn.toggleClick = function() {
		
		var functions = arguments
		return this.click(function() {
			
			var iteration = $(this).data('iteration') || 0
			//	console.log(iteration)
			functions[iteration].apply(this, arguments)
			iteration = (iteration + 1) % functions.length
			$(this).data('iteration', iteration)
		})
	}
	$(function() {
		$('.dislike_button').toggleClick(function() {
			var status_id = $(this).attr('id');
			
			var $abc = {
					userPostId : status_id
				};
			
			$(this).html('Like');
			
		
			
			//alert(status_id);
			$.ajax({
				type : "POST",
				url : "../../like/remove",
				data : JSON.stringify($abc),
				contentType : 'application/json',
				success : function(data) {
					
					$('#live_count'+status_id).html(data);

				}
			});

		}, function() {

			var status_id = $(this).attr('id');
			//alert(status_id);
			
			var $abc = {
					userPostId : status_id
				};
			
		
			$(this).html('Unlike');
			
			$.ajax({
				type : "POST",
				url : "../../like/add",
				data : JSON.stringify($abc),
				contentType : 'application/json',
				success : function(data) {
					
					  $('#live_count'+status_id).html(data);

				}
			});
		})
	})

});

//$(function() {
//	$('.dislike_button').toggle(function() {
//		$(this).html('Like');
//	}, function() {
//		$(this).html('dislike');
//	});
//});