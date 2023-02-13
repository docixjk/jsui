<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<div class="slider">

  <div><img width="600px" src="images/양파쿵야1.jpg"></div>
  <div><img width="600px" src="images/양파쿵야2.jpg"></div>
  <div><img width="600px" src="images/양파쿵야3.jpg"></div>
  
</div>

<script>
$('.slider').bxSlider({
  auto: true,
  autoControls: true,
  stopAutoOnClick: true,
  pager: true,
  slideWidth: 600
});

</script>