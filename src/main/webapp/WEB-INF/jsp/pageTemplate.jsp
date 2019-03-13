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

            <c:forEach begin="0" end="${chapterList.size()-1}" varStatus="loop" var="outerCount">

            <c:if test = "${chapterList.get(loop.index).level == 1}">
            <li data-toggle="collapse" data-target="#chapterSub${chapterList.get(loop.index).id}"  class="collapsed active">
                <div class="row">
                    <a href="#">
                        <a href="/${chapterList.get(loop.index).url}?id=${chapterList.get(loop.index).id}">${chapterList.get(loop.index).chapterNum}  ${chapterList.get(loop.index).name}</a>
                        <i class="fas fa-angle-down" style="color: white"></i>
                    </a>
                </div>
            </li>

            <c:if test = "${chapterList.get(loop.index + 1).parentChapter == chapterList.get(loop.index).id}">

            <c:choose>
            <c:when test="${selectedFirstLevelParentId ne null}">
            <c:choose>
            <c:when test="${selectedFirstLevelParentId == chapterList.get(loop.index).id}">
            <ul class="sub-menu collapse show" id="chapterSub${chapterList.get(loop.index).id}">
                </c:when>
                <c:otherwise>
                <ul class="sub-menu collapse" id="chapterSub${chapterList.get(loop.index).id}">
                    </c:otherwise>
                    </c:choose>

                    </c:when>
                    <c:otherwise>
                    <ul class="sub-menu collapse" id="chapterSub${chapterList.get(loop.index).id}">
                        </c:otherwise>
                        </c:choose>


                        <c:forEach begin="${loop.index + 1}" end="${chapterList.size()-1}"  var="innerCount">


                        <c:if test="${chapterList.get(innerCount).parentChapter == chapterList.get(loop.index).id}">
                        <li data-toggle="collapse" data-target="#chapterSub${chapterList.get(innerCount).id}"  class="collapsed active">
                            <div class="row">
                                <a href="#">
                                    <a href="/${chapterList.get(innerCount).url}?id=${chapterList.get(innerCount).id}">${chapterList.get(innerCount).chapterNum}  ${chapterList.get(innerCount).name}</a>
                                    <i class="fas fa-angle-down" style="color: white"></i>
                                </a>
                            </div>
                        </li>

                        <c:if test="${innerCount + 1<=chapterList.size()-1}">

                        <c:if test="${chapterList.get(innerCount + 1).parentChapter == chapterList.get(innerCount).id}">


                        <c:choose>
                        <c:when test="${selectedSecondLevelParentId ne null}">

                        <c:choose>
                        <c:when test="${selectedSecondLevelParentId == chapterList.get(innerCount).id }">
                        <ul class="sub-menu collapse show" id="chapterSub${chapterList.get(innerCount).id}">
                            </c:when>
                            <c:otherwise>
                            <ul class="sub-menu collapse" id="chapterSub${chapterList.get(innerCount).id}">

                                </c:otherwise>

                                </c:choose>
                                </c:when>
                                <c:otherwise>

                                <ul class="sub-menu collapse" id="chapterSub${chapterList.get(innerCount).id}">
                                    </c:otherwise>

                                    </c:choose>

                                    <c:forEach begin="${innerCount + 1}" end="${chapterList.size()-1}"  var="inner3Count">

                                        <c:if test="${chapterList.get(inner3Count).parentChapter == chapterList.get(innerCount).id}">
                                            <li><a href="/${chapterList.get(inner3Count).url}?id=${chapterList.get(inner3Count).id}">${chapterList.get(inner3Count).chapterNum}  ${chapterList.get(inner3Count).name}</a></li>
                                        </c:if>

                                    </c:forEach>

                                </ul>

                                </c:if>
                                </c:if>
                                </c:if>

                                </c:forEach>

                            </ul>
                            </c:if>

                            </c:if>

                            </c:forEach>
                        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <h1>${chapterTitle}</h1>

            <a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Toggle Menu</a>

            <c:forEach var="pageComponent" items="${pageComponentList}">

                <c:if test="${pageComponent.componentType == 'DESCRIPTION'}">
                    <p>${pageComponent.content}</p>
                </c:if>


                <c:if test="${pageComponent.componentType == 'CODE_SNIPPED'}">

                    <div class="form-group">
                        <label for="comment">Comment:</label>
                        <textarea class="form-control" rows="15" id="comment">${pageComponent.content}</textarea>

                    </div>

                    <div class="form-group">
                        <a id="myCompile" class="btn btn-primary" href="#" role="button">Run</a>
                    </div>

                    <div class="form-group">
                        <textarea class="form-control" rows="3" id="compileResult"></textarea>
                    </div>
                </c:if>

            </c:forEach>




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