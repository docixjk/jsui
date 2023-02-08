<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<style>
	.right{
		 text-align: right;
	}
	#addBtn{
		 text-align: center;
		 margin-right: 30px;
		 margin-top: 20px;
		 width: 100px;
		 border-style: none;
		 border-radius: 10px;
		 height: 40px
	}
</style>

<div class="right">
	<button id="addBtn">글 등록</button>
</div>
<table class="table">
	<thead>
		<tr>
	    <th>글번호</th>
	    <th>작성자</th>
	    <th>제목</th>
	    <th>조회수</th>
		</tr>
	</thead>
	<tbody>
    <c:forEach var="notice" items="${list}">
		<tr>
			<td>
				<a href="noticeDetail.do?nid=${notice.noticeId}">${notice.noticeId}</a>
			</td>
			<td>${notice.noticeWriter}</td>
			<td>${notice.noticeTitle}</td>
			<td>${notice.hitCount}</td>
		</tr>
     </c:forEach>
	</tbody>
</table>
<script>
	document.getElementById('addBtn').addEventListener('click',()=>{
		 location.href = "noticeForm.do";
	})
</script>
