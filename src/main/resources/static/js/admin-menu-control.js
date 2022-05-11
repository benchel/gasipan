/*
* jquery.js가 먼저 실행되어야한다.
* 2022.02.08
*/
$(document).ready(function() {
   adminMenuContrl();
});

const adminMenuContrl = function() {
    $('.menu-elemt').hover(function() {
        // slide down 
        $(this).find('.sub-menu-lst').slideDown();
    });
    $('.menu-group').mouseleave(function() {
		$('.menu-list .sub-menu-lst').slideUp();
	});
};