package com.yedam.common;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.yedam.product.command.*;

public class FrontController extends HttpServlet {

	private Map<String, Command> map;
	private String charset;

	public FrontController() {
		map = new HashMap<String, Command>();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		charset = config.getInitParameter("charset");

		map.put("/productList.do", new ProductList()); // 상품 전체 목록
		map.put("/productDetail.do",new ProductDetail()); // 상품 상세 조회
		map.put("/productrelated.do", new ProductRelated()); // 상품 인기 목록

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);

		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String page = uri.substring(context.length());
		System.out.println(page);

		Command command = map.get(page);
		String viewPage = command.exec(req, resp);
		// notice/noticeList.tiles

		// .tiles는 새로고침 x , .do는 새로고침 o ??
		if (viewPage.endsWith(".tiles")) {
			// RequestDispatcher : url은 그대로고 내용만 바뀜
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);

		} else if (viewPage.endsWith(".do")) {
			// .do는 링크로만 많이 둠 ?
			// sendRedirect : service 처음부터 끝까지 실행한다?
			resp.sendRedirect(viewPage);

		} else if (viewPage.endsWith(".json")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().print(viewPage.substring(0, viewPage.length() - 5));
		}

	}
}
