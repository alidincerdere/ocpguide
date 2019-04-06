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
                    <h1>${chapterTitle}</h1>
                    <a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Toggle Menu</a>
                </div>
                <div class="col-lg-3">
                    <img src="/static/img/java8logo.png">
                </div>
            </div>

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
<textarea class="form-control myCodeText" rows="15" id="comment_${contentLoop}" style="background-color:black;text-align: left; text-align-all: left; color: #999999;" ><c:forEach var="codeLine" items="${pageComponent.content}">
${codeLine}</c:forEach>
</textarea>


                    </div>

                    <div class="form-group">
                        <a id="myCompile_${contentLoop}" class="btn btn-primary" href="#" onclick="compile(${contentLoop}); return false" role="button">Run</a>
                    </div>

                    <div class="form-group">
                        <textarea class="form-control" rows="3" id="compileResult_${contentLoop}" readonly></textarea>
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