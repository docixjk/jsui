package com.yedam.product.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceMybatis;

public class ProductList implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ProductService service = new ProductServiceMybatis();
		req.setAttribute("list", service.productList());

		return "product/productList.tiles"; // 프론트콘트롤러에서 쓸 수 있게 붙여주자
	}

}
