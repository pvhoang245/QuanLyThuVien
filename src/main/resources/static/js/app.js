function preDeleteBook(bookId) {
    console.log(bookId)
    document.getElementById("confirmDeleteButton").href = "/books/delete/" + bookId;
}

function preDeleteCategory(categoryId) {
    console.log(categoryId)
    document.getElementById("confirmDeleteButton").href = "/categories/delete/" + categoryId;
}

function deleteBookById(bookId) {
    console.log(bookId);
    // fetch('https://localhost:8888/books/delete/' + bookId, {
    //     method: 'DELETE'
    // })
    //     .then(function(response) {
    //         if (response.ok) {
    //             alert("Customer deleted successfully!");
    //             location.reload();
    //         } else {
    //             throw new Error('Response not OK');
    //         }
    //     })
    //     .catch(function(error) {
    //         alert("Error: " + error.message);
    //     });
}

function searchProduct() {
    var chuoi = document.querySelector('#searchPro').value;
    var user = {
        "content": chuoi
    };
    fetch("http://localhost:8888/books/search/" + chuoi, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(datas => {
            var listUser = document.querySelector('#list_product');
            var content = datas.map(function(user) {
                return `<tr>
                <td>${user.productId}</td>
                <td>${user.productName}</td>
                <td><img src="${user.image}" alt="" width="100px;"></td>
                <td>${user.quantity}</td>
                <td>${user.numberSell}</td>
                <td style="width: 30px;">${user.manufacturer}</td>
                <td>${user.color}</td>
                <td><span class="badge bg-success">${user.size}</span></td>
                <td>${user.price}</td>
                <td>${user.category.categoryName}</td>
                <td>${user.description}</td>
                <td><button class="btn btn-primary btn-sm trash" type="button" title="Xóa" onclick="popupDaleteProduct(${user.productId})"><i class="fas fa-trash-alt"></i></button>
                    <button class="btn btn-primary btn-sm edit hide" type="button" title="Sửa" id="accept_delete" data-toggle="modal" data-target="#deleteModal"><i class="fas fa-trash-alt"></i></button>
                    <button class="btn btn-primary btn-sm edit" type="button" title="Sửa" onclick="getProductById(${user.productId})"><i class="fas fa-edit"></i></button>
                    <button class="btn btn-primary btn-sm edit hide" type="button" title="Sửa" id="show-emp" data-toggle="modal" data-target="#ModalUP"><i class="fas fa-edit"></i></button>
                </td></tr>`;
            });
            listUser.innerHTML = content.join('');
        })
        .catch(error => console.error(error));
}