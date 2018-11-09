<%-- 
    Document   : activity
    Created on : 25 Oct, 2018, 11:26:05 PM
    Author     : Congx2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>FeelinTrippy - Activities Recommendation</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Favicon -->
        <link rel="shortcut icon" href="img/logo.png">

        <!-- Template CSS Files -->
        <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="../css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="../css/magnific-popup.css" />
        <link rel="stylesheet" type="text/css" href="../css/style.css" />
        <link rel="stylesheet" type="text/css" href="../css/skins/yellow.css" />

        <!-- Template JS Files -->
        <script src="js/modernizr.js"></script>

    </head>

    <body class="double-diagonal dark portfolio-page">
           <% if (request.getSession(false).getAttribute("filterList") == null) { %>
                <script type="text/javascript">
                        alert('Please login to start trippy');
                        document.location.href = 'login.jsp';
                    </script>
            <% }%>
            
        <!-- Preloader Starts -->
        <div class="preloader" id="preloader">
            <div class="logopreloader">
                <img src="../img/preloaders/walking-man.png" alt="logo">
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
                            <a data-toggle="collapse" data-target=".navbar-collapse.show" class="navbar-brand" href="filterTrip.jsp">
                                <!-- Logo White Starts -->
                                <img id="logo-light" class="logo-light" src="../img/logo.png" alt="logo-light" />
                                <!-- Logo White Ends -->
                                <!-- Logo Black Starts -->
                                <img id="logo-dark" class="logo-dark" src="../img/logo.png" alt="logo-dark" />
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
      
            <!-- Portfolio Section Starts -->
            <section class="portfolio">
                <!-- Container Starts -->
                <div class="container">
                    <!-- Filter Wrapper Starts -->
                    <nav>
                        <ul class="simplefilter nav nav-pills">
                            <!-- Filter Wrapper Items Starts -->
                            <li class="active" data-filter="all"><i class="fa fa-reorder"></i> All Activities</li>
                            <li data-filter="1">category 1</li>
                            <li data-filter="2">category 2</li>
                            <li data-filter="3">category 3</li>
                            <!-- Filter Wrapper Items Ends -->
                        </ul>
                    </nav>
                    <!-- Filter Wrapper Ends -->
                    <div>
                        <div class="filtr-container">

                            <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="1">
                                <div class="magnific-popup-gallery">
                                    <!-- Thumbnail Starts -->
                                    <figure class="thumbnail thumbnail__portfolio">
                                        <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[image]" title="Image project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Image Project" /><span class="zoom-icon"></span></a>
                                    </figure>
                                    <!-- Thumbnail Ends -->
                                    <!-- Caption Starts -->
                                    <div class="caption">
                                        <a class="title-link" href="activityDetails.jsp">
                                            <h3>Activity 1</h3>
                                        </a>
                                        <p> details of activity 1</p>
                                        <a class="custom-button" href="activityDetails.jsp">Read More</a>
                                    </div>
                                    <!-- Caption Ends -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="2">
                                <div class="magnific-popup-gallery">
                                    <!-- Thumbnail Starts -->
                                    <figure class="thumbnail thumbnail__portfolio">
                                        <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[image]" title="Image project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Image Project" /><span class="zoom-icon"></span></a>
                                    </figure>
                                    <!-- Thumbnail Ends -->
                                    <!-- Caption Starts -->
                                    <div class="caption">
                                        <a class="title-link" href="activityDetails.jsp">
                                            <h3>activity 2</h3>
                                        </a>
                                        <p>details of activity 2</p>
                                        <a class="custom-button" href="activityDetails.jsp">Read More</a>
                                    </div>
                                    <!-- Caption Ends -->
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="3">
                                <div class="magnific-popup-gallery">
                                    <!-- Thumbnail Starts -->
                                    <figure class="thumbnail thumbnail__portfolio">
                                        <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[image]" title="Image project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Image Project" /><span class="zoom-icon"></span></a>
                                    </figure>
                                    <!-- Thumbnail Ends -->
                                    <!-- Caption Starts -->
                                    <div class="caption">
                                        <a class="title-link" href="activityDetails.jsp">
                                            <h3>Activity 3</h3>
                                        </a>
                                        <p>details of activity 3</p>
                                        <a class="custom-button" href="activityDetails.jsp">Read More</a>
                                    </div>
                                    <!-- Caption Ends -->
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- Container Ends -->
            </section>
            <!-- Portfolio Section Ends -->
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
        <script src="js/plugins/jquery.filterizr.js"></script>
        <script src="js/plugins/jquery.magnific-popup.min.js"></script>

        <!-- Main JS Initialization File -->
        <script src="js/custom.js"></script>

    </body>

</html>