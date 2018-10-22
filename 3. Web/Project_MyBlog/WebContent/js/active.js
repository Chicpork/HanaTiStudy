(function ($) {
    'use strict';

    // :: 4.0 ScrollUp Active JS
    if ($.fn.scrollUp) {
        $.scrollUp({
            scrollSpeed: 500,
            scrollText: '<i class="fa fa-arrow-up" aria-hidden="true"></i>'
        });
    }

    // :: 6.0 PreventDefault a Click
    $("a[href='#']").on('click', function ($) {
        $.preventDefault();
    });

    // :: 7.0 Search Form Active Code
    $(".searchBtn").on('click', function () {
        $(".search-hidden-form").toggleClass("search-form-open");
    });

    // :: 9.0 wow Active Code
    if ($.fn.init) {
        new WOW().init();
    }

    var $window = $(window);

    // :: 11.0 Preloader active code
    $window.on('load', function () {
        $('#preloader').fadeOut('slow', function () {
            $(this).remove();
        });
    });

    // my code
    $(".login").on('click', function () {
        $(".login-form").toggleClass("login-form-open");
        // const urls = window.location.href.split('/');
        // if (urls[urls.length - 1].split('?')[0] !== 'loginfail.jsp') {
        //     $("html").toggleClass("background-gray");
        // }
        document.getElementById('userId').focus();
    });

})(jQuery);