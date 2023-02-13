package com.yedam.product.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceImpl;

public class ProductDetail implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// productDetail.jsp 에서 파라미터를 받아올 이름을 적음
		String code = req.getParameter("code");
		
		ProductService service = new ProductServiceImpl();

		// code로 찾아온 정보를 vo 라는 객체에 담아줌
		req.setAttribute("vo", service.getProduct(code));
		req.setAttribute("list",service.relateList());
		
		
		return "product/product.tiles";
		
	}

}
