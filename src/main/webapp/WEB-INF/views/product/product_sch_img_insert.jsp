<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <h1>관광지 사진 등록</h1>
    <form action="<c:url value='/product/schedule/image/insert'/>" method="post" enctype="multipart/form-data" id="image_upload">
        <input type="text" name="sch_no" placeholder="일정 번호 입력"><br>
        <input type="text" name="prd_cd" placeholder="상품 번호 입력"><br>
        <input type="file" name="prd_img" id="prd_img1"><br>
        <div class="preview-img">
            <img src="" id="product_img1" width="500px" height="300px">
        </div>
        <input type="file" name="prd_img" id="prd_img2"><br>
        <div class="preview-img">
            <img src="" id="product_img2" width="500px" height="300px">
        </div>
        <input type="file" name="prd_img" id="prd_img3"><br>
        <div class="preview-img">
            <img src="" id="product_img3" width="500px" height="300px">
        </div>
    </form>
    <button id="submit">등록</button>

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
                $('#prd_img1').change(function(){
                setImageFromFile(this, '#product_img1');
                });

                $('#prd_img2').change(function(){
                    setImageFromFile(this, '#product_img2');
                });

                $('#prd_img3').change(function(){
                    setImageFromFile(this, '#product_img3');
                });

                $('#submit').on("click",function (){
                    const formData = new FormData($("#image_upload")[0]);
                    formData.append("prd_img",$("#prd_img1").val());
                    formData.append("prd_img",$("#prd_img2").val());
                    formData.append("prd_img",$("#prd_img3").val());
                        $.ajax({
                            type: "POST",
                            url : "<c:url value='/product/schedule/image/insert'/>",
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

                 });
            });
    </script>
</html>
