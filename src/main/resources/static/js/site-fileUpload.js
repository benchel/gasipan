$(document).ready(function() {
   progressEvent();
});

const progressEvent = function() {
	$('.btn-upload').click(function() {
		fileUpload();
	});
	
	$('.btn-close').click(function() {
		modalClose();
	});
	
	$('.btn-add-file').click(function() {
		modalOpen();
	});
};

const iniModal = function() {
	document.getElementById('attachedFileForm').reset();
};

const modalOpen = function() {
	iniModal();
	$('.file-upload-modal').css('display', 'inline-block');	
};

const modalClose = function() {
	$('.file-upload-modal').css('display', 'none');	
};

const setFileHTML = function(fileKey, fileName) {
	let fileGroup = $('.file-group');
	let fileHTML = '';
	
	fileHTML += '<span class="attached-file">';
	fileHTML += '	<img class="file-icon"  src="/img/file_icon.png">'
	fileHTML += '	<input type="hidden" name="fileKey" value="'+ fileKey +'">';
	fileHTML += '	<span class="pointer" onclick="downLoad($(this).parent());">'+ fileName +'</span>';
	fileHTML += '	<button class="btn-rm" type="button" onclick="remove($(this).parent());">삭제</button>';
	fileHTML += '</span>';
	
	fileGroup.append(fileHTML);
};

const fileUpload = function() {
	let fileName = $('.uploadFile').val();
	
	if(fileName == '') {
		alert('파일을 선택하여 주십시오.');
		return;
	}
	
	let data = new FormData($('#attachedFileForm')[0]);
	
	$.ajax({
		type:"POST",
		enctype: 'multipart/form-data',
		url: "/attached/file/upload",
		data: data,
		processData: false,
		contentType: false,
		cache: false,        
		timeout: 600000,
		success: function(res){
			$('.btn-close').trigger('click');
			console.log("res: ", res);
			
			if(!res.resultCode) {
				alert(res.message);
				return;
			}
						
			if(res.attachedFile != '') {
				var fileName = res.attachedFile.fileName;
				var fileKey = res.attachedFile.fileKey;
				
				setFileHTML(fileKey, fileName);
			}
		},
		error: function(xhr){
			alert('파일 업로드 오류: ' + xhr.responseJSON.message);
			_$('.btn-modal-close').trigger('click');
			console.log(xhr);
		}
	});
	
};