$(document).ready( function() {
	$('.menu').hover( function() {
		$(this).stop().animate({
			width: 28,
			height: 28,
			paddingTop: 5
		}, 500);
			
	}, function(){
		$(this).stop().animate({
			width: 24,
			height: 24,
			paddingTop: 8
		}, 500);
	});
});

// 창크기 변화 감지
$( window ).resize(function() {
	if( $(window).width() < 600 )
	{	
	  	$('#map').fadeOut();
	}
	else
	{
		$('#map').fadeIn();
	}
});