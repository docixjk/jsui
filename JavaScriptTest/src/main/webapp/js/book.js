/**
 * 
 */

// bookList배열을 활용해서 처리하도록 하세요.
let bookList = [
	{ bookCode: 'P0206001', bookTitle: '이것이자바다', bookAuthor: '홍성문', bookPress: '신흥출판사', bookPrice: '20000' },
	{ bookCode: 'P0206002', bookTitle: '이것이자바스크립트다', bookAuthor: '홍영웅', bookPress: '우리출판사', bookPrice: '25000' },
]
function makeTh() {
	let tt = document.getElementById('title')
	let titles = ["도서코드", "도서명", "저자", "출판사", "가격"];
	let tr = document.createElement("tr");

	let th = document.createElement('th');

	let ip = document.createElement('input')
	ip.setAttribute("type", "checkbox")
	tr.append(ip)

	titles.forEach((title) => {
		th = document.createElement('th');
		th.innerText = title;
		tr.append(th);
	})
	th = document.createElement("th");
	th.innerText = "삭제";
	tr.append(th);
	tt.append(tr)
}
makeTh();

function makelist(item) {
	let list = document.getElementById('list')
	let titles = ["bookCode", "bookTitle", "bookAuthor", "bookPress", "bookPrice"];
	let tr = document.createElement("tr");

	let td = document.createElement('td');

	let ip = document.createElement('input')
	ip.setAttribute("type", "checkbox")
	tr.append(ip)

	titles.forEach((title) => {
		td = document.createElement('td');
		td.innerText = item[title];
		tr.append(td);
	})
	td = document.createElement("td");
	let btn = document.createElement("button");
	btn.innerText = "삭제";
	btn.id = "del";
	tr.append(btn);
	list.append(tr)
}

for (let i = 0; i < bookList.length; i++) {

	makelist(bookList[i]);
}

document.querySelector("thead input[type='checkbox']").addEventListener("change", allcb);

document.querySelector("#add").addEventListener('click', addList);

function allcb() {
	document.querySelectorAll("tbody input[type='checkbox']").forEach(cb => {
		cb.checked = this.checked;
	});

}


function addList() {
	let bookCode = document.querySelector('input[name="bookCode"]').value;
	let bookName = document.querySelector('input[name="bookName"]').value;
	let author = document.querySelector('input[name="author"]').value;
	let press = document.querySelector('input[name="press"]').value;
	let price = document.querySelector('input[name="price"]').value;
	let title = [bookCode, bookName, author, press, price];
	if (!bookCode || !bookName || !author || !press || !price) {
		alert("필수 입력값을 확인!!!");
		return;
	}
	let list = document.getElementById('list')
	let tr = document.createElement("tr");
	let td = document.createElement('td');
	let ip = document.createElement('input')
	ip.setAttribute("type", "checkbox")
	tr.append(ip)
	title.forEach((title) => {
		td = document.createElement('td');
		td.innerText = title;
		tr.append(td);
	})

	bookCode.value = '';
	bookName.value = '';
	author.value = '';
	press.value = '';
	price.value = '';
	td = document.createElement("td");
	let btn = document.createElement("button");
	btn.innerText = "삭제";
	btn.id = "del";
	tr.append(btn);
	list.append(tr);
}

document.querySelector("#del").addEventListener("click", (ev) => {
	let del = ev.target.closest("tr");
	del.remove();
})

document.querySelector('#checkDel').addEventListener("click", chkdel);

function chkdel() {
	let chks = document.querySelectorAll('#list input[type="checkbox"]:checked');

}