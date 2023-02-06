/**
 *
 */

// bookList배열을 활용해서 처리하도록 하세요.
let bookList = [
  {
    bookCode: "P0206001",
    bookTitle: "이것이자바다",
    bookAuthor: "홍성문",
    bookPress: "신흥출판사",
    bookPrice: "20000",
  },
  {
    bookCode: "P0206002",
    bookTitle: "이것이자바스크립트다",
    bookAuthor: "홍영웅",
    bookPress: "우리출판사",
    bookPrice: "25000",
  },
];
function makeTh() {
  let tt = document.getElementById("title");
  let titles = ["도서코드", "도서명", "저자", "출판사", "가격"];
  let tr = document.createElement("tr");

  let th = document.createElement("th");

  let ip = document.createElement("input");
  ip.setAttribute("type", "checkbox");
  tr.append(ip);

  titles.forEach((title) => {
    th = document.createElement("th");
    th.innerText = title;
    tr.append(th);
  });
  th = document.createElement("th");
  th.innerText = "삭제";
  tr.append(th);
  tt.append(tr);
}
makeTh();
makelist();

// function makelist() {
//   list.innerHTML = "";
//   for (let i = 0; i < bookList.length; i++) {
//     let add = `
//       <tr>
//         <td><input type="checkbox"></td>
//         <td>${bookList[i].bookCode}</td>
//         <td>${bookList[i].bookTitle}</td>
//         <td>${bookList[i].bookAuthor}</td>
//         <td>${bookList[i].bookPress}</td>
//         <td>${bookList[i].bookPrice}</td>
//         <td><button type="button">삭제</td>
//       </tr>`;
//     list.innerHTML += add;
//   }
// }
function makelist() {
  list.innerHTML = "";
  for (let i = 0; i < bookList.length; i++) {
    let tr = document.createElement("tr");

    let td = document.createElement("td");

    let input = document.createElement("input");
    input.setAttribute("type", "checkbox");
    td.className = "sm";
    td.append(input);
    tr.append(td);

    for (let j in bookList[i]) {
      td = document.createElement("td");
      td.innerText = bookList[i][j];
      tr.append(td);
    }
    td = document.createElement("td");
    let btn = document.createElement("button");
    btn.innerText = "삭제";
    td.className = "del";
    td.append(btn);
    tr.append(td);
    list.append(tr);
    btn.addEventListener("click", deleteOneSelect);
  }
}
// list.addEventListener("click", function (ev) {
//   if (ev.target.nodeName == "BUTTON") {
//     let delCode = ev.target.closest("tr").children[1];
//     let index = bookList.findIndex((e) => e.bookCode == delCode.innerText);
//     bookList.splice(index, 1);
//   }
//   // makelist();
// });

function deleteOneSelect() {
  let delCode = this.closest("tr").children[1];
  let index = bookList.findIndex((e) => e.bookCode == delCode.innerText);
  bookList.splice(index, 1);
  makelist();
}

document
  .querySelector("#title input[type='checkbox']")
  .addEventListener("click", allCheckBox);

function allCheckBox() {
  let title = document.querySelector("#title input[type='checkbox']");
  document.querySelectorAll("#list input[type='checkbox']").forEach((cb) => {
    if (title.checked == true) {
      cb.checked = true;
    } else {
      cb.checked = false;
    }
  });
}
document.querySelector("#add").addEventListener("click", addList);

function addList() {
  let add = {
    bookCode: bookCode.value,
    bookTitle: bookName.value,
    bookAuthor: author.value,
    bookPress: press.value,
    bookPrice: price.value,
  };
  bookList.push(add);
  console.log(bookList);
  makelist();

  bookCode.value = "";
  bookName.value = "";
  author.value = "";
  press.value = "";
  price.value = "";
}

checkDel.addEventListener("click", checkdel);

function checkdel() {
  // list 를 돌면서 체크드를 확인
  document
    .querySelectorAll("#list input[type='checkbox']:checked")
    .forEach((chk) => {
      let delCode = chk.closest("tr").children[1];
      let index = bookList.findIndex((e) => e.bookCode == delCode.innerText);
      bookList.splice(index, 1);

      makelist();
    });
}
