
window.onload = function()
{
    initProfileImage(); // 프로필사진 초기화
    initStateMessage(); // 상태메세지 초기화
    initFriendList();
    
	var id = $("#toolbar > input").attr("value");
	// toolbar íê·¸ ì¶ê°	
	$("#toolbar").append("<img src='mainPage/setting.png' class='menu'>");	
	$("#toolbar").append("<img src='mainPage/home.png' class='menu'>");
	$("#toolbar").append("<span id='userId'>" + id  + "</span>");
	$("#toolbar").append("<img src='mainPage/icon.jpg' id='userIcon'/>");
	$("#userIcon").click( function()
		{
			window.location.href = "MyPage.bo"
		}
	);
	$("#toolbar").append("<div class='searchBox'> </div>");
	$("#toolbar > .searchBox").append("<input type='text' class='searchTerm' placeholder='Search...'/>");
	$("#toolbar > .searchBox").append("<button type='submit' class='searchButton'> <img src='mainPage/search.png' id ='searchImg'> </button>");
	
	
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
    	$("#searchImg").attr('src','mainPage/close.png');
    	$("#searchImg").slideToggle(200);
    	
    	$("#searchImg").on("click", function(){
    		$(".searchTerm").val('');
    	})
    });
    //TODO 검색창 클릭 해제 시
    $(".searchTerm").focusout(function(){
    	$("#searchImg").slideToggle(200);
    	$("#searchImg").attr('src','mainPage/search.png');
    	$("#searchImg").slideToggle(200);
    });
}

// 부모 height, 자식 height로 자식 vertical margin 조정
function resizeShowImage( parent, child )
{
    if( parent != undefined && child != undefined ) // 사진 확대 중이면 
    {
        /** margin-top, bottom 브라우저에 맞게 resize **/
        var valueOfMargin = ((parent.clientHeight - child.clientHeight)/2); // 부모 height, 자기자신 height 계산하여 margin 구하기
        child.style.margin = ( ( valueOfMargin > 0 ) ? valueOfMargin : valueOfMargin * -1 ) + "px auto"; // -가 나오지 않도록
    }
}

//창크기 변화 감지 ( 창 사이즈가 변할때 마다 호출 )
$( window ).resize(function() {

	if( $(window).width() <= 600 )
	{	
		// 맵 사라지기
	  	$('#map').fadeOut();

	  	// 검색 입력창 사라지기
	  	$('.searchTerm').stop().animate({
	  		width: 0
	  	},500);
	  	
	  	$('.searchTerm').off('click');
	  	$('.searchTerm').off('focusout');
	  	
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

	  	$('.searchButton').off('click');
	  	$('.searchTerm').off('focusout');

	  	
	  //TODO 검색창 클릭 시
	    $(".searchTerm").click(function(){
	    	$("#searchImg").slideToggle(200);
	    	$("#searchImg").attr('src','http://localhost:8080/plit/mainPage/close.png');
	    	$("#searchImg").slideToggle(200);
	    	
	    	$("#searchImg").on("click", function(){
	    		$(".searchTerm").val('');
	    	})
	    });
	  	//TODO 검색창 클릭 해제 시
    	$(".searchTerm").focusout(function(){
	    	$("#searchImg").slideToggle(200);
	    	$("#searchImg").attr('src','http://localhost:8080/plit/mainPage/search.png');
	    	$("#searchImg").slideToggle(200);
	    });
	}
});
