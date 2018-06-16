<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>writeBoard</title>
    <link rel="stylesheet" href="writeBoard/writeBoard.css">
</head>
<body>
    <form action="writeBoard.bo" method="post">
        <section>
                <div id="location_box">
                    <div id="location_text"></div>
                    <input id="location_button" type="button" value="위치받아오기">
                    <input id="lat" type="hidden">
                    <input id="lon" type="hidden">
                </div>
                <div id="text_box">
                    <textarea id="text_area"></textarea>
                </div>
                <div id="image_box">

                </div>
                <div id="ok_cancel_box">
                    <input id="upload" type="button" value="사진 불러오기">
                    <input id="submit" type="button" value="작성">
                    <input id="cancel" type="button" value="취소">
                </div>
        </section>
    </form>
</body>
</html>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="writeBoard/writeBoard.js"></script>