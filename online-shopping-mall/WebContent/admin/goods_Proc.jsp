<!-- goods_Proc.jsp -->
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="product.UtilMgr"%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<jsp:useBean id="pMgr" class="admin.ProductMgr"/>

<%
		
		request.setCharacterEncoding("EUC-KR");
		String flag = request.getParameter("flag");
		System.out.println(flag);
		String msg = "������ �߻��Ͽ����ϴ�.";
		HttpServletRequest request2 = request;
		boolean result = false;
		
			
		if(flag.equals("insert")){
			result = pMgr.insertProduct(request);
			if(result){
				msg = "��ǰ�� ����ϼ̽��ϴ�.";
				int[] rm_pct = new int[4];
				int[] rm_code = new int[4];
				rm_pct[0] = Integer.parseInt(request.getParameter("rm_percentage1"));
				rm_pct[1] = Integer.parseInt(request.getParameter("rm_percentage2"));
				rm_pct[2] = Integer.parseInt(request.getParameter("rm_percentage3"));
				rm_pct[3] = Integer.parseInt(request.getParameter("rm_percentage4"));
				rm_code[0] =  Integer.parseInt(request.getParameter("rm_code1"));
				rm_code[1] =  Integer.parseInt(request.getParameter("rm_code2"));
				rm_code[2] =  Integer.parseInt(request.getParameter("rm_code3"));
				rm_code[3] =  Integer.parseInt(request.getParameter("rm_code4"));
				int p_code = pMgr.getProductCode(request.getParameter("p_name"));
				System.out.println("���ڵ峪��" + p_code);
				pMgr.insertRmData(p_code, rm_code, rm_pct);
			}
		}else if(flag.equals("update")){
			result = pMgr.updateProduct(request);
			if(result) msg ="��ǰ�� �����Ͽ����ϴ�.";
			int[] rm_pct = new int[4];
			int[] rm_code = new int[4];
			rm_pct[0] = Integer.parseInt(request.getParameter("rm_percentage1"));
			rm_pct[1] = Integer.parseInt(request.getParameter("rm_percentage2"));
			rm_pct[2] = Integer.parseInt(request.getParameter("rm_percentage3"));
			rm_pct[3] = Integer.parseInt(request.getParameter("rm_percentage4"));
			rm_code[0] =  Integer.parseInt(request.getParameter("rm_code1"));
			rm_code[1] =  Integer.parseInt(request.getParameter("rm_code2"));
			rm_code[2] =  Integer.parseInt(request.getParameter("rm_code3"));
			rm_code[3] =  Integer.parseInt(request.getParameter("rm_code4"));
			int p_code = pMgr.getProductCode(request.getParameter("p_name"));
			System.out.println("���ڵ峪��" + p_code);
			pMgr.updateRmData(p_code, rm_code, rm_pct);
			
		}else if(flag.equals("delete")){	
			int p_code = Integer.parseInt(request.getParameter("pcode")); 
			pMgr.deleteproduct1(p_code);
			msg = "��ǰ�� �����Ͽ����ϴ�.";			
		}else{}
		
%>
<script>
	alert("<%=msg%>");
	location.href = "goods_master.jsp";

</script>