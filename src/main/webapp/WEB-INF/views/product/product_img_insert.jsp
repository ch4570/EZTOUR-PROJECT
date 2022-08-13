<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>상품 이미지 등</h1>
    <form action="<c:url value='/product/insert/image'/>" enctype="multipart/form-data" method="post" id="image_upload">
        <input type="file" name="img_file" id="img_file">
        <input type="hidden" name="prd_cd" value="${param.prd_cd}">
        <input type="hidden" name="sch_no" value="${param.sch_no}">
    </form>
    <div class="preview-img">
        <img src="" id="product_img" width="500px" height="300px">
    </div>
    <button id="send">전송</button>
</body>
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
            setImageFromFile(this, '#product_img');
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
                    url : "<c:url value='/product/insert/image'/>",
                    data : formData,
                    contentType: false,
                    processData: false,
                    cache : false,
                    success: function(data){
                        if(data=="success"){
                            alert("이미지가 등록되었습니다.");
                            window.location.reload();
                        }else{
                            alert("이미지 등록을 실패했습니다.");
                            window.location.reload();
                        }
                    }
                });
            }
        });
    });
</script>
</html>
