<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html lang="ko">
</head>
<body>
  <H3>업로드를 해보자.</H3>
<from action="uploadFormAction" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile"multiple>
    <input type="submit" value="파일 전송">
</from>


















<%--<div class="wrap">--%>
<%--    <div class="content">--%>
<%--            <h1>이벤트 이미지 등록</h1>--%>
<%--           <hr>--%>
<%--           <div class="board">--%>
<%--             <div class="prd_img_input_form">--%>

<%--               <form action="<c:url value='/event/insert/image'/>" enctype="multipart/form-data" method="post" id="image_upload">--%>
<%--            <input type="hidden" name="frs_rgs_no" value="${sessionScope.usr_id}"/>--%>
<%--            <input type="file" name="img_file" id="img_file" class="input_prd"><br>--%>
<%--               </form>--%>
<%--                <div class="preview-img">--%>
<%--               <img src="" id="event_img" width="500px" height="300px">--%>
<%--                <button id="send">전송</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--           </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<script src="https://code.jquery.com/jquery-latest.min.js"></script>--%>
<%--<script>--%>
<%--    function setImageFromFile(input, expression) {--%>
<%--        if (input.files && input.files[0]) {--%>
<%--            const reader = new FileReader();--%>
<%--            reader.onload = function (e) {--%>
<%--                $(expression).attr('src', e.target.result);--%>
<%--            }--%>
<%--            reader.readAsDataURL(input.files[0]);--%>
<%--        }--%>
<%--    }--%>

<%--    $(document).ready(function (){--%>
<%--        $('#img_file').change(function(){--%>
<%--            setImageFromFile(this, '#event_img');--%>
<%--        });--%>

<%--        $('#send').on("click",function(){--%>
<%--            const formData = new FormData($("#image_upload")[0]);--%>
<%--            formData.append("img_file",$("#img_file")[0].files[0]);--%>
<%--            let img  = $('#img_file').val();--%>
<%--            if(!img){--%>
<%--                alert("이미지를 첨부하고 다시 시도바랍니다.");--%>
<%--            }else{--%>
<%--                $.ajax({--%>
<%--                    type: "POST",--%>
<%--                    url : '/event/insert/image',--%>
<%--                    data : formData,--%>
<%--                    contentType: false,--%>
<%--                    processData: false,--%>
<%--                    cache : false,--%>
<%--                    success: function(data){--%>
<%--                        if(data=="success"){--%>
<%--                            alert("이미지가 등록되었습니다.");--%>
<%--                            window.location.reload();--%>
<%--                        }else{--%>
<%--                            alert("이미지 등록을 실패했습니다.");--%>
<%--                            window.location.reload();--%>
<%--                        }--%>
<%--                    }--%>
<%--                });--%>
<%--            }--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
</body>
</html>
