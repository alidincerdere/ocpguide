<%@ include file="common/header.jspf"%>

<div id="wrapper" class="toggled">

    <!-- Sidebar -->
    <%@include file="common/sidebar.jspf"%>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-9">
                    <h1>MY JAVA 8 OCP EXAM PREPARATION NOTES</h1>
                    <p>This site includes my notes to prepare JAVA 8 OCP exam</p>
                    <p>Almost all of the content and code samples are taken from the book OCP Oracle Certified Professional JAVA SE 8 Programmer 2 by Jeanne Boyarsky and Scott Selikoff</p>

                    <a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Toggle Menu</a>
                </div>
                <div class="col-lg-3">
                    <img src="/static/img/java8logo.png">
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<!--<script src="vendor/jquery/jquery.min.js"></script>-->
<!--<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>-->
<script src="webjars/bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });

</script>
<%@ include file="common/footer.jspf"%>