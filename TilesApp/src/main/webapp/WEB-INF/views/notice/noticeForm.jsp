<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="noticeAdd.do" enctype="multipart/form-data" method="post">
	<table class="table">
		<tr>
			<th>제 목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea rows="10" cols="60" name="subject"></textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="attach"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input class="btn btn-primary" type="submit" value="저장">
				<input class="btn btn-warning" type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>