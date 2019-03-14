<%@ include file="common/header.jspf"%>

<div id="wrapper" class="toggled">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="${baseUrl}">
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
            <h1>WELCOME TO OCP PREPARATION GUIDE</h1>
            <p>This site helps you to prepare JAVA 8 OCP exam</p>
            <p>The content and code samples are taken from the book OCP Oracle Certified Professional JAVA SE 8 Programmer 2 by Jeanne Boyarsky and Scott Selikoff</p>

            <a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Toggle Menu</a>


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