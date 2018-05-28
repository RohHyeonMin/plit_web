$(document).ready( function() {
	// 메뉴 아이콘 애니메이션
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

	// searchBox 애니메이션
	//TODO 검색창 클릭 시
    $(".searchTerm").click(function(){
    	$("#searchImg").slideToggle(200);
    	$("#searchImg").attr('src','close.png');
    	$("#searchImg").slideToggle(200);
    });
    //TODO 검색창 클릭 해제 시
    $(".searchTerm").focusout(function(){
    	$("#searchImg").slideToggle(200);
    	$("#searchImg").attr('src','search.png');
    	$("#searchImg").slideToggle(200);
    });
});

// 창크기 변화 감지 ( 창 사이즈가 변할때 마다 호출 )
$( window ).resize(function() {

	if( $(window).width() <= 600 )
	{	
		// 맵 사라지기
	  	$('#map').fadeOut();

	  	// 검색 입력창 사라지기
	  	$('.searchTerm').stop().animate({
	  		width: 0
	  	},500);

	  	// 검색 버튼 클릭시
	  	$('.searchButton').click(function(){
	  		// 작은 메뉴 아이콘, 사용자 정보 사라지기
	  		$('.menu').css('display','none');
	  		$('#userId').css('display','none');
	  		$('#userIcon').css('display','none');

	  		$('.searchBox').css('width', '98%');
	  		$('.searchBox').css('margin-left', '1%');
			$('.searchBox').css('margin-right', '1%');
	  		$('.searchTerm').stop().animate({
	  			width: "90%"
	  		},500);
	  	});

	  	$('.searchTerm').focusout(function(){
	  		$('.menu').css('display','block');
	  		$('#userId').css('display','block');
	  		$('#userIcon').css('display','block');

			$('.searchBox').css('width', '15%');
			$('.searchBox').css('margin-right', '40px');
	  		$('.searchTerm').stop().animate({
	  		width: 0
	  	},500);
	  	});
	}
	else
	{
		$('#map').fadeIn();
		
		// 검색 입력창 나타내기
		$('.menu').css('display','block');
	  	$('#userId').css('display','block');
	  	$('#userIcon').css('display','block');

		$('.searchBox').css('width', '15%');
		$('.searchBox').css('margin-right', '40px');
	  	$('.searchTerm').stop().animate({
	  		width: "90%"
	  	},500);

	  	$('.searchButton').off('mouseover');
	  	$('.searchTerm').off('focusout');

	  	//TODO 검색창 클릭 해제 시
    	$(".searchTerm").focusout(function(){
	    	$("#searchImg").slideToggle(200);
	    	$("#searchImg").attr('src','search.png');
	    	$("#searchImg").slideToggle(200);
	    });
	}
});