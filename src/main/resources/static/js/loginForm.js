//로그인 modal 바로 띄우기
$(document).ready(function() {
    //msg : modal로 넘어온 로그인 실패 메시지
    if(msg !== null) {
            Swal.fire({
              title: '로그인 실패',
              icon: 'warning',
              confirmButtonColor: '#C7B199',
              confirmButtonText: '다시 시도',
              background: '#F3F1ED'
            }).then((result) => {
              if (result.isConfirmed) {
                 location.href = "/loginForm";
              }
            })
    }else{
        let loginModal = new bootstrap.Modal(document.getElementById("loginModal"),{
            keyboard : false,
            backdrop: 'static'
        })
        loginModal.show();
    }
});