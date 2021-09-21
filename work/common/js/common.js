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


//팝업 +,-버튼
$(document).on('click','.calcul-btn > button',function() {
    $('.calcul-btn').removeClass('on');
    $(this).parent('.calcul-btn').addClass('on');
});

//사이드 메뉴 열기
$('.open-depth-btn').click(function() {
    $('.depth-con').addClass('opened-depth');
});
//사이드 메뉴 닫기
$('.close-depth-btn').click(function() {
    $('.depth-con').removeClass('opened-depth');
});
//드롭다운 메뉴
$('.depth-item a').click(function() {
    $(this).parent('.depth-item').children('.sub-depth').toggle();
});