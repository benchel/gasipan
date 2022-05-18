/*
* jquery.js가 먼저 실행되어야한다.
* 2022.02.08
*/
$(document).ready(function() {
   menuContrl();
});

const menuContrl = function() {
    $('.top-menu').mouseenter(function(e) {
		e.preventDefault();
        // slide down 
        $('.sub-menu li').slideDown();
    });
    $('.menu-area').mouseleave(function(e) {
		e.preventDefault();
		$('.sub-menu li').slideUp();
	});
};