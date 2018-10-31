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

    <body class="double-diagonal dark auth-page">

        <!-- Page Wrapper Starts -->
        <div class="wrapper">
            <header class="header">
                <div class="header-inner">
                    <!-- Navbar Starts -->
                    <nav class="navbar">
                        <!-- Logo Starts -->
                        <div class="logo">
                            <a data-toggle="collapse" data-target=".navbar-collapse.show" class="navbar-brand" href="index-kenburns.html">
                                <!-- Logo White Starts -->
                                <img id="logo-light" class="logo-light" src="http://via.placeholder.com/159x28" alt="logo-light" />
                                <!-- Logo White Ends -->
                                <!-- Logo Black Starts -->
                                <img id="logo-dark" class="logo-dark" src="http://via.placeholder.com/159x28" alt="logo-dark" />
                                <!-- Logo Black Ends -->
                            </a>
                        </div>
                        <!-- Logo Ends -->
                        <!-- Toggle Icon for Mobile Starts -->
                        <button class="navbar-toggle navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span id="icon-toggler">
                                <span></span>
                                <span></span>
                                <span></span>
                                <span></span>
                            </span>
                        </button>
                        <!-- Toggle Icon for Mobile Ends -->
                        <div id="navbarSupportedContent" class="collapse navbar-collapse navbar-responsive-collapse">
                            <!-- Main Menu Starts -->
                            <ul class="nav navbar-nav" id="main-navigation">
                                <li><a href="filterTrip.jsp"><i class="fa fa-home"></i> Home</a></li>
                                <li><a href="mySavedTrips.jsp"><i class="fa fa-user"></i> Saved Trips</a></li>
                                <li><a href="myPastTrips.jsp"><i class="fa fa-user"></i> Past Trips</a></li>
                                <li><a href="profile.jsp"><i class="fa fa-user"></i> Profile</a></li>		
                                <li><a href="mainPage.jsp"><i class="fa fa-envelope"></i> Logout</a></li>
                            </ul>
                            <!-- Main Menu Ends -->
                        </div>
                        <!-- Search Input Starts -->

                        <!-- Search Input Ends -->
                        <!-- Navigation Menu Ends -->
                    </nav>
                    <!-- Navbar Ends -->
                </div>
            </header>  
            <div class="container-fluid user-auth">
                <div>

                    <!-- Slider Starts -->

                    <!-- Slider Ends -->
                </div>
                <div>
                    <!-- Logo Starts -->
                    <a class="visible-xs" href="mainPage.jsp">
                        <img class="img-responsive mobile-logo" src="img/FeelinTrippy.png" alt="logo">
                    </a>
                    <!-- Logo Ends -->
                    <div class="form-container">
                        <div>
                            <!-- Main Heading Starts -->
                            <div class="text-center top-text">
                                <h1><span>Update</span> Profile</h1>
                                <p></p>
                            </div>
                            <!-- Main Heading Ends -->
                            <!-- Form Starts -->
                            <form class="custom-form">
                                <!-- Input Field Starts -->
                                <div class="form-group">
                                    <input class="form-control" name="firstname" id="firstname" placeholder="FIRST NAME" type="text" required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" name="lastname" id="lastname" placeholder="LAST NAME" type="text" required>
                                </div>
                                <!-- Input Field Ends -->
                                <!-- Input Field Starts -->
                                <div class="form-group">
                                    <input class="form-control" name="email" id="email" placeholder="EMAIL" type="email" required>
                                </div>
                                <!-- Input Field Ends -->
                                <!-- Input Field Starts -->
                                <div class="form-group">
                                    <input class="form-control" name="password" id="password" placeholder="PASSWORD" type="password" required>
                                </div>
                                    
                                <div class="form-group">
                                    <input class="form-control" name="mobilenumber" id="mobilenumber" placeholder="MOBILE NUMBER" type="text" required>
                                </div>
                                
                                <div class="form-group">
                                    <input class="form-control" name="gender" id="gender" placeholder="GENDER" type="text" required>
                                </div>
                                <!-- Input Field Ends -->
                                <!-- Submit Form Button Starts -->
                                <div class="form-group">
                                    <button class="custom-button" type="submit">update</button>

                                </div>
                                <!-- Submit Form Button Ends -->
                            </form>
                            <!-- Form Ends -->
                        </div>
                    </div>
                    <!-- Copyright Text Starts -->
                    <p class="text-center copyright-text">Copyright © 2018 FeelinTrippy All Rights Reserved</p>
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