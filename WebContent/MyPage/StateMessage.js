function initStateMessage()
{
    var inMessage = false; // 메시지 안에 있나?
    var inPencil = false; // 펜슬에 마우스가 드러가있나?
    
    $("#user_state_message").hover(
        function() // mouseenter
        {
            inMessage = true;
            
            $("#user_state_message").append("<div class='pencil'></div>");
            $(".pencil").css({
                position : "absolute",
                backgroundImage : "url('MyPage/pencil.png')",
                backgroundSize : "100%",
                width : "26px",
                height : "26px",
                right : "2px",
                bottom : "2px",
                opacity : "0",
            });
            
            $(".pencil").hover(
                function() // enter
                {
                    inPencil = true;
                    $(this).stop(true);
                    $(this).css({
                        opacity : "1"
                    });
                },
                function() // leave
                {
                    inPencil = false;
                    $(this).css({
                        opacity : "0.5"
                    });
                }
            );
            
            if( !inPencil )
            {
                $(".pencil").animate({
                    opacity : "0.5"
                },100);
            }
        },
        function() // mouseleave
        {
            inMessage = false;
            
            if( !inPencil ) // 마우스가 펜슬에 없을때만 삭제해라
            {
                $(".pencil").animate(
                    {
                        opacity : "0.0"
                    },
                    100, 
                    function()
                    {
                        $(this).remove();
                    }
                );
            }
        }
        
    );
}