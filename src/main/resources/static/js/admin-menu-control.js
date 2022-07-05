/*
* jquery.js가 먼저 실행되어야한다.
* 2022.02.08
*/
$(document).ready(function() {
   adminMenuContrl();
});


const adminMenuContrl = function() {
	let subMenu = '';
	
	$('.menu-elemt').click(function() {
		subMenu = $(this).find('.sub-menu-lst')[0];
		
		if(subMenu.slideYn == undefined) {
			$(subMenu).slideDown();
			subMenu.slideYn = true;			
		} else {
			$(subMenu).slideUp();
			subMenu.slideYn = undefined;			
		}
	});
};