Account = {
    getAccounts : function(now) {
        $.ajax({
            url : 'http://localhost:8080/account?date=' + now,
            method : 'GET',
            contentType : 'application/json',
            success : function(data) {
                console.log(data);
                if (data.length > 0)
                    $("tbody").empty();
                data.forEach(function(e) {
                    console.log("e", e);
                    $("tbody").append(Account.getRowHtml(e.id, e.date, e.content, e.code, e.amount, e.remarks));
                });
            },
            error : function(jqXHR, textStatus, errorThrown) {
                alert("에러가 발생했습니다. 김민우씨에게 알려주세요.");
                console.log(jqXHR, textStatus, errorThrown);
            }
        })
    },
    createAccount : function(data) {
        $.ajax({
            url : 'http://localhost:8080/account',
            method : 'POST',
            contentType : 'application/json',
            data : data,
            success : function(data, status, xhr) {
                alert("추가되었습니다.");
                console.log(data);
                location.href = xhr.getResponseHeader('Location');
            },
            error : function(jqXHR, textStatus, errorThrown) {
                alert("에러가 발생했습니다. 김민우씨에게 알려주세요.");
                console.log(jqXHR, textStatus, errorThrown);
            }
        })
    },
    updateAccount : function(id, data) {
        $.ajax({
            url : 'http://localhost:8080/account/' + id,
            method : 'PATCH',
            contentType : 'application/json',
            data : data,
            success : function(data) {
                alert("수정되었습니다.");
                console.log(data);
                location.href = "/view/account";
            },
            error : function(jqXHR, textStatus, errorThrown) {
                alert("에러가 발생했습니다. 김민우씨에게 알려주세요.");
                console.log(jqXHR, textStatus, errorThrown);
            }
        })
    },
    deleteAccount : function(id) {
        $.ajax({
            url : 'http://localhost:8080/account/' + id,
            method : 'DELETE',
            contentType : 'application/json',
            success : function(data) {
                alert("삭제되었습니다.");
                console.log(data);
                location.href = "/view/account";
            },
            error : function(jqXHR, textStatus, errorThrown) {
                alert("에러가 발생했습니다. 김민우씨에게 알려주세요.");
                console.log(jqXHR, textStatus, errorThrown);
            }
        })
    },
    getTotalAmount : function(now) {
        $.ajax({
            url : 'http://localhost:8080/account/amount?date=' + now,
            method : 'GET',
            contentType : 'application/json',
            success : function(data) {
                console.log(data);
                $("#totalAmount").text(data.totalAmount + "원");
            },
            error : function(jqXHR, textStatus, errorThrown) {
                alert("에러가 발생했습니다. 김민우씨에게 알려주세요.");
                console.log(jqXHR, textStatus, errorThrown);
            }
        })
    },
    getRowHtml : function(id, date, content, code, amount, remarks) {
        return '<tr onClick="openMd(\'account-modify\', this);">' +
                    '<td>' + id + '</td>' +
                    '<td>' + date + '</td>' +
                    '<td>' + content + '</td>' +
                    '<td class="price ' + code.toLowerCase() + '"><span>' + amount + '</span></td>' +
                    '<td>' + remarks + '</td>' +
                '</tr>';
    },
    toView : function(date) {
        location.href = "/view/account?date=" + DateUtils.dateToString(date);
    }
}

DateUtils = {
    dateToString : function(org_date) {
        let date = new Date(org_date);
        return date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
    }
}

ValidCheck = {
    isMoney : function(money) {
        if (Number(money) >= 0) {
            return true;
        }
        alert("금액은 0이상의 숫자여야합니다.");
        return false;
    },
    isValidData : function(data) {
        if (!ValidCheck.isMoney(data.amount)) {
            return false;
        }
        //data.amount가 0이면 부호는 +로 설정
        if (Number(data.amount) == 0) {
            data.code = "PLUS";
        }
        return true;
    }
}

$(document).ready(function() {
    Account.getAccounts(DateUtils.dateToString($("#date").val()));
    Account.getTotalAmount(DateUtils.dateToString($("#date").val()));

    $("#createAccountBtn").click(function(e) {
        console.log(e);
        let data = {
            date : $("#date").val(),
            content : $("#createContent").val(),
            code : $("#createCode").val(),
            amount : $("#createAmount").val(),
            remarks : $("#createRemarks").val(),
        }
        if (!ValidCheck.isValidData(data)) return;
        Account.createAccount(JSON.stringify(data));
    });
    $("#updateAccountBtn").click(function(e) {
        console.log(e);
        let id = $("#updateId").val();
        let data = {
            date : $("#date").val(),
            content : $("#updateContent").val(),
            code : $("#updateCode").val(),
            amount : $("#updateAmount").val(),
            remarks : $("#updateRemarks").val(),
        }
        if (!ValidCheck.isValidData(data)) return;
        Account.updateAccount(id, JSON.stringify(data));
    });
    $("#deleteAccountBtn").click(function(e) {
        console.log(e);
        let id = $("#updateId").val();
        Account.deleteAccount(id);
    });
})

