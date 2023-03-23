const phoneInp = document.getElementById('phone');
const createDateInp = document.getElementById('createDate');
const firstNameInp = document.getElementById('firstName');
const lastNameInp = document.getElementById('lastName');
const usernameInp = document.getElementById('username');
const emailInp = document.getElementById('email');
const passwordInp = document.getElementById('password');
const errorTag = document.getElementById('errorTag');
const errorMsg = document.getElementById('errorMessage')
const btnSubmitForm = document.getElementById('btnSubmitForm');
let usernameDelete = '';

function addNew() {
    phoneInp.value = '';
    firstNameInp.value = '';
    lastNameInp.value = '';
    usernameInp.value = '';
    emailInp.value = '';
    passwordInp.value = '';
    btnSubmitForm.setAttribute('onclick', 'addAccount()');
    usernameInp.removeAttribute('readonly');
    btnSubmitForm.innerText = 'Tạo tài khoản';
}

function addAccount(){
    fetch(`/api/accounts/add?username=${usernameInp.value}`
        +`&phone=${phoneInp.value}`
        +`&firstName=${firstNameInp.value}`
        +`&lastName=${lastNameInp.value}`
        +`&email=${emailInp.value}`
        +`&password=${passwordInp.value}`, {
        method: 'post'
    })
        .then(response => response.json())
        .then(data => {
            setError(true, 'Tạo thành công!!');
            setTimeout(function(){
                location.reload(true);
            }, 1000);
        })
        .catch(error => {
            console.error(error);
            setError(true, error);
        });
}

function getDetailAccount(username) {
    // window.location.href = '/administration/accounts/addOrEdit';
    // console.log(username);
    // formAddOrEdit.setAttribute('acction', '/administration/accounts/update/'+username)

    fetch(`/api/accounts/${username}`, {
        method: 'get'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        console.info(data);
        phoneInp.value = data.phone;
        createDateInp.value = data.createDate;
        firstNameInp.value = data.firstName;
        lastNameInp.value = data.lastName;
        usernameInp.value = data.username;
        emailInp.value = data.email;
        passwordInp.value = data.password;
        btnSubmitForm.setAttribute('onclick', 'updateAccount()');
        usernameInp.setAttribute('readonly', '');
        btnSubmitForm.innerText = 'Cập nhật thông tin';
    })
}

function updateAccount(){

    fetch(`/api/accounts/update/${usernameInp.value}?phone=${phoneInp.value}`
        +`&firstName=${firstNameInp.value}`
        +`&lastName=${lastNameInp.value}`
        +`&email=${emailInp.value}`
        +`&password=${passwordInp.value}`, {
        method: 'post'
    })
        .then(response => response.json())
        .then(data => {
            setError(true, 'Cập nhật thành công!!');
            setTimeout(function(){
                location.reload(true);
            }, 1000);
        })
        .catch(error => {
            console.error(error);
            setError(true, error);
        });
}

function sendRequestDelete(username) {
    usernameDelete = username;
    console.log(usernameDelete);
}

function deleteAccount(){
    fetch(`/api/accounts/delete/${usernameDelete}`, {
        method: 'delete'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        console.log(data.success);
        location.reload(true);
    }).catch(error => {
        console.error(error);
    });
}

function setError(status, message){
    if (status == true){
        errorTag.removeAttribute('hidden');
        errorMsg.innerText = message;
    }else {
        errorTag.setAttribute('hidden', '');
        errorMsg.innerText = '';
    }
}


