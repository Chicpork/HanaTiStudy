<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Jiwon Blog - About...</title>

    <!-- Favicon -->
    <link rel="icon" href="/img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="/style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="/css/responsive/responsive.css" rel="stylesheet">

    <script src="/js/mycommon.js"></script>
</head>

<body>
    <!-- Preloader Start -->
    <div id="preloader">
        <div class="yummy-load"></div>
    </div>

    <!-- ****** Top Header Area Start ****** -->
    <%-- <jsp:include page="/include/top_header_area.jsp" /> --%>
    <%@ include file="/include/top_header_area.jsp"%>
    <!-- ****** Top Header Area End ****** -->

    <!-- ****** Header Area Start ****** -->
    <%-- <jsp:include page="/include/header_area.jsp" /> --%>
    <%@ include file="/include/header_area.jsp"%>
    <!-- ****** Header Area End ****** -->


    <%-- ****** 메인 바디 작성 시작 ****** --%>
    <div class="about-blog" style="text-align: center;">
    	<img alt="" src="https://cdn02.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_download_software_1/H2x1_NSwitchDS_SlayTheSpire_image1600w.jpg">
        <p>안녕하세요.</p>
        <p>게임 관련 리뷰를 하는 블로그 입니다.</p>
        <p>자유롭게 둘러 보세요!</p>
        <p>위의 게임은 제가 요즘 가장 많이 하는 게임이예요!</p>
    </div>


    <%-- ****** 메인 바디 작성 끝 ****** --%>


    <!-- ****** Footer Menu Area Start ****** -->
    <jsp:include page="/include/footer_area.jsp" />
    <!-- ****** Footer Menu Area End ****** -->

    <!-- Jquery-2.2.4 js -->
    <script src="/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="/js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap-4 js -->
    <script src="/js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins JS -->
    <script src="/js/others/plugins.js"></script>
    <!-- Active JS -->
    <script src="/js/active.js"></script>
</body>