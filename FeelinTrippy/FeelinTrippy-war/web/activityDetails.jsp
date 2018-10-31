<%-- 
    Document   : activityDetails
    Created on : 26 Oct, 2018, 1:13:54 AM
    Author     : Congx2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title>Image Project, Amira - Creative Multipurpose Template</title>
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

<body class="double-diagonal dark portfolio-project">
    <!-- Preloader Starts -->
    <div class="preloader" id="preloader">
        <div class="logopreloader">
            <img src="http://via.placeholder.com/159x28" alt="logo">
        </div>
        <div class="loader" id="loader"></div>
    </div>
    <!-- Preloader Ends -->
    <!-- Page Wrapper Starts -->
    <div class="wrapper">
        <!-- Header Starts -->
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
                            <li><a href="mySavedTrips.jsp"><i class="fa fa-envelope"></i> Saved Trips</a></li>
                            <li><a href="myPastTrips.jsp"><i class="fa fa-envelope"></i> Past Trips</a></li>
                            <li><a href="profile.jsp"><i class="fa fa-user"></i> Profile</a></li>
                            <li><a href="mainPage.jsp"><i class="fa fa-user"></i> Log Out</a></li>
                        </ul>
                        <!-- Main Menu Ends -->
                    </div>
                <!-- Navigation Menu Ends -->
                </nav>
                <!-- Navbar Ends -->
            </div>
        </header>
        <!-- Header Ends -->
      
        <!-- Project Starts -->
            <section class="project">
		<div class="container">
			<div class="row">
                            <!-- Project Main Content Starts -->
                            <div class="col-md-6">
                                <div class="project-main-content">
                                    <img class="img-responsive" src="http://via.placeholder.com/700x470" alt="image" />
                                </div>
                                <div class="project-navigation">
                                    <nav>
                                        <ul class="unstyled">
                                            <li><a href="activities.jsp"><i class="fa fa-angle-double-left"></i>Back</a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                            <!-- Project Main Content Ends -->
                            <!-- Project Details Starts -->
                            <div class="col-md-6">
                                <div class="project-details">
                                    <h3>Activity Name</h3>
                                        <ul class="unstyled">
                                            <li><i class="fa fa-user"></i><span class="detail-name"> Location </span>: <span class="detail-value">location</span></li>
                                            <li><i class="fa fa-calendar"></i><span class="detail-name"> Date Available </span>: <span class="detail-value">02/11/2017</span></li>
                                            <li><i class="fa fa-cogs"></i><span class="detail-name"> price </span>: <span class="detail-value">$$$</span></li>
                                        </ul>
                                        <hr>
                        <p>details of activity</p>

                        <a class="custom-button" href="#">Save Activity</a>
                        <a class="custom-button" href="#">Share Activity // pop up to take in email or QR code  </a>
                                            </div>
                                    </div>
                                    <!-- Project Details Ends -->
                            </div>
                    </div>
		</section>
		<!-- Project Ends -->
                <!-- Footer Section Starts -->
                <footer class="footer">
                    <!-- Footer Top Area Starts -->
                    <div class="container top-footer">
                        <div class="row">

                        <!-- Footer Bottom Area Starts -->
                        <div class="bottom-footer">
                            <div class="row">
                                <div class="col-xs-12">
                                    <!-- Copyright Text Starts -->
                                    <p class="text-center">Copyright © 2018 Amira All Rights Reserved | Created with Love by <a href="https://themeforest.net/user/celtano" target="_blank">celtano</a></p>
                                    <!-- Copyright Text Ends -->
                                </div>
                            </div>
                        </div>
                        <!-- Footer Bottom Area Ends -->
                    </div>
                    <!-- Footer Top Area Ends -->

                </footer>
                <!-- Footer Section Ends -->
                <!-- Back To Top Starts -->
                <div id="back-top-wrapper">
                    <p id="back-top">
                        <a href="#top"><span></span></a>
                    </p>
                </div>
                <!-- Back To Top Ends -->
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