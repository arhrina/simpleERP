Account = {
    getAccounts : function(now) {
        $.ajax({
            url : 'http://localhost:8080/account?date=' + now.getFullYear() + '-' + ('0' + (now.getMonth() + 1)).slice(-2) + '-' + ('0' + now.getDate()).slice(-2),
            method : 'GET',
            contentType : 'application/json',
            success : function(data) {
                console.log(data);
                if (data.length > 0)
                    $("tbody").empty();
                data.forEach(function(e) {
                    console.log("e", e);
                    let row =
                    '<tr onClick="openMd(\'account-modify\', this);">' +
                        '<td>' + e.id + '</td>' +
                        '<td>' + e.date + '</td>' +
                        '<td>' + e.content + '</td>' +
                        '<td class="price ' + e.code.toLowerCase() + '"><span>' + e.amount + '</span></td>' +
                        '<td>' + e.remarks + '</td>' +
                    '</tr>';
                    $("tbody").append(row);
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
            url : 'http://localhost:8080/account/amount?date=' + now.getFullYear() + '-' + ('0' + (now.getMonth() + 1)).slice(-2) + '-' + ('0' + now.getDate()).slice(-2),
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
    }
}

$(document).ready(function() {
    let date = new Date($("#date").text());
    Account.getAccounts(date);
    Account.getTotalAmount(date);

    $("#createAccountBtn").click(function(e) {
        console.log(e);
        let data = {
            date : $("#date").text(),
            content : $("#createContent").val(),
            code : $("#createCode").val(),
            amount : $("#createAmount").val(),
            remarks : $("#createRemarks").val(),
        }
        if (!Number(data.amount)) {
            alert("금액은 숫자여야합니다.");
            return;
        }
        Account.createAccount(JSON.stringify(data));
    });
    $("#updateAccountBtn").click(function(e) {
        console.log(e);
        let id = $("#updateId").val();
        let data = {
            date : $("#date").text(),
            content : $("#updateContent").val(),
            code : $("#updateCode").val(),
            amount : $("#updateAmount").val(),
            remarks : $("#updateRemarks").val(),
        }
        if (!Number(data.amount)) {
            alert("금액은 숫자여야합니다.");
            return;
        }
        Account.updateAccount(id, JSON.stringify(data));
    });
    $("#deleteAccountBtn").click(function(e) {
        console.log(e);
        let id = $("#updateId").val();
        Account.deleteAccount(id);
    });
})

