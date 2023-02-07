<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@page import="co.yedam.emp.service.EmpService"%>
<%@page import="co.yedam.emp.service.EmpServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<%
		//jdbc방식
		//EmpService service = new EmpServiceImpl();
		//List<EmpVO> list = service.empList();
		//0206 5교시
		List<EmpVO> list = (List<EmpVO>) request.getAttribute("empList");
	%>
	
	<h3>사원목록(EmpControl.do 의 결과페이지.)</h3>
	<table class="table">
		<thead>
			<tr>
				<th>사원번호</th>
				<th>LastName</th>	
				<th>FirstName</th>
				<th>Email</th>
				<th>직무</th>
				<th>입사날짜</th>			
			</tr>
		</thead>
		
		<tbody>
			<% for (EmpVO emp : list) { %>
				<tr>
					<td><a href="empDetail.do?eid=<%=emp.getEmployeeId() %>"><%=emp.getEmployeeId() %></a></td>
					<td><%=emp.getLastName() %></td>
					<td><%=emp.getFirstName() %></td>
					<td><%=emp.getEmail() %></td>
					<td><%=emp.getJobId() %></td>					
					<td><%=emp.getHireDate() %></td>
				</tr>			
			<%}
			%>
		</tbody>
	</table>
<jsp:include page="../includes/footer.jsp"></jsp:include>         