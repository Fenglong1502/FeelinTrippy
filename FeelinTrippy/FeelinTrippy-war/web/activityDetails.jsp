<%-- 
    Document   : activityDetails
    Created on : 26 Oct, 2018, 1:13:54 AM
    Author     : Congx2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
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
							<li><a href="index-kenburns.html"><i class="fa fa-home"></i> Home</a></li>
							<li><a href="about.html"><i class="fa fa-user"></i> About Us</a></li>
							<li class="active dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-image"></i> portfolio <i class="fa fa-angle-down icon-angle"></i></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="portfolio-2-columns.html">Portfolio 2 Columns</a></li>
									<li><a href="portfolio-3-columns.html">Portfolio 3 Columns</a></li>
									<li><a href="portfolio-4-columns.html">Portfolio 4 Columns</a></li>
									<li class="active"><a href="image-project.html">Image Project</a></li>
									<li><a href="slider-project.html">Slider Project</a></li>
									<li><a href="gallery-project.html">Gallery Project</a></li>
									<li><a href="video-project.html">Video project</a></li>
									<li><a href="youtube-project.html">youtube Project</a></li>
									<li><a href="vimeo-project.html">Vimeo Project</a></li>
								</ul>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-edit"></i> Blog <i class="fa fa-angle-down icon-angle"></i></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="blog-right-sidebar.html">Right Sidebar</a></li>
									<li><a href="blog-left-sidebar.html">Left Sidebar</a></li>
									<li><a href="blog-grid-no-sidebar.html">Grid No Sidebar</a></li>
									<li><a href="blog-post.html">Single Post</a></li>
								</ul>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text-o"></i> pages <i class="fa fa-angle-down icon-angle"></i></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="register.html">Register page</a></li>
									<li><a href="login.html">Login page</a></li>
									<li><a href="pricing.html">Pricing</a></li>                                        
									<li><a href="shopping-cart.html">Shopping cart</a></li>
									<li><a href="shopping-checkout.html">shopping checkout</a></li>
									<li><a href="coming-soon.html">Coming Soon</a></li>
									<li><a href="404.html">404 Page</a></li>
									<li><a href="503.html">Server Error Page</a></li>
									<li><a href="faq.html">FAQ page</a></li>
									<li><a href="terms-of-services.html">Terms of Services</a></li>
								</ul>
							</li>
							<li><a href="contact.html"><i class="fa fa-envelope"></i> Contact</a></li>
							<!-- Cart Icon Starts -->
							<li class="cart hidden-xs hidden-sm"><a href="shopping-cart.html"><i class="fa fa-shopping-cart"></i></a></li>
							<!-- Cart Icon Starts -->
							<!-- Search Icon Starts -->
							<li class="search hidden-xs hidden-sm"><button id="search-button" class="fa fa-search"></button></li>
							<!-- Search Icon Ends -->
						</ul>
						<!-- Main Menu Ends -->
					</div>
					<!-- Search Input Starts -->
					<div class="site-search hidden-xs">
						<div class="search-container">
							<input id="search-input" type="text" placeholder="type your keyword and hit enter ...">
							<span class="close">×</span>
						</div>
					</div>
					<!-- Search Input Ends -->
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
                    <!-- Footer Widget Starts -->
                    <div class="col-xs-6 col-sm-4 col-md-2">
                        <h4>Amira</h4>
                        <div class="menu">
                            <ul>
                                <li><a href="index-kenburns.html">Home</a></li>
                                <li><a href="about.html">About</a></li>
                                <li><a href="pricing.html">pricing</a></li>
								<li><a href="portfolio-3-columns.html">portfolio</a></li>
                                <li><a href="blog-right-sidebar.html">Blog</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- Footer Widget Ends -->
                    <!-- Footer Widget Starts -->
                    <div class="col-xs-6 col-sm-4 col-md-2">
                        <h4>Support</h4>
                        <div class="menu">
                            <ul>
								<li><a href="contact.html">Contact</a></li>
                                <li><a href="faq.html">FAQ</a></li>
                                <li><a href="terms-of-services.html">Terms of Services</a></li>
                                <li><a href="register.html">Register</a></li>
                                <li><a href="login.html">Login</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- Footer Widget Ends -->
                    <!-- Footer Widget Starts -->
                    <div class="col-xs-6 col-sm-4 col-md-3">
                        <h4>Contact US</h4>
                        <div class="menu">
                            <ul>
                                <li><span><i class="fa fa-envelope-open"></i> contact@website.com</span></li>
                                <li><span><i class="fa fa-phone"></i> 00216 21 184 010</span></li>
                                <li><span><i class="fa fa-map-marker"></i> London, England</span></li>
                                <li><span><i class="fa fa-clock-o"></i> mon-sat 08am &#x21FE; 05pm</span></li>
                                <li><span><i class="fa fa-skype"></i> amira.skype</span></li>
                            </ul>
                        </div>
                    </div>
                    <!-- Footer Widget Ends -->
                    <!-- Footer Widget Starts -->
                    <div class="col-xs-6 col-sm-12 col-md-4">
                        <!-- Facts Starts -->
                        <div class="facts-footer">
                            <div>
                                <h5>77,991</h5>
                                <span>projects</span>
                            </div>
                            <div>
                                <h5>80,217</h5>
                                <span>hours work</span>
                            </div>
                            <div>
                                <h5>1,253</h5>
                                <span>offices</span>
                            </div>
                            <div>
                                <h5>17,361</h5>
                                <span>clients</span>
                            </div>
                        </div>
                        <!-- Facts Ends -->
                        <hr>
                        <!-- Social Media Links Starts -->
                        <div class="social-icons">
                            <ul class="social">
                                <li>
                                    <a class="twitter" href="#" title="twitter"></a>
                                </li>
                                <li>
                                    <a class="facebook" href="#" title="facebook"></a>
                                </li>
                                <li>
                                    <a class="google" href="#" title="google"></a>
                                </li>
                                <li>
                                    <a class="linkedin" href="#" title="linkedin"></a>
                                </li>
                                <li>
                                    <a class="youtube" href="#" title="youtube"></a>
                                </li>
                            </ul>
                        </div>
                        <!-- Social Media Links Ends -->
                    </div>
                    <!-- Footer Widget Ends -->
                </div>
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