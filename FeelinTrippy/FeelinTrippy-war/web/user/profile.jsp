
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>FeelinTrippy - My Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Favicon -->
       <link rel="shortcut icon" href="img/logo.png">

        <!-- Template CSS Files -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="css/magnific-popup.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/skins/yellow.css" />

        <!-- Template JS Files -->
        <script src="js/modernizr.js"></script>

    </head>

    <body class="double-diagonal dark about-page">
           <% if (request.getSession().getAttribute("user") == null) { %>
                <script type="text/javascript">
                        alert('Please login to start trippy');
                        document.location.href = 'login.jsp';
                    </script>
            <% }%>
        <!-- Preloader Starts -->
        <div class="preloader" id="preloader">
            <div class="logopreloader">
                <img src="./img/preloaders/walking-man.png" height="128" width="100" alt="logo">            </div>
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
                            <a data-toggle="collapse" data-target=".navbar-collapse.show" class="navbar-brand" href="filterTrip.jsp">
                                <!-- Logo White Starts -->
                                <img id="logo-light" class="logo-light" src="img/logo.png" alt="logo-light" />
                                <!-- Logo White Ends -->
                                <!-- Logo Black Starts -->
                                <img id="logo-dark" class="logo-dark" src="img/logo.png" alt="logo-dark" />
                                <!-- Logo Black Ends -->
                            </a>
                        </div>                        <!-- Logo Ends -->
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
                                <li class="active"><a href="profile.jsp"><i class="fa fa-user"></i> Profile</a></li>
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

            <!-- About Section Starts -->
            <section class="about">
                <!-- Container Starts -->
                <div class="container">
                    <!-- About Content Starts -->
                    <div class="row about-content">
                        <div><br><br><br><br><br><br></div>
                        <div class="col-sm-12 col-md-6 col-lg-6 about-left-side">
                            <h3 class="title-about">Marcus Choo</h3>
                            <p>38 Years Old Male</p>
                            <hr>
                            <p>81726582<br>
                                marcuschoo1478@gmail.com<br> 
                                Points available to redeem: 18<br>
                                <a class="custom-button" href="pastRewards.jsp">View Redeemed rewards</a></p>
                            <a class="custom-button" href="rewards.jsp">Redeem rewards now!</a>
                            <a class="custom-button" href="editProfile.jsp">Edit Profile</a>
                        </div>
                        <!-- Image About Us Starts -->
                        <div class="col-md-6 col-lg-6 about-right-side">
                            <div>
                                <img class="img-responsive" src="http://via.placeholder.com/300x180" alt="">
                            </div>
                        </div>
                        <!-- Image About Us Ends -->
                    </div>
                    <!-- About Content Ends -->
                </div>
                <!-- Container Ends -->
            </section>


            <!-- Logos Section Ends -->
            <!-- Footer Section Starts -->
            <footer class="footer">
                <!-- Footer Top Area Starts -->
                <div class="container top-footer">

                    <!-- Footer Bottom Area Starts -->
                    <div class="bottom-footer">
                        <div class="row">
                            <div class="col-xs-12">
                                <!-- Copyright Text Starts -->
                                <p class="text-center">Copyright © 2018 FeelinTrippy All Rights Reserved</a></p>
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
        <script src="js/plugins/jquery.bxslider.min.js"></script>
        <script src="js/plugins/jquery.magnific-popup.min.js"></script>

        <!-- Main JS Initialization File -->
        <script src="js/custom.js"></script>
    </body>

</html>