
<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ Hàng</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="main.css">
        <link href="https://fonts.googleapis.com/css?family=Permanent+Marker|Playfair+Display&display=swap" rel="stylesheet">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">STT </th>
                                    <th scope="col"></th>
                                    <th scope="col"> Sản Phẩm</th>
                                    <th scope="col"> Giá</th>
                                    <th scope="col" >Số lượng</th>
                                    <th scope="col" >Tổng Tiền</th>
                                    <th></th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:set scope="page" var="totalPrice" value="0" />
                                <c:forEach items="${sessionScope.listCart}" var="cart" varStatus="track">
                                    <tr>
                                        <td>${track.count}</td>
                                        <td><img style="width: 80px" src="image/${cart.productImage}"></td>
                                        <td>${cart.productName}</td>
                                        <td> <fmt:formatNumber value="${cart.productPrice}" type="currency"/></td>                                       <td>${cart.quantity}</td>
                                        <td><fmt:formatNumber value="${cart.totalPrice} " type="currency"/></td>
                                        <td> <a href="remove-cart?productId=${cart.productId}" ><i class="fas fa-trash-alt"></i></a></td>
                                    </tr>
                                <h1 style="display: none">${totalPrice = totalPrice+ cart.totalPrice}</h1>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>



            </div>
            <div style="float: right">
                <span ><a href="get-list-product"> <button type="button" class="btn btn-outline-success">Tiếp tục mua hàng</button>
                    </a></span> 

                <span ><a href="go-checkout"><button type="button" class="btn btn-outline-success">Đặt ngay</button>
                    </a></span>
                <span >
                    <a  href="remove-cart?productId=0" ><button type="button" class="btn btn-outline-danger">Xóa hết</button>
                    </a> 
                </span>
            </div>

            <div style="float: right;margin-right: 10%"> 
                <h3> Total money:<fmt:formatNumber value="${totalPrice}" type="currency"/></h3>
            </div>
        </div>


    </body>
</html>
