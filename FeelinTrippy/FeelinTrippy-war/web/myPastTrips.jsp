
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title>FeelinTrippy - My Past Trips</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- Favicon -->
    <link rel="shortcut icon" href="http://via.placeholder.com/30x30">

    <!-- Template CSS Files -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
	<link rel="stylesheet" type="text/css" href="css/magnific-popup.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/skins/yellow.css" />

    <!-- Template JS Files -->
    <script src="js/modernizr.js"></script>

</head>

<body class="double-diagonal dark portfolio-page">
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
                                <li class="active"><a href="myPastTrips.jsp"><i class="fa fa-envelope"></i> Past Trips</a></li>
                                <li><a href="profile.jsp"><i class="fa fa-user"></i> Profile</a></li>
                                <li><a href="mainPage.jsp"><i class="fa fa-user"></i> Log Out</a></li>
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
        <!-- Header Ends -->
        <!-- Banner Starts -->
        <section class="banner">
			<div class="content text-center">
				<div class="text-center top-text">
                    <!-- Main Heading Starts -->
					<div class="text-center top-text">
						<h1>saved trips</h1>
					</div> 
					<!-- Main Heading Ends -->
					<hr>
                    <!-- Breadcrumb Starts -->
					<ul class="breadcrumb">
						<li><a href="index-kenburns.html"> home</a></li>
						<li>saved trips</li>
					</ul>
					<!-- Breadcrumb Ends -->
                </div>
			</div>
		</section>
		<!-- Banner Ends -->
		<!-- Portfolio Section Starts -->
        <section class="portfolio">
            <!-- Container Starts -->
            <div class="container">
                <nav>
                    <ul class="simplefilter nav nav-pills">
                        <!-- Filter Wrapper Items Starts -->
                        <li class="active" data-filter="all"><i class="fa fa-reorder"></i> All Trips</li>
                        <li data-filter="1">Music & Entertainment</li>
                        <li data-filter="2">Food</li>
                        <li data-filter="3">Night Life</li>
                        <!-- Filter Wrapper Items Ends -->
                    </ul>
                </nav>

                <div>
                    <div class="filtr-container">
                        <!-- Project Starts -->
                        <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="1">
                            <div class="magnific-popup-gallery">
                                <!-- Thumbnail Starts -->
                                <figure class="thumbnail thumbnail__portfolio">
                                    <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                </figure>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <!-- Thumbnail Ends -->
                                <!-- Caption Starts -->
                                <div class="caption">
                                    <a class="title-link" href="gallery-project.html">
                                        <h3>Gallery Photos</h3>
                                    </a>
                                    <p>"Event descriptions"</p>
                                    <a class="custom-button" href="gallery-project.html">Read More</a>
                                </div>
                                <!-- Caption Ends -->
                            </div>
                        </div>
                        <!-- Project Ends -->
                        <!-- Project Starts -->
                        <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="2">
                            <div class="magnific-popup-gallery">
                                <!-- Thumbnail Starts -->
                                <figure class="thumbnail thumbnail__portfolio">
                                    <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                </figure>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <!-- Thumbnail Ends -->
                                <!-- Caption Starts -->
                                <div class="caption">
                                    <a class="title-link" href="gallery-project.html">
                                        <h3>Gallery Photos</h3>
                                    </a>
                                    <p>"Event descriptions"</p>
                                    <a class="custom-button" href="gallery-project.html">Read More</a>
                                </div>
                                <!-- Caption Ends -->
                            </div>
                        </div>
                        <!-- Project Ends -->
                        <!-- Project Starts -->
                        <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="3">
                            <div class="magnific-popup-gallery">
                                <!-- Thumbnail Starts -->
                                <figure class="thumbnail thumbnail__portfolio">
                                    <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                </figure>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <!-- Thumbnail Ends -->
                                <!-- Caption Starts -->
                                <div class="caption">
                                    <a class="title-link" href="gallery-project.html">
                                        <h3>Gallery Photos</h3>
                                    </a>
                                    <p>"Event descriptions"</p>
                                    <a class="custom-button" href="gallery-project.html">Read More</a>
                                </div>
                                <!-- Caption Ends -->
                            </div>
                        </div>
                        <!-- Project Ends -->
                        <!-- Project Starts -->
                        <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="4">
                            <div class="magnific-popup-gallery">
                                <!-- Thumbnail Starts -->
                                <figure class="thumbnail thumbnail__portfolio">
                                    <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                </figure>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <!-- Thumbnail Ends -->
                                <!-- Caption Starts -->
                                <div class="caption">
                                    <a class="title-link" href="gallery-project.html">
                                        <h3>Gallery Photos</h3>
                                    </a>
                                    <p>"Event descriptions"</p>
                                    <a class="custom-button" href="gallery-project.html">Read More</a>
                                </div>
                                <!-- Caption Ends -->
                            </div>
                        </div>
                        <!-- Project Ends -->
                        <!-- Project Starts -->
                        <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="1">
                            <div class="magnific-popup-gallery">
                                <!-- Thumbnail Starts -->
                                <figure class="thumbnail thumbnail__portfolio">
                                    <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                </figure>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <!-- Thumbnail Ends -->
                                <!-- Caption Starts -->
                                <div class="caption">
                                    <a class="title-link" href="gallery-project.html">
                                        <h3>Gallery Photos</h3>
                                    </a>
                                    <p>"Event descriptions"</p>
                                    <a class="custom-button" href="gallery-project.html">Read More</a>
                                </div>
                                <!-- Caption Ends -->
                            </div>
                        </div>
                        <!-- Project Ends -->
                        <!-- Project Starts -->
                        <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="1">
                            <div class="magnific-popup-gallery">
                                <!-- Thumbnail Starts -->
                                <figure class="thumbnail thumbnail__portfolio">
                                    <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                </figure>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <!-- Thumbnail Ends -->
                                <!-- Caption Starts -->
                                <div class="caption">
                                    <a class="title-link" href="gallery-project.html">
                                        <h3>Gallery Photos</h3>
                                    </a>
                                    <p>"Event descriptions"</p>
                                    <a class="custom-button" href="gallery-project.html">Read More</a>
                                </div>
                                <!-- Caption Ends -->
                            </div>
                        </div>
                        <!-- Project Ends -->
                        <!-- Project Starts -->
                      <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="1">
                            <div class="magnific-popup-gallery">
                                <!-- Thumbnail Starts -->
                                <figure class="thumbnail thumbnail__portfolio">
                                    <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                </figure>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <!-- Thumbnail Ends -->
                                <!-- Caption Starts -->
                                <div class="caption">
                                    <a class="title-link" href="gallery-project.html">
                                        <h3>Gallery Photos</h3>
                                    </a>
                                    <p>"Event descriptions"</p>
                                    <a class="custom-button" href="gallery-project.html">Read More</a>
                                </div>
                                <!-- Caption Ends -->
                            </div>
                        </div>
                        <!-- Project Ends -->
                        <!-- Project Starts -->
                        <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="1">
                            <div class="magnific-popup-gallery">
                                <!-- Thumbnail Starts -->
                                <figure class="thumbnail thumbnail__portfolio">
                                    <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                </figure>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <!-- Thumbnail Ends -->
                                <!-- Caption Starts -->
                                <div class="caption">
                                    <a class="title-link" href="gallery-project.html">
                                        <h3>Gallery Photos</h3>
                                    </a>
                                    <p>"Event descriptions"</p>
                                    <a class="custom-button" href="gallery-project.html">Read More</a>
                                </div>
                                <!-- Caption Ends -->
                            </div>
                        </div>
                        <!-- Project Ends -->
                        <!-- Project Starts -->
                        <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="1">
                            <div class="magnific-popup-gallery">
                                <!-- Thumbnail Starts -->
                                <figure class="thumbnail thumbnail__portfolio">
                                    <a class="image-wrap" href="http://via.placeholder.com/700x470" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img class="img-responsive" src="http://via.placeholder.com/700x470" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                </figure>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <a href="http://via.placeholder.com/700x470" title="Gallery project" data-gal="magnific-pop-up[gallery]" style="display:none;"></a>
                                <!-- Thumbnail Ends -->
                                <!-- Caption Starts -->
                                <div class="caption">
                                    <a class="title-link" href="gallery-project.html">
                                        <h3>Gallery Photos</h3>
                                    </a>
                                    <p>"Event descriptions"</p>
                                    <a class="custom-button" href="gallery-project.html">Read More</a>
                                </div>
                                <!-- Caption Ends -->
                            </div>
                        </div>
                        <!-- Project Ends -->
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
	<script src="js/plugins/jquery.filterizr.js"></script>
	<script src="js/plugins/jquery.magnific-popup.min.js"></script>

    <!-- Main JS Initialization File -->
    <script src="js/custom.js"></script>

</body>

</html>