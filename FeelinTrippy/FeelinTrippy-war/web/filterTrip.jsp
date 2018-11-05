<%-- 
    Document   : filterTrip
    Created on : 28 Oct, 2018, 4:23:54 PM
    Author     : CheeS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <title>FeelinTrippy - Filter Trips</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <!-- Favicon -->
        <link rel="shortcut icon" href="http://via.placeholder.com/30x30">

        <!-- Template CSS Files -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="css/magnific-popup.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/skins/yellow.css" />

        <!-- Revolution Slider CSS Files -->
        <link rel="stylesheet" type="text/css" href="js/plugins/revolution/css/settings.css" />
        <link rel="stylesheet" type="text/css" href="js/plugins/revolution/css/layers.css" />
        <link rel="stylesheet" type="text/css" href="js/plugins/revolution/css/navigation.css" />

        <!-- Template JS Files -->
        <script src="js/modernizr.js"></script>

    </head>

    <body class="double-diagonal dark">

        <% if (request.getSession().getAttribute("user") == null) { %>
                <script type="text/javascript">
                        alert('Please login to start trippy');
                        document.location.href = 'login.jsp';
                    </script>
            <% }%>



        <!-- Preloader Starts -->
        <div class="preloader" id="preloader">
            <div class="logopreloader">
                <img src="./img/preloaders/walking-man.png" height="128" width="100" alt="logo">            
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
                            <a data-toggle="collapse" data-target=".navbar-collapse.show" class="navbar-brand" href="mainPage.jsp">
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
                                <li class="active"><a href="mainpage.jsp"><i class="fa fa-home"></i> Home</a></li>                               
                                <li><a href="mySavedTrips.jsp"><i class="fa fa-envelope"></i> Saved Trips</a></li>
                                <li><a href="myPastTrips.jsp"><i class="fa fa-envelope"></i> Past Trips</a></li>
                                <li><a href="profile.jsp"><i class="fa fa-user"></i> Profile</a></li>
                                <li><a href="javascript:logout();"><i class="fa fa-user"></i>    
                                        Log Out
                                    
                                    </a></li>
                            </ul>
                            <!-- Main Menu Ends -->
                        </div>
                        <!-- Navigation Menu Ends -->
                    </nav>
                    <!-- Navbar Ends -->
                </div>
            </header>
            <!-- Header Ends -->



            <div class="text-center" style="margin-top: 150px">
                <h3>Set Your Budget</h3>
            </div>
            <form onSubmit="#" action="ActivityServlet" >
                <div class="slidecontainer" >
                    <input type="range" min="1" max="100" value="50" class="slider" id="myRange">
                    <h3 style="text-align: center">$ <span id="demo"></span></h3>
                </div>

                <div class="text-center" style="margin-top: 30px">
                    <h3>Type Of Activities</h3>
                </div>

                <script>
                    var slider = document.getElementById("myRange");
                    var output = document.getElementById("demo");
                    output.innerHTML = slider.value;
                    slider.oninput = function () {
                    output.innerHTML = this.value;
                    h
                    }
                </script>

                <div class="row services-box">
                    <div class="col-lg-4 col-md-6 col-sm-12 services-box-item">
                        <!-- Service Item Cover Starts -->
                        <span class="services-box-item-cover fa fa-leaf" data-headline="Creative Solutions"></span>
                    </div>
                </div>

                <div class ="buttondiv" style="height:200px">
                    <a id="btn1" class="first-page-button" href="#btn1" onClick="showme(1)">Everything!</a>
                    <a id="btn2" class="second-page-button" href="#btn2" onClick="showme(2)">Adventure!</a>
                    <a id="btn3" class="third-page-button" href="#btn3" onClick="showme(3)">Culture!</a>
                    <a id="btn4" class="fourth-page-button" href="#btn4" onClick="showme(4)">Foodie!</a>
                    <a id="btn5" class="fifth-page-button" href="#btn5" onClick="showme(5)">Nightowl!</a>
                </div>                
                <div class ="text-center">
                    <button class="trippy-button" type="submit" > Trippy! </button>
                </div>
            </form>
        </div>
        <!-- Wrapper Ends -->


        <!-- The script to get the variable for the item to be pass to the backend to filter -->
        <script >
            function showme(count) {
            test = "everything";
            number = count;
            switch (number) {
            case 1:
                    test = "everything";
            break;
            case 2:
                    test = "adventure";
            break;
            case 3:
                    test = "culture";
            break;
            case 4:
                    test = "foodie";
            break;
            case 5:
                    test = "nightowl";
            break;
            default:
                    test = "everything";
            }

            }

            function logout() {
                <% session = request.getSession();
                session.invalidate();%>
                
                alert("Logout Successfully!");
                document.location.href = 'mainPage.jsp';
            }
            /*
             function testing(click){
             alert("You picked "+ test);
             }*/

        </script>



        <!-- Template JS Files -->
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/plugins/jquery.easing.1.3.js"></script>
        <script src="js/plugins/bootstrap.min.js"></script>
        <script src="js/plugins/jquery.bxslider.min.js"></script>
        <script src="js/plugins/jquery.filterizr.js"></script>
        <script src="js/plugins/jquery.magnific-popup.min.js"></script>

        <!-- Revolution Slider Main JS Files -->
        <script src="js/plugins/revolution/js/jquery.themepunch.tools.min.js"></script>
        <script src="js/plugins/revolution/js/jquery.themepunch.revolution.min.js"></script>

        <!-- Revolution Slider Extensions -->

        <script src="js/plugins/revolution/js/extensions/revolution.extension.actions.min.js"></script>
        <script src="js/plugins/revolution/js/extensions/revolution.extension.carousel.min.js"></script>
        <script src="js/plugins/revolution/js/extensions/revolution.extension.kenburn.min.js"></script>
        <script src="js/plugins/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
        <script src="js/plugins/revolution/js/extensions/revolution.extension.migration.min.js"></script>
        <script src="js/plugins/revolution/js/extensions/revolution.extension.navigation.min.js"></script>
        <script src="js/plugins/revolution/js/extensions/revolution.extension.parallax.min.js"></script>
        <script src="js/plugins/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
        <script src="js/plugins/revolution/js/extensions/revolution.extension.video.min.js"></script>

        <!-- Main JS Initialization File -->
        <script src="js/custom.js"></script>

    </body>

</html>
