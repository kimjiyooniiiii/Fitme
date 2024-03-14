// 아이디 중복 확인
function userIdCheck() {
    const userId = $("#userId").val();
    const spendData = {"userId" : userId};

    $.ajax({
        type: "post",
        url: "/checkDuplicatedId",
        data : JSON.stringify(spendData),
        dataType: "json",               // 받을 데이터 타입
        contentType: "application/json; charset=UTF-8",     // 보낼 데이터 타입

        success: function(result) {
            console.log(result);
            console.log(spendData);

            if(!result.result) {
                if(confirm("이 아이디는 사용 가능합니다. \n 사용하시겠습니까?")) {
                    $("#userIdCheckButton").attr("disabled", true);
                    $("#id").attr("check_result", "success");
                }
                return false;
            }
            else if(result.result) {
                alert("이미 사용중인 아이디입니다.");
                $("#id").focus();
            }
        },
        error: function(request, status, error) {
            alert("ajax 실행 실패 : " + request.status + ", error : " + error);
            console.log("code: " + request.status)
            console.log("message: " + request.responseText)
            console.log("error: " + error);
            console.log("error: " + spendData);
        }
    });
}

// 인증번호 보내기
var certificationNumber = "";

function sendSms() {
    const phone = $("#phone").val();
    const spendData = {"phone" : phone};

    $.ajax({
        type: "post",
        url: "/send-sms",
        data: JSON.stringify(spendData),
        dataType: "json",               // 받을 데이터 타입
        contentType: "application/json; charset=UTF-8",     // 보낼 데이터 타입

        success: function(result) {
            console.log(result.num);
            alert("인증번호 전송완료");
            $("#certificationInput").focus();
            certificationNumber = result.num;
        },
        error: function(request, status, error) {
            alert("ajax 실행 실패 : " + request.status + ", " + error);
        }
    });
}

// 인증번호 일치확인
var passedPhone = 0;

function checkNumber() {
    if($("#certificationInput").val() == certificationNumber) {
        alert("인증이 완료되었습니다!");
        $("#checkNumButton").attr("disabled", true);
        $("#certificationInput").attr("disabled", true);
        $("#certificationInput").attr("check_result", "success");
        passedPhone = 1;
    }else {
        alert("인증번호가 일치하지 않습니다");
    }
}

// 가입하기 버튼
function joinComplete() {
    if($("#id").attr("check_result") === "fail") {
        alert("아이디 중복확인을 해주세요");
        $("#id").focus();
        return false;
    }
    else if($("#certificationInput").attr("check_result") === "fail") {
        alert("전화번호 인증을 완료해주세요");
        $("#certificationInput").focus();
        return false;
    }
    else{
        alert("회원가입 완료");
    }
}