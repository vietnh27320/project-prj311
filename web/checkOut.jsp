
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
        <title>Check out</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="main.css">
        <link href="https://fonts.googleapis.com/css?family=Permanent+Marker|Playfair+Display&display=swap" rel="stylesheet">
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="container-fluid mb-4">
            <div  class="row">
                <div class="col-lg-7" style="border-right: 1px solid lightblue;">
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
                        <div style="float: left">
                            <span class="filter"><a href="get-list-product">Continute Shopping</a></span> 
                        </div>
                        <div style="float: right " > 
                            <h4> Total money:<fmt:formatNumber value="${totalPrice}" type="currency"/></h4>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5" style="margin-top: 2%">
                    <h3 style="text-align: center">Thông tin khách hàng</h3>
                    <form action="checkOut" method="post" class="checkout-info" style="margin-left: 11%;">
                        <table>

                            <tr>
                                <td>
                                    <input class="input" type="text" name="name" placeholder="Họ và tên"/>
                                </td>

                                <td>
                                    <input class="input" type="text" name="sdt"placeholder="Số điện thoại" />
                                </td> 
                            </tr>

                            <tr>
                                <td colspan="12">
                                    <input style="height: 30px;    width: 98%;   border-radius: 20px;  border: 1px solid #ccc;
                                           padding: 0 10px;" type="email" name="email" placeholder="Email" />
                                </td>
                            </tr>

                            <tr>
                                <td colspan="6">
                                    <textarea class="input-area" name="address" rows="3" placeholder="Địa chỉ"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="6">
                                    <textarea class="input-area" name="note" rows="3" placeholder="Ghi chú"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td colspan="6" style="text-align: right;">
                                    <input style="border: 1px solid #002752" type="submit" class="btn" value="Xác nhận" />
                                </td>
                            </tr>
                        </table>
                    </form>     
                </div>




            </div>
        </div>


    </body>
</html>
