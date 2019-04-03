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
                        <c:choose>
                            <c:when test="${selectedChapter.level == 1}">
                                <c:choose>
                                    <c:when test="${selectedChapter.id == chapterList.get(loop.index).id}">
                                        <a href="/${chapterList.get(loop.index).url}?id=${chapterList.get(loop.index).id}" style="background: rgba(255, 255, 255, 0.2)">${chapterList.get(loop.index).chapterNum}  ${chapterList.get(loop.index).name}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/${chapterList.get(loop.index).url}?id=${chapterList.get(loop.index).id}">${chapterList.get(loop.index).chapterNum}  ${chapterList.get(loop.index).name}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <a href="/${chapterList.get(loop.index).url}?id=${chapterList.get(loop.index).id}">${chapterList.get(loop.index).chapterNum}  ${chapterList.get(loop.index).name}</a>
                            </c:otherwise>
                        </c:choose>

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

                                    <c:choose>
                                        <c:when test="${selectedChapter.level == 2}">
                                            <c:choose>
                                                <c:when test="${selectedChapter.id == chapterList.get(innerCount).id}">
                                                    <a href="/${chapterList.get(innerCount).url}?id=${chapterList.get(innerCount).id}" style="background: rgba(255, 255, 255, 0.2)">${chapterList.get(innerCount).chapterNum}  ${chapterList.get(innerCount).name}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="/${chapterList.get(innerCount).url}?id=${chapterList.get(innerCount).id}">${chapterList.get(innerCount).chapterNum}  ${chapterList.get(innerCount).name}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/${chapterList.get(innerCount).url}?id=${chapterList.get(innerCount).id}">${chapterList.get(innerCount).chapterNum}  ${chapterList.get(innerCount).name}</a>
                                        </c:otherwise>
                                    </c:choose>
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
                                            <li>

                                                <c:choose>
                                                    <c:when test="${selectedChapter.level == 3}">
                                                        <c:choose>
                                                            <c:when test="${selectedChapter.id == chapterList.get(inner3Count).id}">
                                                                <a href="/${chapterList.get(inner3Count).url}?id=${chapterList.get(inner3Count).id}" style="background: rgba(255, 255, 255, 0.2)">${chapterList.get(inner3Count).chapterNum}  ${chapterList.get(inner3Count).name}</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="/${chapterList.get(inner3Count).url}?id=${chapterList.get(inner3Count).id}">${chapterList.get(inner3Count).chapterNum}  ${chapterList.get(inner3Count).name}</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="/${chapterList.get(inner3Count).url}?id=${chapterList.get(inner3Count).id}">${chapterList.get(inner3Count).chapterNum}  ${chapterList.get(inner3Count).name}</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </li>
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

            <c:set var = "contentLoop" value = "${0}"/>
            <c:forEach var="pageComponent" items="${pageComponentList}">

                <c:if test="${pageComponent.componentType == 'DESCRIPTION'}">
                    <c:forEach var="descLine" items="${pageComponent.content}">
                        <p>${descLine}</p>
                    </c:forEach>

                </c:if>


                <c:if test="${pageComponent.componentType == 'CODE_SNIPPED'}">

                    <div class="form-group">
                        <label for="comment_${contentLoop}">Code Snipped:</label>

                        <textarea class="form-control myCodeText" rows="15" id="comment_${contentLoop}" style="background-color:black;text-align: left; color: #999999;" ><c:forEach var="codeLine" items="${pageComponent.content}">${codeLine}
                            </c:forEach>
                        </textarea>


                    </div>

                    <div class="form-group">
                        <a id="myCompile_${contentLoop}" class="btn btn-primary" href="#" onclick="compile(${contentLoop}); return false" role="button">Run</a>
                    </div>

                    <div class="form-group">
                        <textarea class="form-control" rows="3" id="compileResult_${contentLoop}"></textarea>
                    </div>
                </c:if>

                <c:set var = "contentLoop" value = "${contentLoop + 1}"/>

            </c:forEach>

        </div>

        <br />
        <div class="row">

            <br />
            <div class="col-sm-4">
                <c:if test="${prevPage ne null}">
                    <a id="prev_page" class="btn btn-primary" href="/${prevPage.url}?id=${prevPage.id}" role="button">Previous Chapter</a>
                </c:if>
            </div>

            <div class="col-sm-4">

            </div>

            <div class="col-sm-4" style="align-content: flex-end">
                <c:if test="${nextPage ne null}">
                    <a id="next_page" class="btn btn-primary"  href="/${nextPage.url}?id=${nextPage.id}" role="button">Next Chapter</a>
                </c:if>
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

    function compile(sequenceNum) {
        console.log("hey");

        $.ajaxSetup({
            contentType:"application/json;charset=utf-8"
        })

        var thisVar = $("#comment"+ "_" + sequenceNum).next()[0];
        //debugger;
        //console.log(thisVar.getValue());
        //console.log(thisVar.CodeMirror.getValue());
        $.post( "/compileAndRun/", JSON.stringify({"script":thisVar.CodeMirror.getValue()}))
            .done(function( data ) {
                //alert( "Script Loaded: " + data );
                $('#compileResult' + "_" + sequenceNum).text(data);
            }, "json");

    }

    $( document ).ready(function() {
        console.log( "ready!" );

        $(".myCodeText").each(function( index ) {
            var myCodeMirror = CodeMirror.fromTextArea(this,{mode:"clike", lineNumbers:true});
            //console.log(myCodeMirror.getValue());
        });

        //var myCodeMirror = CodeMirror.fromTextArea(myCodeText,{mode:"java", lineNumbers:true});
        /*
        var myCodeMirror = CodeMirror(function(elt) {
            myTextArea.parentNode.replaceChild(elt, myTextArea);
        }, {value: myTextArea.value});
        */

    });
</script>
<%@ include file="common/footer.jspf"%>