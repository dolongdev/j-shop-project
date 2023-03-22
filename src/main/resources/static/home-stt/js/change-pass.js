const oldPass = document.getElementById('oldPass');
const newPass = document.getElementById('newPass');
const reNewPass = document.getElementById('reNewPass');
const divError = document.getElementById('rowError');
const errorMessage = document.getElementById('error-msg');
const btn = document.getElementById('changePassBtn');

newPass.addEventListener('blur', function () {
    if (newPass.value.length < 5){
        isError(true, 'Mật khẩu phải từ 5 kí tự trở lên!!');
    }else {
        isError(false, '');
        reNewPass.addEventListener('blur', function () {
            if (newPass.value !== reNewPass.value){
                isError(true, 'Nhập lại mật khẩu không đúng!');
            }else {
                isError(false, '');
                btn.removeAttribute('disabled');
                console.log(oldPass.value);
            }
        })
    }
})

function changePass() {
    const oldPassVal = oldPass.value;
    const newPassVal = newPass.value;
    fetch(`/api/accounts/changePass/${oldPassVal}/${newPassVal}`, {
        method: 'get'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        if (data){
            isError(true, 'Đổi mật khẩu thành công!');
        }else {
            isError(true, 'Sai mật khẩu!!');
        }
    })
}

function isError(flag, message){
    if (flag == true){
        divError.removeAttribute('hidden');
        errorMessage.innerText = message;
    }else {
        divError.setAttribute('hidden', '');
        errorMessage.innerText = '';
    }
}