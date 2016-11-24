
document.getElementById("search_result").style.display = "none";
function navbar_search_function() {
	
	var search_term = $('.search_bar').val();
	
	

	var $abc = search_term;

	if (search_term == "") {
		document.getElementById("search_result").style.display = "none";
	} else {
		document.getElementById("search_result").style.display="inherit"; 
		$.ajax({
			type : "POST",
			url : "/search",
			data : JSON.stringify($abc),
			contentType : 'application/json',

			success : function(result) {
				$('.search_result').html(result);
				
				
			},
			error : function() {

				alert("Something Error. Post not saved.");
			}

		});

	}

}