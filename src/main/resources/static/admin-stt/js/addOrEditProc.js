const urlParams = new URLSearchParams(window.location.search);

//xu li anh
const input = document.getElementById('file-input');
const image = document.getElementById('img-preview');

input.addEventListener('change', (e) => {
    if (e.target.files.length) {
        const src = URL.createObjectURL(e.target.files[0]);
        image.src = src;
    }
    const fileList = input.files;
    console.log(fileList);
});

function addImageToProc() {
    const productId = urlParams.get('productId');
    const formData = new FormData();
    formData.append('image', input.files[0]);

    fetch(`/api/products/image/${productId}`, {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            // console.log(data);
        })
        .catch(error => console.error(error));
}


function updateQuantityCS(id){
    const quantity = document.getElementById('quantity'+id).value;
    console.log(quantity);
    fetch(`/api/colorSizes/update?id=${id}&quantity=${quantity}`, {
        method: 'put'
    }).then(function (res) {
        return res.json();
    }).then(function (code) {
        console.info(code)
        const error = document.getElementById('errorMessage');
        error.innerText = 'Đã cập nhật!';
    })
}

function addPcAndCs() {
    const productId = urlParams.get('productId');
    const cId = document.getElementById('cId').value;
    const sId = document.getElementById('sId').value;
    const quantity = document.getElementById('quantityAdd').value;
    const error = document.getElementById('errorMessage');
    if (cId == 'null' || sId == 'null' || quantity < 0 || quantity == ''){
        console.log('Vui lòng nhập đủ thông tin!');
        error.innerText = 'Vui lòng nhập đủ thông tin!';
    }else {
        fetch(`/api/products/addPcAndCs?productId=${productId}&colorId=${cId}&sizeId=${sId}&quantity=${quantity}`, {
            method: 'post'
        }).then(function (res) {
            return res.json();
        }).then(function (data) {
            if (data){
                console.log('success!!' + data);
                error.innerText = 'Thêm thành công!';
                location.reload(true);
            }else {
                console.log('fail!!' + data);
                error.innerText = 'Đã tồn tại!';
            }
        })

    }
}

function deletePC(id) {
    fetch(`/api/productColors/delete/${id}`, {
        method: 'delete'
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        const error = document.getElementById('errorMessage');
        location.reload(true);
    })
}