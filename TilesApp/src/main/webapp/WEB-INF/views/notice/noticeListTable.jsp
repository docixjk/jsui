<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
      <link href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css" rel="stylesheet" />
      <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
      <script src=" https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
      <div>
        작성자 : <input type="text" id="writer" value="user1" readonly>
        제 목 : <input type="text" id="title"><br>
        내 용 : <input type="text" id="subject">
        <button id="addBtn">저장</button>
        <button id="delBtn">삭제</button>
      </div>
      <br>
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
        <tbody>
          <c:forEach var="notice" items="${noticeList }">
            <tr>
              <td>${notice.noticeId }</td>
              <td>${notice.noticeWriter }</td>
              <td>${notice.noticeTitle }</td>
              <td>${notice.hitCount }</td>
              <td>
                <fmt:formatDate pattern="yy년 MM월 dd일" value="${notice.noticeDate }" />
              </td>
            </tr>
          </c:forEach>
        </tbody>
        <tfoot>
          <tr>
            <th>글번호</th>
            <th>작성자</th>
            <th>제목</th>
            <th>조회수</th>
            <th>작성일자</th>
          </tr>
        </tfoot>
      </table>
      <script>
        var t = $('#example').DataTable();

        $('#addBtn').on('click', function () {
          var formData = new FormData();
          formData.append('writer', $('#writer').val());
          formData.append('title', $('#title').val())
          formData.append('subject', $('#subject').val())

          // DB insert 후 화면 처리
          $.ajax({
            url: "noticeAddJson.do",
            method: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: (res) => {
              console.log(res);
              t.row.add([
                res.noticeId,
                res.noticeWriter,
                res.noticeTitle,
                res.hitCount,
                res.noticeDate
              ]).draw(false); // draw는 화면을 다시 그려주는것

            }, error: (err) => {
              console.log(err);
            }

          });
        });
        // tr 선택시 스타일 변경
        // id가 example인 table 밑 tbody에서 tr을 click하면 실행되는 함수
        $('#example tbody').on('click', 'tr', function () {
          if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
          } else {
            t.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
          }
          console.log($(this).children().eq(0).text());
          // 클릭할때마다 noticeId 값을 담아둔다
          localStorage.setItem('noticeId', $(this).children().eq(0).text());
        });

        // 삭제
        $('#delBtn').click(function () {
          t.row('.selected').remove().draw(false);
        });

      </script>