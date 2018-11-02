<%-- 
    Document   : rewards
    Created on : 31 Oct, 2018, 3:18:12 AM
    Author     : cheesiangng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>FeelinTrippy - Rewards</title>
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
                <img src="./img/preloaders/walking-man.png" height="128" width="100" alt="logo">        </div>
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
                        </div>
                        <!-- Logo Ends -->
                        <!-- Toggle Icon for Mobile Starts -->
                        <button class="navbar-toggle navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span id="icon-toggler">

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
            <!-- Banner Starts -->
            <section class="banner">
                <div class="content text-center">
                    <div class="text-center top-text">
                        <img src="./img/backgrounds/rewards.png" alt="" data-bgposition="center center" data-bgfit="cover" data-bgrepeat="no-repeat" data-bgparallax="6" class="rev-slidebg" data-no-retina>                     
                    </div>
                </div>
            </section>
            <!-- Banner Ends -->
            <!-- Portfolio Section Starts -->
            <section class="portfolio">
                <!-- Container Starts -->
                <div class="container">                    
                    <div>
                        <div class="filtr-container">
                            <!-- Project Starts -->
                            <div class="col-xs-12 col-sm-4 col-md-4 filtr-item" data-category="1">
                                <div class="magnific-popup-gallery">
                                    <!-- Thumbnail Starts -->
                                    <figure class="thumbnail thumbnail__portfolio">
                                        <a class="image-wrap" href="../web/img/backgrounds/mcdonald.jpg" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img src="../web/img/backgrounds/mcdonald.jpg" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                    </figure>
                                    <!-- Thumbnail Ends -->
                                    <!-- Caption Starts -->
                                    <div class="caption">                                        
                                        <h3>McDonald $5 Voucher</h3>                                        
                                        <p>"500 points"</p>
                                        <button id="myBtn" class="custom-button">Redeem</button>
                                        <!-- The Modal -->
                                        <div id="myModal" class="modal">
                                            <p>QR Code</p>
                                            <span class="close">&times;</span>                                                                                            
                                        </div>
                                        <!-- End of Modal -->   
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
                                        <a class="image-wrap" href="../web/img/backgrounds/shaw.jpg" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img src="../web/img/backgrounds/shaw.jpg" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                    </figure>
                                    <!-- Thumbnail Ends -->
                                    <!-- Caption Starts -->
                                    <div class="caption">
                                        <h3>Shaw $10 Voucher</h3>
                                        <p>"1000 points"</p>
                                        <button id="myBtn2" class="custom-button">Redeem</button>
                                        <!-- The Modal -->
                                        <div id="myModal2" class="modal">
                                            <p>QR Code</p>
                                            <span class="close">&times;</span>                                                                                            
                                        </div>
                                        <!-- End of Modal -->  
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
                                        <a class="image-wrap" href="../web/img/backgrounds/safra.jpg" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img src="../web/img/backgrounds/safra.jpg" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                    </figure>
                                    <!-- Thumbnail Ends -->
                                    <!-- Caption Starts -->
                                    <div class="caption">
                                        <h3>Safra $10 Voucher</h3>                                    
                                        <p>"1000 points"</p>
                                        <button id="myBtn3" class="custom-button">Redeem</button>
                                        <!-- The Modal -->
                                        <div id="myModal" class="modal">
                                            <p>QR Code</p>
                                            <span class="close">&times;</span>                                                                                            
                                        </div>
                                        <!-- End of Modal -->  
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
                                        <a class="image-wrap" href="../web/img/backgrounds/bottle.jpg" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img src="../web/img/backgrounds/bottle.jpg" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                    </figure>
                                    <!-- Thumbnail Ends -->
                                    <!-- Caption Starts -->
                                    <div class="caption">
                                        <h3>Thermal Flask</h3>
                                        <p>"2000 points"</p>
                                        <button id="myBtn4" class="custom-button">Redeem</button>
                                        <!-- The Modal -->
                                        <div id="myModal" class="modal">
                                            <p>QR Code</p>
                                            <span class="close">&times;</span>                                                                                            
                                        </div>
                                        <!-- End of Modal -->  
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
                                        <a class="image-wrap" href="../web/img/backgrounds/pillow.jpg" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img src="../web/img/backgrounds/pillow.jpg" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                    </figure>
                                    <!-- Thumbnail Ends -->
                                    <!-- Caption Starts -->
                                    <div class="caption">
                                        <h3>Travel Pillow</h3>                                        
                                        <p>"2000 points"</p>
                                        <button id="myBtn5" class="custom-button">Redeem</button>
                                        <!-- The Modal -->
                                        <div id="myModal" class="modal">
                                            <p>QR Code</p>
                                            <span class="close">&times;</span>                                                                                            
                                        </div>
                                        <!-- End of Modal -->  
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
                                        <a class="image-wrap" href="../web/img/backgrounds/massage.jpg" data-gal="magnific-pop-up[gallery]" title="Gallery project"><img src="../web/img/backgrounds/massage.jpg" alt="Gallery project" /><span class="zoom-icon gallery-icon"></span></a>
                                    </figure>
                                    <!-- Thumbnail Ends -->
                                    <!-- Caption Starts -->
                                    <div class="caption">
                                        <h3>Ogawa Massager</h3>
                                        <p>"10000 points"</p>
                                        <button id="myBtn6" class="custom-button">Redeem</button>
                                        <!-- The Modal -->
                                        <div id="myModal" class="modal">
                                            <p>QR Code</p>
                                            <span class="close">&times;</span>                                                                                            
                                        </div>
                                        <!-- End of Modal -->  
                                    </div>
                                    <!-- Caption Ends -->
                                </div>
                            </div>
                            <!-- Project Ends -->                                                                
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
                                <p class="text-center">Copyright © 2018 FeelinTrippy All Rights Reserved</p>
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

        <script>
            // Get the modal for btn1
            var modal = document.getElementById('myModal');

            // Get the button that opens the modal
            var btn = document.getElementById("myBtn");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks the button, open the modal 
            btn.onclick = function () {
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>

        <script>
            // Get the modal2
            var modal = document.getElementById('myModal2');

            // Get the button that opens the modal
            var btn = document.getElementById("myBtn2");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks the button, open the modal 
            btn.onclick = function () {
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>

        <script>
            // Get the modal3
            var modal = document.getElementById('myModal');

            // Get the button that opens the modal
            var btn = document.getElementById("myBtn3");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks the button, open the modal 
            btn.onclick = function () {
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>

        <script>
            // Get the modal4
            var modal = document.getElementById('myModal');

            // Get the button that opens the modal
            var btn = document.getElementById("myBtn4");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks the button, open the modal 
            btn.onclick = function () {
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>

        <script>
            // Get the modal5
            var modal = document.getElementById('myModal');

            // Get the button that opens the modal
            var btn = document.getElementById("myBtn5");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks the button, open the modal 
            btn.onclick = function () {
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>

        <script>
            // Get the modal6
            var modal = document.getElementById('myModal');

            // Get the button that opens the modal
            var btn = document.getElementById("myBtn6");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks the button, open the modal 
            btn.onclick = function () {
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>


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