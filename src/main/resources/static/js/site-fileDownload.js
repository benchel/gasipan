$(document).ready(function() {
   progrEvent();
});

const progrEvent = function() {
	$('.fileName').click(function() {
		downLoad($(this).parent()); 
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
	formData.submit();
};

const remove = function(rmTarget) {
	let file = rmTarget;
	let fileName = $(file).children('span').text();
	let fileKey = $(file).children('input').val();
	let parentType = $('input[name="parentType"]').val();
	let formData = [];

	formData.push({ name : 'fileKey', value : fileKey });		
	formData.push({ name : 'fileName', value : fileName });
	formData.push({ name : 'parentType', value : parentType });

	let url = '/attached/file/delete';
	$.ajax({
		url : url,
		type : 'POST',
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded',
		data : formData
	}).done(function (result, status, xhr) {
		if(result.code) {
			$(file).remove();
		}
	});
	
};

const getFileKeyList = function() {
	let fileList = [];
	
	$('input[name="fileKey"]').each(function() {
		fileList.push($(this).val());
		console.log($(this).val());
	});
	
	return fileList;
};


