//팝업 열기
function openMd(modal_name) {
	$('body').addClass('opened-modal');
	$('.modal-wrap').hide();
	$('.' + modal_name).show();
	$('.modal-input input').val('');
}
//팝업 닫기
$(document).on('click','.modal-close-btn',function() {
     $('body').removeClass('opened-modal');
	$('.modal-wrap').hide();
});
