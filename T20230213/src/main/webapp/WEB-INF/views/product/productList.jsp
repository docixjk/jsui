<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!-- 보낸 객체명을 적어주고 DB 테이블 컬럼명을 적어주어서 받아옴 -->
<section class="py-5">
  <!-- section 은 상품카드를 담아야함-->
  <div class="container px-4 px-lg-5 mt-5">
    <div
      class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"
    >
      <!-- 내부에서 사용할 이름은 product 그리고 받아온 정보는 list <= productList.java에서 넘어온거 -->
      <c:forEach var="product" items="${list }">
        <!-- 카드 하나하나 임 ( DB에 있는 정보 수만큼 리스트 뽑아옴 ) -->
        <div class="col mb-5">
          <div class="card h-100">
            <!-- Sale badge-->
            <div
              class="badge bg-dark text-white position-absolute"
              style="top: 0.5rem; right: 0.5rem"
            >
              Sale
            </div>
            <!-- Product image-->
            <img
              class="card-img-top"
              src="images/${product.image }"
              alt="..."
            />
            <!-- Product details-->
            <div class="card-body p-4">
              <div class="text-center">
                <!-- Product name-->
                <h5 class="fw-bolder">
                  <!-- 상품의 이름을 눌렀을때 해당 상품의 상세정보가 나와야함 -->
                  <a href="productDetail.do?code=${product.productCode }"
                    >${product.productName }</a
                  >
                </h5>
                <!-- Product reviews-->
                <div
                  class="d-flex justify-content-center small text-warning mb-2"
                >
                  <div class="bi-star-fill"></div>
                  <div class="bi-star-fill"></div>
                  <div class="bi-star-fill"></div>
                  <div class="bi-star-fill"></div>
                  <div class="bi-star-fill"></div>
                </div>
                <!-- Product price-->
                <span class="text-muted text-decoration-line-through"
                  >${product.productPrice }원</span
                >
                ${product.salePrice }원
              </div>
            </div>
            <!-- Product actions-->
            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
              <div class="text-center">
                <a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</section>
