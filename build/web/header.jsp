
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="main.css">
        <link rel="stylesheet" href="style.css">
        <script src="https://kit.fontawesome.com/150b05f9e5.js" crossorigin="anonymous"></script>
        <script>
            function myFunction() {
                document.getElementById("demo").submit();
            }
        </script>
    </head>
    <body>
        <div class="py-1 bg-primary">
            <div class="container">
                <div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
                    <div class="col-lg-12 d-block">
                        <div class="row d-flex">
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
                                <span class="text">+ 0084 378872222</span>
                            </div>
                            <div class="col-md pr-4 d-flex topper align-items-center">
                                <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
                                <span class="text"> vietnhhe140392@fpt.edu.vn</span>
                            </div>
                            <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                                <span class="text"> <i class="fab fa-phoenix-framework"></i> Tiệm Bánh Của VVV</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
            <div class="container-fluid ">
                <div style="margin-left:5%">
                    <a class="navbar-branch" href="/get-list-product">
                        <img src="image/20161022_121120_design_4.jpg" height="70" >
                    </a>  
                </div>

                <button class="navbar-toggler" type="button" data-toggle="collapse" 
                        data-target="#navbarResponsive">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive" style="margin-right: 12%">

                    <ul class="navbar-nav ml-auto"> 
                        <form id="demo" action="search" method="get" >
                            <div class="searchbar">
                                <input class="search_input" type="text" name="search" placeholder="Tìm kiếm...">
                                <a onclick="myFunction()" style="cursor: pointer" class="search_icon" ><i class="fas fa-search"></i></a>
                            </div>
                        </form>

                        <li class="nav-item active">
                            <a class="nav-link" href="/get-list-product">Trang Chủ <span class="sr-only">(current)</span></a>
                        <li class="nav-item">
                            <a class="nav-link  mr-2" href="cart.jsp">
                                <i class="fas fa-shopping-cart mr-1"></i>Giỏ hàng <sup>${sessionScope.listCart.size()}</sup>
                            </a>                        
                        </li>

                        <div class="nav"> 
                            <c:if test="${sessionScope.loginAccount eq null}">
                                <div style="padding-top: 6px">
                                    <a href="login.jsp"><i class="fas fa-sign-in-alt fa-fw mr-1"></i>Đăng nhập</a>
                                </div>            
                            </c:if>
                            <c:if test="${sessionScope.loginAccount ne null}">

                                <div class="dropdown">
                                    <button  class="btn btn-secondary1 dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-user-circle"></i>   ${sessionScope.loginAccount.email}
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenu2">

                                        <a href="getCustomerInfo?id=${sessionScope.loginAccount.id}"><button class="dropdown-item" type="button">Trang cá nhân</button></a>

                                        <a href="logout"><button class="dropdown-item" type="button"> Đăng Xuất</button></a>
                                    </div>
                                </div>
                            </c:if>

                        </div>
                    </ul> 
                </div>

            </div>

        </nav>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
