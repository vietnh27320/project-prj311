<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="main.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

        <title>ĐĂNG NHẬP</title>
        <style type="text/css">
            .login-form {
                width: 340px;
                margin: 50px auto;
            }
            .login-form form {
                margin-bottom: 15px;
                background: #f7f7f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .login-form h2 {
                margin: 0 0 15px;
            }
            .form-control, .btn {
                min-height: 38px;
                border-radius: 2px;
            }
            .btn {        
                font-size: 15px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>

        <div class=" container" style="margin-top: 5%">
            <div style="width: 100%">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Đăng nhập</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Tạo tài khoản</a>
                    </li>
                </ul>  
            </div>

            <div class="tab-content" id="myTabContent">

                <div class="  tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <div style="float: right" data-delay="3000" class="toast" data-autohide="true">
                        <div class="toast-header">
                            <strong class="mr-auto text-primary">Thông báo!!!</strong>
                            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                        </div>
                        <div class="toast-body">
                            ${requestScope.message}                        
                        </div>
                    </div>
                    <div class="login-form">
                        <form action="login" method="post">

                            <h2 class="text-center">ĐĂNG NHẬP</h2>       
                            <div class="form-group">
                                <input type="text" class="form-control" name="email" placeholder="Tên tài khoản" required="required">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control"  name="password" placeholder="Mật khẩu" required="required">
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block">ĐĂNG NHẬP</button>
                            </div>

                        </form>
                    </div>




                </div>
                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">

                    <div class="login-form">
                        <form action="createAccount" method="get">

                            <h2 class="text-center">TẠO TÀI KHOẢN</h2>       
                            <div class="form-group">
                                <input type="email" class="form-control"  name="Nemail" placeholder="Email" required="required">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control"  name="Npassword" placeholder="Mật khẩu" required="required">
                            </div>


                            <div class="form-group">
                                <input type="text" class="form-control" name="cusName" placeholder="Tên người dùng" required="required">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="mobile" placeholder="Số điện thoại" required="required">
                            </div> 
                            <div class="form-group">
                                Gender 
                                <select name="gender"> 
                                    <option value="0">Nam</option>
                                    <option value="1">Nữ</option>
                                </select>
                                <br/>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="address" placeholder="Địa chỉ" required="required">
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block">TẠO</button>
                            </div>

                        </form>
                    </div> 
                </div>
            </div>


        </div>
        <script>
            <c:if test="${requestScope.message ne null}">
            $(document).ready(function () {
                $('.toast').toast('show');
            });
            </c:if>
        </script>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>