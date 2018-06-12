	$(document).ready( function() {
		var id = $("#toolbar > input").attr("value");
		// toolbar íê·¸ ì¶ê°	
		$("#toolbar").append("<img src='http://localhost:8080/plit/mainPage/setting.png' class='menu'>");	
		$("#toolbar").append("<img src='http://localhost:8080/plit/mainPage/home.png' class='menu'>");
		$("#toolbar").append("<span id='userId'>" + id  + "</span>");
		$("#toolbar").append("<img src='http://localhost:8080/plit/mainPage/icon.jpg' id='userIcon'/>");
		$("#userIcon").click( function()
			{
				window.location.href = "myPage.bo"
			}
		);
		$("#toolbar").append("<div class='searchBox'> </div>");
		$("#toolbar > .searchBox").append("<input type='text' class='searchTerm' placeholder='Search...'/>");
		$("#toolbar > .searchBox").append("<button type='submit' class='searchButton'> <img src='mainPage/search.png' id ='searchImg'> </button>");
		
		
		// ë©ë´ ìì´ì½ ì ëë©ì´ì
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

		// searchBox ì ëë©ì´ì
		//TODO ê²ìì°½ í´ë¦­ ì
	    $(".searchTerm").click(function(){
	    	$("#searchImg").slideToggle(200);
	    	$("#searchImg").attr('src','http://localhost:8080/plit/mainPage/close.png');
	    	$("#searchImg").slideToggle(200);
	    	
	    	$("#searchImg").on("click", function(){
	    		$(".searchTerm").val('');
	    	})
	    });
	    //TODO ê²ìì°½ í´ë¦­ í´ì  ì
	    $(".searchTerm").focusout(function(){
	    	$("#searchImg").slideToggle(200);
	    	$("#searchImg").attr('src','http://localhost:8080/plit/mainPage/search.png');
	    	$("#searchImg").slideToggle(200);
	    });
	});

	// ì°½í¬ê¸° ë³í ê°ì§ ( ì°½ ì¬ì´ì¦ê° ë³í ë ë§ë¤ í¸ì¶ )
	$( window ).resize(function() {

		if( $(window).width() <= 600 )
		{	
			// ë§µ ì¬ë¼ì§ê¸°
		  	$('#map').fadeOut();

		  	// ê²ì ìë ¥ì°½ ì¬ë¼ì§ê¸°
		  	$('.searchTerm').stop().animate({
		  		width: 0
		  	},500);
		  	
		  	$('.searchTerm').off('click');
		  	$('.searchTerm').off('focusout');
		  	
		  	// ê²ì ë²í¼ í´ë¦­ì
		  	$('.searchButton').click(function(){
		  		// ìì ë©ë´ ìì´ì½, ì¬ì©ì ì ë³´ ì¬ë¼ì§ê¸°
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
			
			// ê²ì ìë ¥ì°½ ëíë´ê¸°
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

		  	
		  //TODO ê²ìì°½ í´ë¦­ ì
		    $(".searchTerm").click(function(){
		    	$("#searchImg").slideToggle(200);
		    	$("#searchImg").attr('src','http://localhost:8080/plit/mainPage/close.png');
		    	$("#searchImg").slideToggle(200);
		    	
		    	$("#searchImg").on("click", function(){
		    		$(".searchTerm").val('');
		    	})
		    });
		  	//TODO ê²ìì°½ í´ë¦­ í´ì  ì
	    	$(".searchTerm").focusout(function(){
		    	$("#searchImg").slideToggle(200);
		    	$("#searchImg").attr('src','http://localhost:8080/plit/mainPage/search.png');
		    	$("#searchImg").slideToggle(200);
		    });
		}
	});