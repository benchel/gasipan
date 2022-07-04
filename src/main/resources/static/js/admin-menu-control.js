/*
* jquery.js가 먼저 실행되어야한다.
* 2022.02.08
*/
$(document).ready(function() {
   adminMenuContrl();
});

var toggle = false;

const adminMenuContrl = function() {
	$('.menu-elemt').click(function() {
		if(toggle) {
			$(this).find('.sub-menu-lst').slideUp();
			toggle = false;
		} else {
			$(this).find('.sub-menu-lst').slideDown();
			toggle = true;
		}      
	});
};