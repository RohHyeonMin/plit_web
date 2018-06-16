$(document).ready( function() {

	var height = screen.height - 250;
	var width = window.innerWidth;
	$.menu();
	$.changeMenu(width);
	$("#wrapper").css('height', height );
	var wh = height - 50;
	$("#map").css('height', wh);
	$("#list").css('height', wh );

});

// 창크기 변화 감지 ( 창 사이즈가 변할때 마다 호출 )
$( window ).resize(function() {

	var width = window.innerWidth;
	$.changeMenu(width);
});

