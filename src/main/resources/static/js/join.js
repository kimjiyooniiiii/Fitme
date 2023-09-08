function userIdCheck() {
    const userId = $("#id").val();

    $.ajax({
        type: "post",
        url: "/checkDuplicatedId",
        data: {"userId": userId},
        dataType: "JSON",

        success: function(result) {
            if(result.result == "0") {
                if(confirm("이 아이디는 사용 가능합니다. \n 사용하시겠습니까?")) {
                    $("#userIdCheckButton").attr("disabled", true);
                    $("#id").attr("disabled", true);
                }
                return false;
            }
            else if(result.result == "1") {
                alert("이미 사용중인 아이디입니다.");
                $("#id").focus();
            }
        },
        error: function(request, status, error) {
            alert("ajax 실행 실패 : " + request.status + ", " + error);
        }
    });
}