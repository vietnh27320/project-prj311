
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN"/>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Quản Lí</title>
        <script src="https://kit.fontawesome.com/150b05f9e5.js" crossorigin="anonymous"></script>
        <link href="cus.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="datatable/lib/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="datatble/css/style.css">

    </head>

    <body>

        <nav class="navbar navbar-expand-md navbar-light  sticky-top">

            <div class="container-fluid ">
                <div style="margin-left:5%">
                    <a class="navbar-branch" href="/get-list-product">
                        <img src="imageHtml/logo.png" height="50" >
                    </a>  
                </div>

                <button class="navbar-toggler" type="button" data-toggle="collapse" 
                        data-target="#navbarResponsive">
                    <span class="navbar-toggler-icon"></span>
                </button>

            </div>

        </nav>

        <div class="container-fluid">

            <div style="margin-top: 5%" class="container-fluid row">
                <div class="col-3" >
                    <div class="text-center">
                        DANH MỤC SẢN PHẨM
                        <hr>
                    </div>
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                        <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab" aria-controls="v-pills-home" aria-selected="true">Thông tin tài khoản</a>
                        <!--                        <a style="margin-left: 5%" href="getOrder">Đơn Hàng</a>-->
                    </div>
                </div>
                <div class="col-9">
                    <div class="tab-content" id="v-pills-tabContent">
                        <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
                            <div>
                                <p style="text-align: center" > THÔNG TIN TÀI KHOẢN</p>
                                <hr>

                                <div style="border: 1px gray dashed; border-radius: 0.5rem;  padding: 1%">   
                                    <p> Email: ${sessionScope.cusID.email}</p>     
                                    <p> Mật Khẩu: ${sessionScope.cusID.password}</p>    
                                    <p>Trạng Thái: ${sessionScope.cusID.getDisplayStatus() }</p> 
                                </div> 
                            </div>
                            <div>
                                <p style="margin-top: 2%"> THÔNG TIN CÁ NHÂN</p>
                                <hr>
                                <div style="border: 1px gray dashed;  border-radius: 0.5rem;padding: 1% ">    
                                    <p> Tên Khách Hàng: ${accountDetail.name} </p>  
                                    <p> SĐT: ${accountDetail.mobile}</p>     
                                    <p> Giới tính: ${accountDetail.getDisplayGender()}</p>    
                                    <p> Địa chỉ: ${accountDetail.address}</p> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script type="text/javascript" src="datatable/js/jquery-3.3.1.min.js"></script>
        <script src="datatable/js/custom.js" type="text/javascript"></script>
        <script type="text/javascript" src="datatable/lib/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="datatable/js/script.js"></script>
    </body>

</html>