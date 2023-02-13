<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
			<link href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css" rel="stylesheet" />
			<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
			<script src=" https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
			<!-- 
				noticeListAjax.jsp => return "notice/noticeListAjax.tiles"
				서버 : noticeListjson.do => json 데이터 반환
			-->
			<table id="example" class="display" style="width:100%">
				<thead>
					<tr>
						<th>글번호</th>
						<th>작성자</th>
						<th>제목</th>
						<th>조회수</th>
						<th>작성일자</th>
					</tr>
				</thead>
				<tfoot>
					<th>글번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성일자</th>
					</tr>
				</tfoot>
			</table>
			<script>
				$('#example').DataTable({
					// 여기서 넘겨줄 정보가 담긴 url 넘겨줌
					ajax: 'noticeListJson.do',
				});
			</script>