<!-- goods_view.jsp -->
<%@page import="product.ProductBean"%>
<%@page import="java.util.Vector"%>
<%@page import="java.sql.Date"%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="mgr" class="product.ProductMgr"/>
<jsp:useBean id="ap_mgr" class="admin.ProductMgr"/>
<%
	request.setCharacterEncoding("EUC-KR");
	int p_code = Integer.parseInt(request.getParameter("p_code"));
	ProductBean bean = mgr.getProduct(p_code);
	Vector<ProductBean>rm_vlist = ap_mgr.getRmDatas(p_code);
	int rmSize = rm_vlist.size();
%>
<html>
<head>
<!-- <title>-������-��ǰ ���</title>  -->



</head>
<body topmargin="100">	

<link rel="stylesheet" type="text/css" href="./css/goods_view.css">
<!-- <title>-������-��ǰ ���</title> -->
<!-- <link rel="stylesheet" type="text/css" href="./css/mypage_orderlist.css"> -->
<%@ include file="../top.jsp" %>

	<%@ include file="./admin_side.jsp"%> 

<!--------------->
<!--  �۾� ����  -->
<!--------------->
	
        <div id="manager">  
        
            <h1 class="title">��ǰ �󼼺���</h1>

            <form name="frm" id="goods_form" method="post" action="goods_Proc.jsp?flag=update"
                enctype="multipart/form-data" >
                <!-- enctype="multipart/form-data" �϶����� post������� ������. 
            ��ſ� action�� ���ؼ� action="productProc.jsp?flag=insert" �� ������Ѵ�.-->        
        
        		<!--  input hidden ���� flag �� �ѱ�� flag=update ���� ���� 
        		<input type="hidden" value="update" name="flag">	-->
        
        
        
                <h3 class="inner_title">�⺻����</h3>
                
                <input id="chgread1" class="btn" type="button" value="����">
                
                            <table class="mgr_table verHead">
        
                                <tr>
                                    <th>��ǰ�ڵ�</th>
                                    <td><input name="p_code" value="<%=bean.getP_code()%>" readonly></td>                                                         	
                                    <th>��ǰ��</th>
                                    <td><input class="1 readChange" id="p_name" name="p_name" value="<%=bean.getP_name()%>" disabled></td>
                                </tr>
                                <tr>
                                    <th>��ǰ����</th>
                                    <td><input class="2 readChange" name="p_price" value="<%=bean.getP_price()%>" disabled>��</td>
                                    <th>�Ǹſ���</th>
                                    <td>
                                    	<%if(bean.getP_on_sale()==1){%>
                                    	<input  class="3 readChange radio" type="radio" name="p_on_sale" value="1"checked disabled>�Ǹ�
                                        <input  class="4 readChange radio" type="radio" name="p_on_sale" value="0" disabled>�����
                                        <%}else{%>
                                        <input  class="3 readChange radio" type="radio" name="p_on_sale" value="1" disabled>�Ǹ�
                                        <input  class="4 readChange radio" type="radio" name="p_on_sale" value="0" checked disabled>�����
                                        <%}%>
                                    </td>
                                </tr>						
                                <tr>
                                    <th>�����(1)</th>
                                    <td>
                                        <select name="rm_code1" id="rm_code1" class="5a readChange"disabled>
                                            <option value="" disabled selected>������</option>
                                           
                                            <%
                                            Vector<ProductBean>mer_list = ap_mgr.getMertaterialListAll();
                                            for(int i=0; i<mer_list.size();i++) {
                                                	ProductBean bean2 = mer_list.get(i);
                                                %>
                                                <option value="<%=bean2.getRm_code()%>"><%=bean2.getRm_name() %></option>
                                                <%}%>
                                        </select>
                                        <input class="5b readChange rm_pct" id="rm_percentage1" name="rm_percentage1" placeholder="������" disabled>%
                                    
                                    </td>
                                    
                                    <th>�����(2)</th>
                                    <td>
                                        <select name="rm_code2" id="rm_code2" class="6a readChange" disabled>
                                                <option value="" disabled selected>������</option>
                                                <%for(int i=0; i<mer_list.size();i++) {
                                                	ProductBean bean2 = mer_list.get(i);
                                                %>
                                                <option value="<%=bean2.getRm_code()%>"><%=bean2.getRm_name() %></option>
                                                <%}%>
                                        </select>
                                        <input class="6b readChange rm_pct" id="rm_percentage2" name="rm_percentage2" placeholder="������" disabled>%
                                    </td>
                                </tr>
                                <tr>
                                    <th>�����(3)</th>
                                    <td>
                                        <select name="rm_code3" id="rm_code3" class="7a readChange" disabled>
                                                <option value="" disabled selected>������</option>
                                                <%for(int i=0; i<mer_list.size();i++) {
                                                	ProductBean bean2 = mer_list.get(i);
                                                %>
                                                <option value="<%=bean2.getRm_code()%>"><%=bean2.getRm_name() %></option>
                                                <%}%>
                                        </select>
                                        <input class="7b readChange rm_pct" id="rm_percentage3" name="rm_percentage3" placeholder="������" disabled>%
                                    </td>                                
                                    <th>�����(4)</th>
                                    <td>
                                        <select class="8a readChange" id="rm_code4" name="rm_code4" disabled>
                                                <option value="" disabled selected>������</option>
                                                <%for(int i=0; i<mer_list.size();i++) {
                                                	ProductBean bean2 = mer_list.get(i);
                                                %>
                                                <option value="<%=bean2.getRm_code()%>"><%=bean2.getRm_name() %></option>
                                                <%}%>
                                        </select>
                                        <input class="8b readChange rm_pct" id="rm_percentage4" name="rm_percentage4" placeholder="������" disabled>%
                                    </td>
                                </tr>
                            </table>
        
                <h3 class="inner_title">��������</h3>
                            <table class="mgr_table verHead" id="pht_table">
                                <tr>
                                    <th>�����̹���</th>
                                    <td>
                                    <div class="filebox">				
                                    <span id="main_img_name"><%=bean.getP_main_pht_name()%></span>						                                    
										 <label for="main_img_btn">���ε�</label>
                                    	<input class="9 readChange" type="file" name="upFile1" id="main_img_btn" disabled></td>
                                	</div>
                                    </td>
                                    </tr>
                                <tr>
                                    <th>�����̹���</th>                                                                    
                                    <td> 
                                    <div class="filebox">
                                    <span id="ex_img_name"><%=bean.getP_detail_pht_name()%></span>
										  <label for="ex_img_btn">���ε�</label>
                                    	 <input class="10 readChange" type="file" name="upFile2" id="ex_img_btn" disabled></td>
                              		</div>
                                    </td>
                                     </tr>
                                <tr>
                                    <th>���̹���</th>
                                    <td> 
                                    <div class="filebox">										                                    
                                    <span id="detail_img_name"><%=bean.getP_info_pht_name()%></span>
										  <label for="detail_img_btn">���ε�</label>
                                    	<input class="11 readChange" type="file" name="upFile3" id="detail_img_btn" disabled></td>
                                    </div>
                                    </td>
                                </tr>
                            </table>
                            

            <div class="submit_wrapper">
            
                <input class="btn" type="button" value="�������" onclick="location.href='goods_master.jsp'">
                <input class="btn readChange" type="button" onclick="submitCheck()" value="�����Ϸ�" disabled>                
                <input class="btn" type="reset" value="�ٽþ���">
                <input class="btn" id="delete" type="button" value="�����ϱ�" onclick="location.href='javascript:confirmDel(<%=bean.getP_code()%>)'">
                <input type="hidden"  value="<%=bean.getP_code()%>" name="pcode">
                
            </div>
            </form>        
        </div>		
        
        <script>
        const rm_percentage1 = document.getElementById('rm_percentage1');
		const rm_percentage2 = document.getElementById('rm_percentage2');
		const rm_percentage3 = document.getElementById('rm_percentage3');
		const rm_percentage4 = document.getElementById('rm_percentage4');
		const rm_code1 = document.getElementById('rm_code1');
		const rm_code2 = document.getElementById('rm_code2');
		const rm_code3 = document.getElementById('rm_code3');
		const rm_code4 = document.getElementById('rm_code4');
		const p_name = document.getElementById('p_name');
		const goods_form = document.getElementById('goods_form');

		<%System.out.println(rmSize);%>;
		if(<%=rmSize%>==1)
		{
			rm_code1.selectedIndex = <%=rm_vlist.get(0).getRm_code()%>;
			rm_percentage1.value = <%=rm_vlist.get(0).getRm_percentage()%>;
		}
		else if(<%=rmSize%>==2)
		{
			rm_code1.selectedIndex = <%=rm_vlist.get(0).getRm_code()%>;
			rm_code2.selectedIndex = <%=rm_vlist.get(1).getRm_code()%>;
			rm_percentage1.value = <%=rm_vlist.get(0).getRm_percentage()%>;
			rm_percentage2.value = <%=rm_vlist.get(1).getRm_percentage()%>;
		}
		else if(<%=rmSize%>==3)
		{
			rm_code1.selectedIndex = <%=rm_vlist.get(0).getRm_code()%>;
			rm_code2.selectedIndex = <%=rm_vlist.get(1).getRm_code()%>;
			rm_code3.selectedIndex = <%=rm_vlist.get(2).getRm_code()%>;
			rm_percentage1.value = <%=rm_vlist.get(0).getRm_percentage()%>;
			rm_percentage2.value = <%=rm_vlist.get(1).getRm_percentage()%>;
			rm_percentage3.value = <%=rm_vlist.get(2).getRm_percentage()%>;
		}
		else if(<%=rmSize%>==4)
		{
			rm_code1.selectedIndex = <%=rm_vlist.get(0).getRm_code()%>;
			rm_code2.selectedIndex = <%=rm_vlist.get(1).getRm_code()%>;
			rm_code3.selectedIndex = <%=rm_vlist.get(2).getRm_code()%>;
			rm_code4.selectedIndex = <%=rm_vlist.get(3).getRm_code()%>;
			rm_percentage1.value = <%=rm_vlist.get(0).getRm_percentage()%>;
			rm_percentage2.value = <%=rm_vlist.get(1).getRm_percentage()%>;
			rm_percentage3.value = <%=rm_vlist.get(2).getRm_percentage()%>;
			rm_percentage4.value = <%=rm_vlist.get(3).getRm_percentage()%>;
		}
		
		function submitCheck(){
			goods_form.action='goods_Proc.jsp?flag=update&rm_percentage1='+rm_percentage1.value+
			'&rm_percentage2='+rm_percentage2.value + '&rm_percentage3='+rm_percentage3.value +
			'&rm_percentage4='+rm_percentage4.value + '&rm_code1='+rm_code1.value + 
			'&rm_code2='+rm_code2.value + '&rm_code3='+rm_code3.value + 
			'&rm_code4='+rm_code4.value + '&p_name='+p_name.value;
			goods_form.submit();
		}
		
        </script>
<!----------------->
<!--  �۾� ���� �� -->
<!----------------->
	
</div> <!--  #btn_manager_wrapper (��ư�޴� + manager) : admin_side.jsp ���� ����-->
</div> <!-- #main (��ܿ�� + ��ư + manager) : admin_side.jsp ���� ����-->
	<%@ include file="../bottom.jsp" %>
</body>

<script>
	window.onload = function(){
		function btnInit(){
			const button = document.querySelector('#chgread1');
			let flag = 0;
			button.addEventListener('click', function(){
				const reads = document.querySelectorAll('.readChange');
				if(flag===0){
					console.log("DDD");
					reads.forEach(function(items){
						items.disabled = false;
					});
					flag = 1;
					button.value = '����';
					console.log(button);
				}else{
					reads.forEach(function(items){
						items.disabled = true;
					});
					flag = 0;
					console.log("FFF");
					button.value = '����';
					console.log(button);
				}
			});
			
			document.getElementById('main_img_btn').addEventListener('change',function(){
			    var fileValue = $("#main_img_btn").val().split("\\");
			    var fileName = fileValue[fileValue.length-1]; // ���ϸ�
				document.getElementById('main_img_name').innerHTML = fileName;
			});
			document.getElementById('ex_img_btn').addEventListener('change',function(){
			    var fileValue = $("#ex_img_btn").val().split("\\");
			    var fileName = fileValue[fileValue.length-1]; // ���ϸ�
				document.getElementById('ex_img_name').innerHTML = fileName;
			});
			document.getElementById('detail_img_btn').addEventListener('change',function(){
			    var fileValue = $("#detail_img_btn").val().split("\\");
			    var fileName = fileValue[fileValue.length-1]; // ���ϸ�
				document.getElementById('detail_img_name').innerHTML = fileName;
			});
				
				
		}
		btnInit();
	}
	
	function confirmDel(p_code){
		if(confirm("�����Ͻðڽ��ϱ�?")==true){
			location.href="goods_Proc.jsp?flag=delete&pcode="+p_code;
		}else{
			return;
		}
	}
</script>
</html>