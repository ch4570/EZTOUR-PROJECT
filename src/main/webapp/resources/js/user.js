<!-- 비밀번호 체크 모달 -->
const openPwCheck = document.getElementById("openPwCheck");
const pwCheckModal = document.querySelector("#pwCheckModal");
const pwCheckOverlay = pwCheckModal.querySelector("#pwCheckOverlay");
const openPwCheckModal = () => {
    delModal.classList.add("hidden")
    pwCheckModal.classList.remove("hidden");
}
const closePwCheckModal = () => {
    pwCheckModal.classList.add("hidden")
}
openPwCheck.addEventListener("click", openPwCheckModal);
pwCheckOverlay.addEventListener("click", closePwCheckModal);

<!-- 탈퇴 사유 모달 -->
const openDelButton = document.getElementById("openDelUsr");
const delModal = document.querySelector("#delModal");
const delOverlay = delModal.querySelector("#delOverlay");
const openDelUsrModal = () => {
    delModal.classList.remove("hidden");

}
const closeDelUsrModal = () => {
    delModal.classList.add("hidden")
}
openDelButton.addEventListener("click", openDelUsrModal);
delOverlay.addEventListener("click", closeDelUsrModal);

<!-- 탈퇴사유코드 다음 모달에 전달 -->
var drpRadioVal;
$(function() {
    $( "#openPwCheck:contains('탈퇴하기')" ).on("click" , function() {
        drpRadioVal = $('input[name="cmn_cd_drp"]:checked').val();
        alert(drpRadioVal);
        $('input[id=drpCd]').attr('value',drpRadioVal);
    });
});

<!-- 비밀번호 변경 모달 -->
const OpenPwdChangeModal = document.getElementById("OpenPwdChangeModal");
const pwdChangeModal = document.querySelector("#pwdChangeModal");
const pwdChangeOverlay = pwdChangeModal.querySelector("#pwdChangeOverlay");
const delBtn = delModal.querySelector("#closedelBtn")
const openPwChangeModal = () => {
    pwdChangeModal.classList.remove("hidden");
}
const closePwChangeModal = () => {
    pwdChangeModal.classList.add("hidden")
}
OpenPwdChangeModal.addEventListener("click", openPwChangeModal);
pwdChangeOverlay.addEventListener("click", closePwChangeModal);


