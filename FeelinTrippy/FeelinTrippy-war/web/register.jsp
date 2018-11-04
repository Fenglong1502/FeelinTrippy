<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Register, FeelinTrippy - Creative Trip Advisor</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Favicon -->
        <link rel="shortcut icon" href="http://via.placeholder.com/30x30">

        <!-- Template CSS Files -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/skins/yellow.css" />

        <!-- Template JS Files -->
        <script src="js/modernizr.js"></script>

    </head>

    <body class="double-diagonal dark auth-page" style="overflow: auto">
        <!-- Preloader Starts -->
        <div class="preloader" id="preloader">
            <div class="logopreloader">
<img src="./img/preloaders/walking-man.png" height="128" width="100" alt="logo">            </div>
            <div class="loader" id="loader"></div>
        </div>
        <!-- Preloader Ends -->
        <!-- Page Wrapper Starts -->
        <div class="wrapper">
            <div class="container-fluid user-auth">
                <div class="hidden-xs col-sm-4 col-md-4 col-lg-4">
                    <!-- Logo Starts -->
                    <a class="logo" href="filterTrip.jsp">
                        <img id="single-logo" class="img-responsive" src="./img/logo.png" alt="logo">
                    </a>
                    <!-- Logo Ends -->
                    <!-- Slider Starts -->
                    <div id="carousel-testimonials" class="carousel slide carousel-fade" data-ride="carousel">
                        <!-- Indicators Starts -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-testimonials" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-testimonials" data-slide-to="1"></li>
                            <li data-target="#carousel-testimonials" data-slide-to="2"></li>
                        </ol>
                        <!-- Indicators Ends -->
                        <!-- Carousel Inner Starts -->
                        <div class="carousel-inner">
                            <!-- Carousel Item Starts -->
                            <div class="item active item-1">
                                <div>
                                    <blockquote>
                                        <p>FeelinTrippy's Team Was Great To Work With And Interpreted Our Needs Perfectly.</p>
                                        <footer><span>Lucy Smith</span>, Singapore</footer>
                                    </blockquote>
                                </div>
                            </div>
                            <!-- Carousel Item Ends -->
                            <!-- Carousel Item Starts -->
                            <div class="item item-2">
                                <div>
                                    <blockquote>
                                        <p>The Team Is Endlessly Helpful, Flexible And Always Quick To Respond, Thanks FeelinTrippy!</p>
                                        <footer><span>Rawia Chniti</span>, Singapore</footer>
                                    </blockquote>
                                </div>
                            </div>
                            <!-- Carousel Item Ends -->
                            <!-- Carousel Item Starts -->
                            <div class="item item-3">
                                <div>
                                    <blockquote>
                                        <p>We Are So Appreciative Of Their Creative Efforts, And Love All The Events!, Millions Of Thanks FeelinTrippy!</p>
                                        <footer><span>Mario Verratti</span>, Singapore</footer>
                                    </blockquote>
                                </div>
                            </div>
                            <!-- Carousel Item Ends -->
                        </div>
                        <!-- Carousel Inner Ends -->
                    </div>
                    <!-- Slider Ends -->
                </div>
                <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
                    <!-- Logo Starts -->
                    <a class="visible-xs" href="index-kenburns.html">
                        <img class="img-responsive mobile-logo" src="img/FeelinTrippy.png" alt="logo">
                    </a>
                    <!-- Logo Ends -->
                    <div class="form-container" >
                        <div>
                            <!-- Main Heading Starts -->
                            <div class="text-center top-text">
                                <h1><span>get</span> started</h1>
                            
                            </div>
                            <!-- Main Heading Ends -->
                            <!-- Form Starts -->
                            <form class="custom-form" method="POST" action="RegisterServlet" >
                                <!-- Input Field Starts -->
                                <div class="form-group">
                                    <input class="form-control" name="username" id="username" placeholder="USERNAME" type="text" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" name="fName" id="fName" placeholder="FIRST NAME" type="text" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" name="lName" id="lName" placeholder="LAST NAME" type="text" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" name="mobileNumber" id="name" placeholder="MOBILE NUMBER" type="text" required>
                                </div>
                                <div class="form-group">
                                    <input type="radio" name="gender" value="2"> Male
                                    <input type="radio" name="gender" value="1" style="margin-left: 20px"> Female
                                </div>
                                <div class="form-group">
                                    <input class="form-control" name="email" id="email" placeholder="EMAIL" type="email" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" name="password" id="password" placeholder="PASSWORD" type="password" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" name="confirmPassword" id="confirmPassword" placeholder="CONFIRM PASSWORD" type="password" required>
                                </div>
                                <!-- Input Field Ends -->
                                <!-- Submit Form Button Starts -->
                                <div class="form-group">
                                    <button class="custom-button" type="submit">create account</button>
                                    <p class="text-center">already have an account ? <a href="login.jsp">Login</a>
                                </div>
                                <!-- Submit Form Button Ends -->
                            </form>
                            <!-- Form Ends -->
                        </div>
                    </div>
                    <!-- Copyright Text Starts -->
                    <f>
                  
                    <!-- Copyright Text Ends -->
                </div>
            </div>
        </div>
        <!-- Wrapper Ends -->

        <!-- Template JS Files -->
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/plugins/jquery.easing.1.3.js"></script>
        <script src="js/plugins/bootstrap.min.js"></script>

        <!-- Main JS Initialization File -->
        <script src="js/custom.js"></script>
    </body>

</html>