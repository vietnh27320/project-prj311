<%@page import="entity.Image"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="main.css">
        <link href="https://fonts.googleapis.com/css?family=Permanent+Marker|Playfair+Display&display=swap" rel="stylesheet">

        <%  List<Image> list = (List<Image>) request.getAttribute("listImage");
        %>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="container"> 
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="get-list-product">Trang chủ</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Chi tiết</li>
                </ol>
            </nav>
            <div class="row">
                <div class="col-sm-6">

                    <c:forEach items="${listImage}" var="img">
                        <div class="mySlides">
                            <img width="200px" src="image/${img.imgName}" style="width:60%; margin-left: 20%">
                        </div>
                    </c:forEach>

                    <a class="prev" onclick="plusSlides(-1)">❮</a>
                    <a class="next" onclick="plusSlides(1)">❯</a>

                    <div class="caption-container">
                        <p id="caption"></p>
                    </div>
                    <div class="row">
                        <%for (int i = 0; i < list.size(); i++) {

                        %>
                        <div class="column">
                            <img class="demo cursor" src="image/<%=list.get(i).getImgName()%>" style="width:100%" 
                                 onclick="currentSlide(<%=i + 1%>)" alt="">
                        </div>
                        <%}%>
                    </div>
                    <script>
                        var slideIndex = 1;
                        showSlides(slideIndex);

                        function plusSlides(n) {
                            showSlides(slideIndex += n);
                        }

                        function currentSlide(n) {
                            showSlides(slideIndex = n);
                        }

                        function showSlides(n) {
                            var i;
                            var slides = document.getElementsByClassName("mySlides");
                            var dots = document.getElementsByClassName("demo");
                            var captionText = document.getElementById("caption");
                            if (n > slides.length) {
                                slideIndex = 1
                            }
                            if (n < 1) {
                                slideIndex = slides.length
                            }
                            for (i = 0; i < slides.length; i++) {
                                slides[i].style.display = "none";
                            }
                            for (i = 0; i < dots.length; i++) {
                                dots[i].className = dots[i].className.replace(" active", "");
                            }
                            slides[slideIndex - 1].style.display = "block";
                            dots[slideIndex - 1].className += " active";
                            captionText.innerHTML = dots[slideIndex - 1].alt;
                        }
                    </script>
                </div>
                <div class="col-sm-6" >
                    <h1>${productDetail.name}</h1>
                    <p><b>Giá:</b>
                        <fmt:formatNumber value="${productDetail.price}" type="currency"/> </p>
                    <span class="mb-1">
                        <a href="addToCart?id=${productDetail.id}&page=2" class="btn btn-outline-danger "><i class="fas fa-cart-plus mr-2"></i>Thêm vào giỏ hàng</a>
                        <a href="/go-checkout"><button type="button" class="btn btn-outline-success">Mua ngay</button> </a>

                    </span>
                    <hr>


                    <p> <b>Mô tả:</b>
                        <span style=" font-style: italic;
                              line-height: 1.5rem;">
                            ${productDetail.description}   
                        </span> 
                    </p>
                    <hr>
                    <p class="local bd-t pt-3">
                        <b>Mua hàng trực tiếp tại cửa hàng</b>
                    <ul>
                        <li>Dom H, H513L, KTX Đại học FPT</li>
                        <li>KM29 Khu GD&ĐT, Khu CNC, ĐCT08, Hòa Lạc, Thạch Thất, Hà Nội 155300</li>
                    </ul>
                    </p>
                    <hr>
                    <p class="refund bd-t pt-3">
                        <b>Giao hàng và bảo hành</b>
                    <ul>
                        <li>Cam kết bảo hành ít nhất 1 năm</li>
                        <li>Phí vận chuyển ngoại thành/ngoại tỉnh: Xem xét</li>
                        <li>Thanh toán khi nhận hàng</li>
                    </ul>
                    </p>

                </div>

            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
