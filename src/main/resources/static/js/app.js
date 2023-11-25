function showNotification(mess) {
    var notification = document.querySelector('.notification');
    notification.innerHTML = mess;
    notification.classList.remove('hide');
    notification.classList.remove('notification_hide');
    setTimeout(function () {
        notification.classList.add('notification_hide');
    }, 4000);
}

function preDeleteBook(bookId) {
    console.log(bookId)
    document.getElementById("confirmDeleteButton").href = "/books/delete/" + bookId;
}

function preDeleteUser(userId) {
    console.log(userId)
    let id = parseInt(userId)
    document.getElementById("confirmDeleteButton").href = "/users/delete/" + id;
}

function preDeleteCategory(categoryId) {
    console.log(categoryId)
    document.getElementById("confirmDeleteButton").href = "/categories/delete/" + categoryId;
}
function preDeleteReader(categoryId) {
    console.log(categoryId)
    document.getElementById("confirmDeleteButton").href = "/readers/delete/" + categoryId;
}

function preChoseReader(readerId) {
    localStorage.setItem("readerId", readerId);
}

//Tìm book trang book
function searchBookInBook() {
    var chuoi = document.querySelector('#searchBook').value;
    fetch("http://localhost:8888/books/search/" + chuoi, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(datas => {
            var listUser = document.querySelector('#listBook');
            var content = datas.map(function(book) {
                return `<tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.totalNumber}</td>
                <td>${book.borrowNumber}</td>
                <td>${book.location}</td>
                <td>${book.category.name}</td>
                <td>
                <a href="/books/update/${book.id}"
                      class="btn btn-primary">Update</a>
                <a data-toggle="modal" data-target="#deleteModal" onclick="preDeleteBook(${book.id})"
                      class="btn btn-danger">Delete</a>
                </td></tr>`;
            });
            listUser.innerHTML = content.join('');
        })
        .catch(error => console.error(error));
}
//Tìm book mượn
function searchBook() {
    var chuoi = document.querySelector('#searchBook').value;
    fetch("http://localhost:8888/books/search/" + chuoi, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(datas => {
            var listUser = document.querySelector('#listSearchBookBorrow');
            var content = datas.map(function(book) {
                return `<tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.totalNumber}</td>
                <td>${book.location}</td>
                <td><button class="btn btn-primary" type="button" onclick="addToList('${book.id}')">Chọn</button>
                </td></tr>`;
            });
            listUser.innerHTML = content.join('');
        })
        .catch(error => console.error(error));
}

//Tìm reader trang độc giả
function searchReader() {
    let chuoi = document.querySelector('#searchReader').value;
    fetch("http://localhost:8888/readers/search/" + chuoi, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(datas => {
            var listUser = document.querySelector('#listSearchReader');
            var content = datas.map(function(reader) {
                return `<tr>
                <td>${reader.id}</td>
                <td>${reader.name}</td>
                <td>${reader.phone}</td>
                <td>${reader.email}</td>
                <td>${reader.address}</td>
                <td><button class="btn btn-primary" type="button" onclick="addToList('${reader.id}')">Chọn</button>
                </td></tr>`;
            });
            listUser.innerHTML = content.join('');
        })
        .catch(error => console.error(error));
}

//Thêm sách vào hàng chờ mượn
async function addToList(bookId) {
    let checkToAdd = false;
    let readerId = JSON.parse(localStorage.getItem("readerId"));
    // kiểm tra có đang mượn mà chưa trả không
    let user = {
        "status": bookId,
        "readerId": readerId
    };
    await fetch("http://localhost:8888/bookreturn/find", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(data => {
            if (data && data.status == "Borrowing") {
                showNotification("Cuốn sách này chưa được trả lại");
                checkToAdd = true;
                }
        })
        .catch(function (error) {
            alert("Error: " + error.message);
        });
    if (checkToAdd) return;
    //kiểm tra tồn tại bên hàng chờ chưa
    let check = document.getElementById("book_" + bookId);
    if (check) {
        showNotification("Đã có trong hàng chờ");
        return;
    }

    // tạo list
    let array = JSON.parse(localStorage.getItem("listBorrowBook"));
    array.push(bookId);
    localStorage.setItem("listBorrowBook", JSON.stringify(array));
    // thêm vào hàng chờ
    fetch("http://localhost:8888/books/" + bookId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            var list = document.querySelector('#listWaitBookBorrow');
            var content =`
                <tr id="book_${data.id}" class="">
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.author}</td>
                <td>${data.totalNumber}</td>
                <td>${data.location}</td>
                <td><button class="btn btn-primary" type="button" title="Xóa" onclick="removeFromList('${data.id}')">Xóa</button>
                </td></tr>`;
            list.innerHTML += content;
        })
        .catch(error => console.error(error));
}

//Xóa sách khỏi hàng chờ mượn
function removeFromList(bookId) {
    document.getElementById("book_"+bookId).remove();
    let array = JSON.parse(localStorage.getItem("listBorrowBook"));
    array = array.filter(item => item!== bookId);
    localStorage.setItem("listBorrowBook", JSON.stringify(array));
}

//Xác nhận mượn sách
async function borrowBook() {
    let check = false;
    let array = JSON.parse(localStorage.getItem("listBorrowBook"));
    let userId = JSON.parse(localStorage.getItem("userId"));
    let readerId = JSON.parse(localStorage.getItem("readerId"));
    let today = new Date();
    let day = today.getDate();
    let month = today.getMonth() + 1;
    let year = today.getFullYear();
    let formattedDate = year + '-' + month + '-' + day;

    let user0 = {
        "book": array
    };
    await fetch("http://localhost:8888/books/checkNumber" , {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user0)
    })
        .then(response => response.json())
        .then(data => {
            if(data.length >= 1) {
                check = true;
                let mess = "";
                for (let i = 0; i < data.length - 1; i++) {
                    mess += data[i] + ", ";

                }
                mess += data[data.length - 1];
                showNotification("Cuốn sách có mã " + mess + " đã hết trong kho sách");
                for (let i = 0; i < data.length; i++) {
                    let bookElement = document.getElementById("book_" + data[i]);
                    bookElement.classList.add('make_text_red');
                }
            }
        })
        .catch(error => console.error(error));

    if(check) return;
    let user = {
        "dateBorrow": formattedDate,
        "book": array,
        "userId": userId,
        "readerId": readerId
    };
    await fetch("http://localhost:8888/bookborrow/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(function (response) {
            if (response.ok) {
                alert("Borrowed successfully!");
                location.reload();
            } else {
                throw new Error('Response not OK');
            }
        })
        .catch(function (error) {
            alert("Error: " + error.message);
        });
}

//Thêm sách vào hàng chờ trả
function addToReturnList(bookId) {
    let array = JSON.parse(localStorage.getItem("listReturnBook"));
    array.push(bookId);
    localStorage.setItem("listReturnBook", JSON.stringify(array));
    document.getElementById("listBook_" + bookId).remove();
    fetch("http://localhost:8888/books/" + bookId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            var list = document.querySelector('#listWaitBookReturn');
            var content = `
                <tr id="book_${data.id}">
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.author}</td>
                <td>${data.location}</td>
                <td><button class="btn btn-primary" type="button" title="Xóa" onclick="removeFromReturnList('${data.id}')">Xóa</button>
                </td></tr>`;
            list.innerHTML += content;
        })
        .catch(error => console.error(error));
}

//Xóa sách khỏi hàng chờ trả
function removeFromReturnList(bookId) {
    document.getElementById("book_" + bookId).remove();
    let array = JSON.parse(localStorage.getItem("listReturnBook"));
    array = array.filter(item => item !== bookId);
    localStorage.setItem("listReturnBook", JSON.stringify(array));
    fetch("http://localhost:8888/books/" + bookId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            var listUser = document.querySelector('#listBorrowedBook');
            console.log(bookId)
            console.log(data)
            var content = `<tr id="listBook_${data.id}">
                <td>${data.id}</td>
                <td>${data.name}</td>
                <td>${data.author}</td>
                <td>${data.location}</td>
                <td><button class="btn btn-primary" type="button" title="Chọn"
                     onclick="addToReturnList('${data.id}')">Chọn</button></td></tr>`;
            listUser.innerHTML += content;
        })
        .catch(error => console.error(error));
}

//Xác nhận trả sách
function returnBook() {
    let list = JSON.parse(localStorage.getItem("listReturnBook"));
    let readerId = JSON.parse(localStorage.getItem("readerId"));
    let user = {
        "readerId": readerId,
        "book": list
    }
    fetch("http://localhost:8888/bookreturn/add", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(function (response) {
            if (response.ok) {
                alert("Returned successfully!");
                location.reload();
            } else {
                throw new Error('Response not OK');
            }
        })
        .catch(function (error) {
            alert("Error: " + error.message);
        });
}

//Kiểm tra mã sách tồn tại khi thêm
async function checkSaveBook() {
    let bookId = document.querySelector('input[name="id"]').value;
    await fetch("http://localhost:8888/books/check/"+bookId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            if(data) {
                showNotification("Cuốn sách có mã "+bookId+" đã có, vui lòng nhập mã khác.")
            } else {
                document.getElementById("saveNewBook").click();
            }
        })
        .catch(error => console.error(error));
}

async function checkSaveCategory() {
    let name = document.querySelector('input[name="name"]').value;
    await fetch("http://localhost:8888/categories/search/"+name, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            if(data) {
                showNotification("Thể loại có tên "+name+" đã có, vui lòng nhập thể loại khác.")
            } else {
                document.getElementById("saveNewCategory").click();
            }
        })
        .catch(error => console.error(error));
}