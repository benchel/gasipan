$(document).ready(function() {
   progrEvent();
});

const progrEvent = function() {
	$('.attached-file').click(function() {
		downLoad(this); 
	});
};


const downLoad = function(terget) {
	let file = terget;
	let fileName = $(file).children('span').text();
	let fileKey = $(file).children('input').val();
	
	console.log(fileKey);
	console.log(fileName);
	
	let formData = document.downform;
	formData.fileName.value = fileName;
	formData.fileKey.value = fileKey;
	formData.boardType.value = boardType;
	formData.boardTypeStr.value = boardTypeStr;
	formData.submit();
};

