<%@page import="order.PointBean"%>
<%@page import="java.util.Vector"%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="mgr" class="order.PointMgr"/>

<%@ include file="../top.jsp" %>

	<%@ include file="../order/mypage_side.jsp"%> 
	
<%
		request.setCharacterEncoding("EUC-KR");
		
%>
	<style>
		.point_blue{
			color: #4b4bf9;
			text-shadow: 0.5px 0.5px 0.5px #4b4bf9;
		}
		.point_red{
			color: #f74d4d;
			text-shadow: 0.5px 0.5px 0.5px #f74d4d;
		}
	</style>
	<div id="mypage">
	
		<h1 class="title">����Ʈ����</h1>
	
		<table class="mypage_table horHead">	
						
			<tr id="column_tr">
				<th>��ȣ</th>
				<th>����</th>
				<th>�ݾ�</th>
				<th>��¥</th>
			</tr>
				<%
					Vector<PointBean> plist = mgr.getPointList(id);
					System.out.println("����Ʈ����Ʈ�� ������?:"+plist.size());	
					int listSize =plist.size(); 
					if(plist.isEmpty()){
				%>
					<tr>
						<td colspan="4">����Ʈ ������ �����ϴ�</td>
					</tr>
				<%
					}else{
					for(int i=0; i<plist.size(); i++){
					PointBean pbean = plist.get(i);
					int num = i+1;
				%>
			<tr>
			<td><%=num%></td>		
			<td class="btn_td"><a href="../order/mypage_order_view.jsp?order=<%=pbean.getO_index()%>"><%=pbean.getPt_detail()%></a></td>
			<%if(pbean.getPt_point()>0){%>	
			<td><font class="point_blue"><%=UtilMgr.intFormat(pbean.getPt_point())%></font></td>
			<%}else if(pbean.getPt_point()<0){%>
			<td><font class="point_red"><%=UtilMgr.intFormat(pbean.getPt_point())%></font></td>
			<%}else{%>
			<td><%=UtilMgr.intFormat(pbean.getPt_point())%></td>
			<%}%>
			<td><%=pbean.getPt_date()%></td>
			</tr>
			<%}//--for%>
		<%}//--else%>
			
		</table>
	</div>

</div> <!--  #btn_mypage_wrapper (��ư�޴� + mypage) : mypage_side.jsp ���� ����-->
</div> <!-- #main (��ܿ�� + ��ư + mypage) : mypage_side.jsp ���� ����-->
	<%@ include file="../bottom.jsp" %>
</body>
</html>
</html>