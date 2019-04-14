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



                <c:forEach var="question" items="${questionList}">

                    <div class="row">
                        <p>${question.number}.${question.text}</p>
                    </div>

                    <br/>

                    <div class="row">
                        <c:if test="${question.code ne null}">
                            <textarea class="form-control myCodeText"  id="code_${question.number}" style="background-color:black;text-align: left; text-align-all: left; color: #999999;">${question.code}</textarea>
                        </c:if>
                    </div>

                    <br/>

                    <div class="row">
                        <form onsubmit="return checkAnswer(${question.number},'${question.serializedCorrectAnswer}','${question.explanation}');">
                        <c:forEach var="option" items="${question.options}">
                            <div class="checkbox">
                                <label><input type="checkbox" value="${option.letter}" name="checkbox_${question.number}"> ${option.letter}. ${option.optionText}</label>
                            </div>
                        </c:forEach>
                            <input type="submit">
                        </form>



                    </div>

                    <div class="row">
                        <p id="correctAnswer_${question.number}"></p>
                    </div>

                </c:forEach>

            <div class="row">

                <button type="button" onclick="checkAllAnswers()">Check Score</button>

            </div>

            <div class="row">
                
                <p id="score"></p>
            </div>

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


    function checkAnswer(number, correctAnswers, explanation) {

        $("#score").text("");

        if(correctAnswers != undefined) {
            var answerKey = correctAnswers.split(",");
            var answers = [];
            $.each($("input[name='checkbox_" + number + "']:checked"), function () {
                answers.push($(this).val());
            });
            if (answerKey.length != answers.length) {
                $("#correctAnswer_" + number).text("Result: Fail, Correct Answers: " + correctAnswers + ", Explanation: " + explanation);
                $("#correctAnswer_" + number).css("color",'red');
                return false;
            }

            for(var i=0; i<answers.length; i++) {
                if(answerKey[i] != answers[i]){
                    $("#correctAnswer_" + number).text("Result: Fail, Correct Answers: " + correctAnswers + ", Explanation: " + explanation);
                    $("#correctAnswer_" + number).css("color",'red');
                    return false;
                }
            }

            $("#correctAnswer_" + number).text("Result: Success, Correct Answers: " + correctAnswers + ", Explanation: " + explanation);
            $("#correctAnswer_" + number).css("color",'green');

            return false;
        }

        return false;
    }


    function checkAnswerReturn(number, correctAnswers, explanation) {

        if(correctAnswers != undefined) {
            var answerKey = correctAnswers.split(",");
            var answers = [];
            $.each($("input[name='checkbox_" + number + "']:checked"), function () {
                answers.push($(this).val());
            });
            if (answerKey.length != answers.length) {
                $("#correctAnswer_" + number).text("Result: Fail, Correct Answers: " + correctAnswers + ", Explanation: " + explanation);
                $("#correctAnswer_" + number).css("color",'red');
                return false;
            }

            for(var i=0; i<answers.length; i++) {
                if(answerKey[i] != answers[i]){
                    $("#correctAnswer_" + number).text("Result: Fail, Correct Answers: " + correctAnswers + ", Explanation: " + explanation);
                    $("#correctAnswer_" + number).css("color",'red');
                    return false;
                }
            }

            $("#correctAnswer_" + number).text("Result: Success, Correct Answers: " + correctAnswers + ", Explanation: " + explanation);
            $("#correctAnswer_" + number).css("color",'green');

            return true;
        }

        return false;
    }


    function checkAllAnswers() {

        var correctCounter = 0;

        var list = ${questionListAsJson};

        list.forEach(function (entry) {

            if(checkAnswerReturn(entry.number, entry.serializedCorrectAnswer,entry.explanation)) {
                correctCounter++;
            }
        });

        console.log(correctCounter);

        $("#score").text("Your score is: " + correctCounter + "/" + list.length);

    }
</script>
<%@ include file="common/footer.jspf"%>