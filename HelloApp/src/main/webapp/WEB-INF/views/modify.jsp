<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
  EmpVO emp = (EmpVO) request.getAttribute("svo");
%>

<h3>현재 페이지는 empModForm.do의 결과 modify.jsp 입니다.</h3>
<form action="./empModify.do" method="get">
	<table class="table">
		<tr>
			<th>사원번호</th>
			<td><input type="text" value="<%=emp.getEmployeeId()%>" name="eid" readonly /></td>
		</tr>
		<tr>
			<th>FistName</th>
			<td><input type="text" value="<%=emp.getFirstName()%>" name="fname" /></td>
		</tr>
		<tr>
			<th>LastName</th>
			<td><input type="text" value="<%=emp.getLastName()%>" name="lname" /></td>
		</tr>
		<tr>
			<th>메일</th>
			<td><input type="text" value="<%=emp.getEmail()%>" name="email" /></td>
		</tr>
		<tr>
			<th>직무</th>
			<td><input type="text" value="<%=emp.getJobId()%>" name="job" /></td>
		</tr>
		<tr>
			<th>입사일자</th>
			<td><input type="text" value="<%=emp.getHireDate().substring(0,10) %>" name="hire" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" class="btn btn-primary" value="변경">
			</td>
	</table>
</form>

<jsp:include page="../includes/footer.jsp"></jsp:include>
