function initFriendList()
{
	var id = $("#toolbar > input").attr("value");
	
    $.ajax({
        type : "POST",
        url : ".data",
        data : { "type" : "friendList", "id" : id},
        dataType : "json",
        
        success : function( jsonArray )
        {
            for( key in jsonArray ) // foreach문 jsonArray 마지막 인덱스까지 key 값을 반환해준다.
            {
                var user_info = jsonArray[ key ].user_info;
                var friend_id = jsonArray[ key ].id;
                var message = jsonArray[ key ].message;
                
                $("#user_friend").append("<div class='friend_list'>" +
                                            "<div class='friend_left'>" +
                                                 "<div class='friend_profile_image'></div>" +
                                                 "<div class='friend_id'>" + friend_id + "</div>" +
                                             "</div>" +
                                             "<div class='friend_right'>" +
                                                 "<div rowspan='2' class='friend_message'>상태메세지</div>" +
                                             "</div>" + 
                                         "</div>");
            }
            
            $(".friend_list").css({
                width : "98%",
                height : "98px",
                backgroundColor : "red",
                margin : "1px 1%",
                textAlign : "center",

            });
            
            // 친구 목록 왼쪽 영역
            $(".friend_left").css({
                width : "40%",
                height : "98px",
                backgroundColor : "gray",
                float : "left",
            });
            // 친구 목록 오른쪽 영역
            $(".friend_right").css({
                width : "60%",
                height : "98px",
                backgroundColor : "white",
                float : "left",
            });
            
            $(".friend_profile_image").css({
                width : "50%",
                margin : "0 25%",
                height : "70px",
                backgroundColor : "green",
                lineHeight : "70px",
                float : "none",
                backgroundSize : "250%",
                backgroundPosition : "center center",
                backgroundRepeat : "no-repeat",
            });
            
            $(".friend_id").css({
                width : "100%%",
                height : "28px",
                backgroundColor : "blue",
                lineHeight : "28px",
                overflow : "hidden",
                textOverflow: "ellipsis",
                whiteSpace : "nowrap",
            });
            
            $(".friend_message").css({
                width : "100%%",
                height : "98px",
                backgroundColor : "yellow",
                lineHeight : "98px",
                overflow : "hidden",
                textOverflow: "ellipsis",
                whiteSpace : "nowrap",
            });
        },
        
        error : function()
        {
            alert(" 친구목록 생성 실패 ");
        }
        
    });
}