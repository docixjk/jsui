/**
 * manage.js
 */

console.log("manage.js start......");

function updateMemberFnc(e) {
	// modifyMember.do 사용자 정보 수정.
	let tr = $(e.target).parent().parent();
	
	console.log(tr.find("input.name").val());
	console.log(tr.find("input.phone").val()); // 입력된 값을 불러옴 
	console.log(tr.find("input.addr").val());
	console.log(tr.find("input.auth").val());
} // end of updateMemberFnc()

$(document).ready(function() {
	// 목록가져오는 Ajax 호출.
	console.log($("#list"));
	$.ajax({
		url: "memberList.do",
		success: function(result) {
			console.log(result);
			$(result).each(function(idx, item) {
				$("#list").append(makeRow(item));
			});
		},
		error: function(reject) {
			console.log(reject);
		},
	});

	// 등록이벤트
	$("#addBtn").on("click", function() {
		let id = $("#mid").val(); // element.value 속성읽어옴.
		let name = $("#mname").val();
		let phone = $("#mphone").val();
		let addr = $("#maddr").val();
		let img = $("#mimage")[0].files[0];

		let formData = new FormData();
		formData.append("id", id);
		formData.append("name", name);
		formData.append("phone", phone);
		formData.append("addr", addr);
		formData.append("img", img);

		$.ajax({
			url: "addMember.do",
			method: "post",
			data: formData,
			contentType: false,
			processData: false,
			success: function(result) {
				// 처리된 정보를 화면 생성.
				console.log(result);
				if (result.retCode == "Success") {
					$("#list").append(makeRow(result.member));
				} else {
					alert("입력미완!");
				}
			},
			error: function(error) {
				console.log(error);
			},
		});
	});

	function makeRow(member = {}) {
		// member값을 활용해서 tr생성.
		let tr = $("<tr />"); // document.createElement('tr')
		tr.on("dblclick", function(e) {
			let id = $(this).children().eq(0).text();
			let name = $(this).children().eq(1).text();
			let phone = $(this).children().eq(2).text();
			let addr = $(this).children().eq(3).text();
			let resp = $(this).children().eq(4).text();

			let nTr = $("<tr />").append(
				$("<td />").text(id),
				$("<td />").append($('<input class="name" />').val(name)),
				$("<td />").append($('<input class="phone" />').val(phone)),
				$("<td />").append($('<input class="addr" />').val(addr)),
				$("<td />").append($('<input class="auth"/>').val(resp)),
				$("<td />").append(
					$('<button onclick="updateMemberFnc(event)">수정</button>')
				) //.on('click', updateMemberFnc))
			);
			// 새로운 tr로 기존 tr을 대신.
			$(this).replaceWith(nTr);
		});
		tr.append(
			$("<td />").text(member.memberId),
			$("<td />").text(member.memberName),
			$("<td />").text(member.memberPhone),
			$("<td />").text(member.memberAddr),
			$("<td />").text(member.responsibility),
			$("<td />").append(
				$("<button>삭제</button>") //
					.attr("mid", member.memberId) //
					.on("click", deleteMemberFnc)
			)
		);
		return tr;
	}

	function deleteMemberFnc(e) {
		if (!window.confirm("삭제하시겠습니까?")) {
			return;
		}

		let user = $(e.target).attr("mid");

		$.ajax({
			url: "removeMember.do",
			data: {
				id: user,
			}, // removeMember.do?id=user
			success: function(result) {
				if (result.retCode == "Success") {
					$(e.target).parent().parent().remove();
				} else {
					alert("삭제건수가 없습니다!");
				}
			},
			error: function(reject) {
				alert(
					"처리코드: " +
					reject.status +
					"\n" + //
					"에러메세지: " +
					reject.statusText
				);
			},
		});
	} // end of deleteFnc
});
