<%@ include file="common/header.jspf"%>

<div id="wrapper" class="toggled">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    OCP Preparation Guide
                </a>
            </li>



            <c:forEach begin="0" end="${chapterList.size()-1}" varStatus="loop">

                <c:if test = "${chapterList.get(loop.index).level == 1}">
                    <li data-toggle="collapse" data-target="#chapterSub${chapterList.get(loop.index).id}"  class="collapsed active">
                        <div class="row">
                            <a href="#">
                                <a href="/${chapterList.get(loop.index).url}" id="${chapterList.get(loop.index).id}" >${chapterList.get(loop.index).name}</a>
                                <i class="fas fa-angle-down" style="color: white"></i>
                            </a>
                        </div>
                    </li>

                    <c:if test = "${chapterList.get(loop.index + 1).level == 2}">

                        <ul class="sub-menu collapse" id="chapterSub${chapterList.get(loop.index).id}">

                            <c:forEach begin="${loop.index + 1}" end="${chapterList.size()-1}" varStatus="innerloop">

                                <c:if test = "${chapterList.get(innerloop.index).level == 2}">

                                    <li><a href="/${chapterList.get(innerloop.index).url}">${chapterList.get(innerloop.index).name}</a></li>



                                </c:if>



                            </c:forEach>

                        </ul>
                    </c:if>





                 </c:if>



                <c:if test = "${chapter.level == 3}">

                </c:if>



            </c:forEach>



            <li data-toggle="collapse" data-target="#chapterSub"  class="collapsed active">
                <div class="row">
                    <a href="#">
                        <a href="/chapterOne" id="chapter1" >Chapter 1: Advanced Class Design</a>
                        <i class="fas fa-angle-down" style="color: white"></i>
                    </a>
                </div>
            </li>
            <ul class="sub-menu collapse" id="chapterSub">
                <li class="active"><a href="#">Subchapter</a></li>
            </ul>


            <li  data-toggle="collapse" data-target="#products" class="collapsed active">
                <div class="row">

                    <a href="#">

                        <a href="chapterOne"> Chapter 2: Design Pattrens and Principles <span class="arrow" style="color: white"></span></a>
                        <i class="fas fa-angle-down" style="color: white; "></i>

                    </a>

                </div>

            </li>
            <ul class="sub-menu collapse" id="products">
                <li class="active"><a href="#">CSS3 Animation</a></li>
                <li><a href="#">General</a></li>
                <li><a href="#">Buttons</a></li>
                <li><a href="#">Tabs & Accordions</a></li>
                <li><a href="#">Typography</a></li>
                <li><a href="#">FontAwesome</a></li>
                <li><a href="#">Slider</a></li>
                <li><a href="#">Panels</a></li>
                <li><a href="#">Widgets</a></li>
                <li><a href="#">Bootstrap Model</a></li>
            </ul>
            <li>
                <a href="#">Chapter 3: Generics and Collections</a>
            </li>
            <li>
                <a href="#">Chapter 4: Functional Programming</a>
            </li>
            <li>
                <a href="#">Chapter 5: Dates, String and Localization</a>
            </li>
            <li>
                <a href="#">Chapter 6: Exceptions and Assertions</a>
            </li>
            <li>
                <a href="#">Chapter 7: Concurrency</a>
            </li>
            <li>
                <a href="#">Chapter 8: IO</a>
            </li>
            <li>
                <a href="#">Chapter 9: NIO.2</a>
            </li>
            <li>
                <a href="#">Chapter 10: JDBC</a>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <h1>Simple Sidebar</h1>
            <p>This template has a responsive menu toggling system. The menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will appear/disappear. On small screens, the page content will be pushed off canvas.</p>
            <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>.</p>
            <a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Toggle Menu</a>


            <div class="form-group">
                <label for="comment">Comment:</label>
                <textarea class="form-control" rows="15" id="comment"></textarea>

            </div>

            <div class="form-group">
                <a id="myCompile" class="btn btn-primary" href="#" role="button">Run</a>
            </div>

            <div class="form-group">
                <textarea class="form-control" rows="3" id="compileResult"></textarea>
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

    $('#myCompile').click(function(){
        compile();
    });

    function compile() {
        console.log("hey");

        $.ajaxSetup({
            contentType:"application/json;charset=utf-8"
        })

        $.post( "/compileAndRun/", JSON.stringify({"script":$("#comment").val()}))
            .done(function( data ) {
                //alert( "Script Loaded: " + data );
                $('#compileResult').text(data);
            }, "json");

/*
        $.post(
            '/compileAndRun/',
            JSON.stringify({"script":$("#comment").val()}),
            function( data ){
                alert( "Script Loaded: " + data );
            },
            'json'
        );
*/

/*
        $.ajax({
            url:/compileAndRun/,
            type:"POST",
            data:JSON.stringify({"script":$("#comment").val()}),
            contentType:"application/json; charset=utf-8",
            dataType:"json",
            success: function(data) {
                alert( "Script Loaded: " + data )
            }
        });
*/



    }
</script>
<%@ include file="common/footer.jspf"%>