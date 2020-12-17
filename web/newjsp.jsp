
<%@page import="util.HtmlHelper"%>
<%@page import="entity.Category"%>
<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>

<!doctype html>
<html lang="en">
    <head>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="main.css">
        <script src="https://kit.fontawesome.com/150b05f9e5.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/ionicons.min.css">
        <link rel="stylesheet" href="css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <title>Make My Home</title>
        <script>
            function myFunction() {
                document.getElementById("demo").submit();
            }
        </script>
        <%
            List<Category> category = (List<Category>) request.getAttribute("category");
        %>
    </head>
    <body>

        <%@include file="header.jsp" %>





        <div  class="container" >
            <div class="products_cat_heading"><div class="heading3"> <ul class="product-category">
                        <li>
                            <a href="filter?categoryId=0#test" class="active">All</a>
                        </li>

                        <%for (Category elem : category) {
                        %>

                        <li>
                            <a  href="filter?categoryId=<%=elem.getId()%>#test">
                                <%=elem.getName()%>
                            </a>
                        </li>
                        <%}%>

                    </ul>
                    <div style="float: right" class="dropdown">
                        <button  class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Sắp xếp theo giá   </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                            <a href="d#test"><button class="dropdown-item" type="button">  Từ cao đến thấp</button></a>
                            <a href="d1#test"><button class="dropdown-item" type="button">  Từ thấp đến cao</button></a>

                        </div>
                    </div>
                </div>

            </div>
            <div class="row ">
                <c:forEach items="${results}" var="product" varStatus="i">
                    <div class="col-md-6 col-lg-3 ftco-animate">
                        <div class="product">
                            <a href="GetProDetail?productId=${product.id}" class="img-prod"><img class="img-fluid"
                                                                                                 src="image/${listImagecoverMapping.get(i.count-1).imgName}" alt="Colorlib Template">
                                <span class="status">${product.getDisplayStatus()}</span>
                                <div class="overlay"></div>
                            </a>
                            <div class="text py-3 pb-4 px-3 text-center">
                                <h3><a href="#">${product.name}</a></h3>
                                <div class="d-flex">
                                    <div class="pricing">
                                        <p class="price"><span class="mr-2 price-dc"></span><span class="price-sale"> <fmt:formatNumber value="${product.price} " type="currency"/></span></p>
                                    </div>
                                </div>
                                <div class="bottom-area d-flex px-3">
                                    <div class="m-auto d-flex">
                                        <a href="GetProDetail?productId=${product.id}" class="add-to-cart d-flex justify-content-center align-items-center text-center">
                                            <i class="fa fa-search"></i>       </a>
                                        <a href="addToCart?id=${product.id}&page=1" class="buy-now d-flex justify-content-center align-items-center mx-1">
                                            <span><i class="fa fa-shopping-cart"></i></span>
                                        </a>
                                        <a href="#" class="heart d-flex justify-content-center align-items-center ">
                                            <span><i class="fas fa-grin-hearts"></i></span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>               
                </c:forEach>
                <div style=" float: right;position: fixed ;bottom: 10px;right: 50px">
                    <div class="toast" data-autohide="true" data-delay="2000">
                        <div class="toast-header">
                            <strong class="mr-auto text-primary">Thông báo!!!</strong>
                            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                        </div>
                        <div class="toast-body">
                            ${requestScope.message}                 
                        </div>
                    </div>
                </div>
            </div>
            <div style="float: right;" >
                ${requestScope.htmlHelper.pager(requestScope.pageIndex, requestScope.pageCount, 1)}

            </div>


            <section id="map">
                <div id="gmap"></div>
                <script>
                    function myMap() {
                        var myCenter = new google.maps.LatLng(23.026450, 104.971240);
                        var mapCanvas = document.getElementById("gmap");
                        var mapOptions = {
                            center: myCenter,
                            zoom: 18
                        };
                        var map = new google.maps.Map(mapCanvas, mapOptions);
                        var marker = new google.maps.Marker({
                            position: myCenter
                        });
                        marker.setMap(map);
                        var infowindow = new google.maps.InfoWindow({
                            content: ""
                        });
                        infowindow.open(map, marker);
                    }
                </script>
                <!--<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB2LlIj3sk2akFpnpNcXzX9_NS08Xda1sE&callback=myMap"></script>-->
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.4704284832214!2d105.52464460085196!3d21.01385494359341!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x313454b32ca5086d%3A0xa3c62e29d8ab37e4!2sFPT%20University!5e0!3m2!1sen!2s!4v1594027585241!5m2!1sen!2s" width="1200" height="750" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
            </section>
            <!--<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.4822058528202!2d105.52509225085196!3d21.013383293609504!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x313454b32ca5086d%3A0xa3c62e29d8ab37e4!2sFPT%20University!5e0!3m2!1sen!2s!4v1594027176865!5m2!1sen!2s" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>-->
        </div>
        <%--<%=HtmlHelper.pager(pageindex, pagecount, 2) %>--%>


        <script>
            <c:if test="${requestScope.message ne null}">
            $(document).ready(function () {
                $('.toast').toast('show');
            });
            </c:if>
        </script>
        <%@include file="footer.jsp" %>
        <!-- Footer -->
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery-migrate-3.0.1.min.js"></script>
        <script src="js/jquery.waypoints.min.js"></script>
        <script src="js/jquery.stellar.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/aos.js"></script>
        <script src="js/scrollax.min.js"></script>
        <script src="js/main.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>