<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-latest.min.js"/>
    <script>
        $('#submit').on("click",function (){
            const formData = new FormData($("#image_upload")[0]);
            formData.append("img_file",$("#img_file")[0].files[0]);
            let img  = $('#img_file').val();
            if(!img){
                alert("이미지를 첨부하고 다시 시도바랍니다.");
            }else{
                $.ajax({
                    type: "POST",
                    url : "/product/insert/image",
                    data : formData,
                    contentType: false,
                    processData: false,
                    cache : false,
                    success: function(data){
                        if(data=="success"){
                            alert("이미지가 등록되었습니다.");
                        }else{
                            alert("이미지 등록을 실패했습니다.");
                        }
                    }
                });
            }
        });
    </script>
<body>
    <form action="<c:url value='/product/insert/image'/>" enctype="multipart/form-data" method="post" id="image_upload">
        <input type="file" name="img_file" id="img_file">
        <input type="hidden" name="prd_cd" value="${param.prd_cd}">
        <button id="submit">전송</button>
    </form>

</body>
</html>
