<%@page import="java.util.Map.Entry" %>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

	<!-- 0206 1교시 전송되게 만들기-->
<%
	Map<String, String> list =
		(Map<String, String>) request.getAttribute("jobList"); //Object -> (Map<String, String>) 타입으로 캐스팅
%>

	<form name="myFrm" action="employee.do" method="post"> <!--  전송 버튼을 눌렀을때 /employee.do로 이동 -->
        <h3>현재페이지는 /empForm.do 의 결과 emp.jsp 입니다. </h3>
        <table class="table">
        <tr>
            <th><label>사원번호:</label></th>
            <td><input type="number" name="eid"></td>
        </tr>

        <tr>
            <th><label>LastName:</label></th>
            <td><input type="text" name="last_name"></td>
        </tr>


        <tr>
            <th><label>이메일:</label></th>
            <td><input type="email" name="email"></td>
        </tr>

        <tr>
            <th><label>입사일자:</label></th>
            <td><input type="date" name="hire_date"></td>
        </tr>

        <tr>
            <th><label>직무:</label></th>
        <td><select name="job">
			<%
			for (Entry<String, String> ent : list.entrySet()) {
			%>
			
				<option value="<%=ent.getKey() %>"><%=ent.getValue() %></option>
			<%	
			}
				//0206 1교시
			%>
		</select></td>
		 </tr>

        <tr>
            <td><input type="submit" value="전송">
            <input type="button" value="조회" onclick="empGetFnc()">
		  <!--<input type="button" value="다음" onclick="daumGetFnc()"> -->
		 </tr>
		 <!-- <a href="../result/empList.jsp">조회</a> -->
		 <!--  /result/empLsit.jsp해도 됨. 나중에 보안 상 문제를 해결하기 위해 web-inf 밑으로 result 폴더내
		  jsp 파일들을 옮겨서 이게 안 되게 막을 것 -->
	   </table>
	</form>	
	<script>
		console.log(document.forms.myFrm);
		//크롬 콘솔창 가서 window 검색 후 document 열면 forms:HTMLCollection [form, myFrm: form] 볼 수 있다.
		function empGetFnc() {
			document.forms.myFrm.method = 'get'; //메소드 변경 post방식이면 입력이 될 것이기 때문.
			document.forms.myFrm.action = 'empList.do';
			document.forms.myFrm.submit(); //전송
		}
		
		//function daumGetFnc() {
		//document.forms.myFrm.method = 'delete'; //메소드 변경 post방식이면 입력이 될 것이기 때문.
		//document.forms.myFrm.submit();
		
	</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>     