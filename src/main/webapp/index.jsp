<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body style="background-color: rgb(225,225,225)">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Library</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <c:choose>
                    <c:when test="${not empty sessionScope.auth}">
                        <li class="nav-item">
                            <form method="post" action="${pageContext.request.contextPath}/auth/logout">
                                <button class="nav-link active" role="button" aria-current="page" type="submit" style="background-color: transparent; border: none; cursor:pointer;">Logout</button>
                            </form>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <div class="dropdown">
                                <a class="nav-link active" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    LogIn
                                </a>

                                <div class="dropdown-menu bg-dark" aria-labelledby="dropdownMenuLink" style="border-radius: 20px">
                                    <div class=" dropdown-item d-flex justify-content-center bg-dark" style=" border-radius: 20px">
                                        <form method="post" action="${pageContext.request.contextPath}/auth/login">
                                            <c:if test="${not empty requestScope.message}">
                                                <div class="alert alert-danger" role="alert">
                                                        ${requestScope.message}
                                                </div>
                                            </c:if>
                                            <div class="mb-3">
                                                <label for="exampleInputEmail1" class="form-label text-light">Email address</label>
                                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
                                                <div id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="exampleInputPassword1" class="form-label text-light" >Password</label>
                                                <input type="password" class="form-control" id="exampleInputPassword1" name="password">
                                            </div>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="nav-item">
                            <div class="dropdown">
                                <a class="nav-link active" href="#" role="button" id="dropdownMenuLink1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    SigIn
                                </a>

                                <div class="dropdown-menu bg-dark" aria-labelledby="dropdownMenuLink1" style="border-radius: 20px">
                                    <div class=" dropdown-item d-flex justify-content-center bg-dark" style=" border-radius: 20px">
                                        <form method="post" action="${pageContext.request.contextPath}/auth/sigin">
                                            <c:if test="${not empty requestScope.message}">
                                                <div class="alert alert-danger" role="alert">
                                                        ${requestScope.message}
                                                </div>
                                            </c:if>
                                            <div class="mb-3">
                                                <label for="email" class="form-label text-light">Email address</label>
                                                <input type="email" class="form-control" id="email" name = "email" aria-describedby="emailHelp">
                                                <div id="emailHelp1" class="form-text  text-muted">We'll never share your email with anyone else.</div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="password" class="form-label text-light">Password</label>
                                                <input type="password" class="form-control" id="password" name="password">
                                            </div>
                                            <div class="mb-3">
                                                <label for="confirm_password" class="form-label text-light">Confirm password</label>
                                                <input type="password" class="form-control" id="confirm_password" name="confirm_password">
                                            </div>
                                            <script>
                                                let password = document.getElementById("password")
                                                    , confirm_password = document.getElementById("confirm_password");

                                                function validatePassword(){
                                                    if(password.value !== confirm_password.value) {
                                                        confirm_password.setCustomValidity("Passwords Don't Match");
                                                    } else {
                                                        confirm_password.setCustomValidity('');
                                                    }
                                                }
                                                password.onchange = validatePassword;
                                                confirm_password.onkeyup = validatePassword;
                                            </script>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
            <form class="form-inline">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script></body>
</html>