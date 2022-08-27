<%--
  Created by IntelliJ IDEA.
  User: fhohf
  Date: 2022-08-21(021)
  Time: 오후 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="board">
    <div class="evnt_img_input_form">
        <h1>상품 이미지 수정</h1>
        <form action="<c:url value='/event/image/modify'/>" enctype="multipart/form-data" method="post" id="image_upload">
            <input type="file" name="img_file" id="img_file" class="input_prd"><br>

        </form>
        <div class="preview-img">
            <img src="${param.evnt_img_pth}" id="event_img" width="500px" height="300px">
            <button id="send">수정</button>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    function setImageFromFile(input, expression) {
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                $(expression).attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $(document).ready(function (){
        $('#img_file').change(function(){
            setImageFromFile(this, '#event_img');
        });

        $('#send').on("click",function (){
            const formData = new FormData($("#image_upload")[0]);
            formData.append("img_file",$("#img_file")[0].files[0]);
            let img  = $('#img_file').val();
            if(!img){
                alert("이미지를 첨부하고 다시 시도바랍니다.");
            }else{
                $.ajax({
                    type: "POST",
                    url : "<c:url value='/event/image/modify'/>",
                    data : formData,
                    contentType: false,
                    processData: false,
                    cache : false,
                    success: function(data){
                        if(data=="success"){
                            alert("이미지가 수정되었습니다.");

                        }else{
                            alert("이미지 등록을 실패했습니다.");
                            window.location.href = "<c:url value='/event/image/modify?evnt_img_pth=${param.evnt_img_pth}'/>";
                        }
                    }
                });
            }
        });
    });
</script>
</body>
</html>