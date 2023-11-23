function preDeleteBook(bookId) {
    console.log(bookId)
    document.getElementById("confirmDeleteButton").href = "/books/delete/" + bookId;
}

function preDeleteCategory(categoryId) {
    console.log(categoryId)
    document.getElementById("confirmDeleteButton").href = "/categories/delete/" + categoryId;
}

function preChoseReader(readerId) {
    localStorage.setItem("readerId", readerId);
}


function searchBook() {
    var chuoi = document.querySelector('#searchBook').value;
    fetch("http://localhost:8888/search/" + chuoi, {
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
                <td><button class="btn btn-primary" type="button" title="Xóa" onclick="addToList('${book.id}')">Chọn</button>
                </td></tr>`;
            });
            listUser.innerHTML = content.join('');
        })
        .catch(error => console.error(error));
}

function addToList(bookId) {
    let array = JSON.parse(localStorage.getItem("listBorrowBook"));
    array.push(bookId);
    localStorage.setItem("listBorrowBook", JSON.stringify(array));
    let check = document.getElementById("book_"+bookId);
    if(check) return;
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
                <tr id="book_${data.id}">
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

function removeFromList(bookId) {
    document.getElementById("book_"+bookId).remove();
    let array = JSON.parse(localStorage.getItem("listBorrowBook"));
    array = array.filter(item => item!== bookId);
    localStorage.setItem("listBorrowBook", JSON.stringify(array));
}

function borrowBook() {
    let array = JSON.parse(localStorage.getItem("listBorrowBook"));
    let userId = JSON.parse(localStorage.getItem("userId"));
    let readerId = JSON.parse(localStorage.getItem("readerId"));
    var today = new Date();
    var day = today.getDate();
    var month = today.getMonth() + 1;
    var year = today.getFullYear();

    // Hiển thị ngày hiện tại trong định dạng "Ngày/Tháng/Năm"
    var formattedDate = year + '-' + month + '-' + day;
        var user = {
            "dateBorrow": formattedDate,
            "book": array,
            "userId": userId,
            "readerId": readerId
        };
        fetch("http://localhost:8888/bookborrow/add", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(function(response) {
                if (response.ok) {
                    alert("Product added successfully!");
                    location.reload();
                } else {
                    throw new Error('Response not OK');
                }
            })
            .catch(function(error) {
                alert("Error: " + error.message);
            });
}