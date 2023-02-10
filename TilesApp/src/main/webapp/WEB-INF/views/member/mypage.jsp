<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>


<h3>현재 페이지는 myPage.do의 결과 mypage.jsp 입니다.</h3>
<form action="modifyMember.do?mid=${vo.memberId}" method="post">

	<input type="file" id="fileUpload" accept="image/*" style="display: none" onchange="imageChangeFnc()">
	
	<table class="table">
	  <tr>
	    <th>아이디</th><td><input type="text" name="mid" value="${vo.memberId }" readonly></td>
	  </tr>
	  <tr>
	    <th>이름</th><td><input type="text" name="mname" value="${vo.memberName }"></td>
	  </tr>
	  <tr>
	    <th>비밀번호</th><td><input type="text" name="mpass" value="${vo.memberPw }"></td>
	  </tr>
	  <tr>
	    <th>연락처</th><td><input type="text" name="mphone" value="${vo.memberPhone }"></td>
	  </tr>
	  <tr>
	    <th>주소</th><td><input type="text" name="maddr" value="${vo.memberAddr }"></td>
	  </tr>
	  <tr>
	    <th>image</th><td><img id="imgSrc" width="150px" src="upload/${image}"></td>
	  </tr> 
	  <tr>
	  	<td colspan="2" align="center"><input type="submit" value="수정"></td>
	  </tr>
	</table>
</form>

<script>
	// event 등록 : addEventListener('type',function(){})
	// emem.on('click',function(){})
	$('#imgSrc').on('click',()=>{
		$('#fileUpload').click(); // fileUpload를 click을 하면 파일을 넣을수 있게 창이 생긴다.
	})
	
	function imageChangeFnc(){
		console.log($('#fileUpload')[0].files[0]);
		let file = $('#fileUpload')[0].files[0];
		
		let formData = new FormData(); // 빈 폼데이타 객체 생성 , multipart 처리
		// id 파라미터를 넣어줌
		formData.append('id', '${vo.memberId}'); // id와 file 업로드해서 서버에 저장하고 DB도 변경할거임
		formData.append('image', file);
		// 서버에 multipart/form-data : ajax 요청
		$.ajax({
			url : 'imageUpload.do',
			method : 'post',
			//멀티파트를 요청할때는 폼데이타라는 객체를 활용
			data : formData,
			contentType : false, // multipart 요청일 경우에 옵션
			processData : false,
			success : (res) => {
				console.log(res);
				// 화면에서도 선택된 이미지가 보여줌
				let reader = new FileReader();
				
				reader.onload = (ev) => { // 이벤트값이 들어감
					console.log(ev.target);
					// 서버에 이미지 정보를 바꾸고 화면에도 띄어준다
					$('#imgSrc').attr('src', ev.target.result);
					
				}
				
				reader.readAsDataURL(file);
				
			}, error : (err) => {
				console.log(err);
			}
		})
	}

</script>