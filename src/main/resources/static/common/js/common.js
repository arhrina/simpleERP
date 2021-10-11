


//팝업 열기
function openMd(modal_name, e) {
	preMd(this, e);//account
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

//account
$("#createCodePlus").click(function() {
	$("#createCode").val("PLUS");
})
$("#createCodeMinus").click(function() {
	$("#createCode").val("MINUS");
})
$("#updateCodePlus").click(function() {
	$("#updateCode").val("PLUS");
})
$("#updateCodeMinus").click(function() {
	$("#updateCode").val("MINUS");
})
preMd = function (t, e) {
	console.log("t e", t, e);

	if (!e) return;

	$('.account-modify').find('.calcul-btn-wrap').children().removeClass('on');
	$('.account-modify').find('.calcul-btn.' + $(e).children()[3].classList[1]).addClass('on');

	$("#updateId").val($(e).children()[0].textContent);
	$("#updateContent").val($(e).children()[2].textContent);
	$("#updateCode").val($(e).children()[3].classList[1].toUpperCase());
	$("#updateAmount").val($(e).children()[3].textContent);
	$("#updateRemarks").val($(e).children()[4].textContent);
};