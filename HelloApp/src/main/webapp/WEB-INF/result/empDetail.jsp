<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
EmpVO emp = (EmpVO) request.getAttribute("searchVO");
//request 는 선언하지 않았지만 톰캣의 내장객체라 쓸 수 있음.
//getAttribute가 모든 정보를 받아오기 위해 object 타입
//(EmpVO) 라고 해서 EmpVO 타입으로 변환해서 받음.
Integer age = (Integer) request.getAttribute("myAge");
String id = (String) request.getAttribute("loginId");
%>
<%=age%>,
<%=id%>
<h3>현재 페이지는 empDetail.do의 결과 empDetail.jsp 입니다.</h3>
<table class="table">
	<tr>
		<th>사원번호</th>
		<td><%=emp.getEmployeeId()%></td>
	</tr>
	<tr>
		<th>FirstName</th>
		<td><%=emp.getFirstName()%></td>
	</tr>
	<tr>
		<th>LastName</th>
		<td><%=emp.getLastName()%></td>
	</tr>
	<tr>
		<th>메일</th>
		<td><%=emp.getEmail()%></td>
	</tr>
	<tr>
		<th>직무</th>
		<td><%=emp.getJobId()%></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<%
			if (id != null) {
			%>
			<button class="btn btn-primary"
				onclick="location.href='empModForm.do?id=<%=emp.getEmployeeId()%>'">수정</button>
			<button class="btn btn-warning"
				onclick="location.href='empRemove.do?id=<%=emp.getEmployeeId()%>'">삭제</button>
			<!-- empRemove.do?id=? / removeEmp(int id) / 삭제후 목록으로 --> <%
			 } else {
			 %> <!--  <span>Guest</span> --> <%
			 }
			 %>
		</td>
	</tr>
</table>

<jsp:include page="../includes/footer.jsp"></jsp:include>
