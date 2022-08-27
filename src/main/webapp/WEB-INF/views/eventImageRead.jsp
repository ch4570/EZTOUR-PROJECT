<%--
  Created by IntelliJ IDEA.
  User: fhohf
  Date: 2022-08-21(021)
  Time: 오후 4:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<H1>이벤트 이미지 관리 페이지</H1>
<div class="event_img_input_form">
    <h1>상품 이미지 조회</h1>
    <div class="preview-img">
        <img src="${param.evnt_img_pth}" id="evnt_img" width="500px" height="300px">
        <button id="modify_btn" class="button_click">수정</button><br>
        <button id="delete_btn" class="button_click">삭제</button>
    </div>
</div>
</div>
</div>
</div>
</div>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).ready(function (){
        $('#delete_btn').on("click",function (){
            var result = confirm("정말 삭제하시겠습니까?");
            if(result){
                $.ajax({
                    type: "POST",
                    url : "<c:url value='/event/image/delete'/>",
                    data: {prd_img_no:"${param.prd_img_no}",img_pth:"${param.img_pth}"},
                    success : function (data){
                        if(data=="success"){
                            alert('삭제가 성공했습니다.');

                        }else{
                            alert('삭제가 실패했습니다.');
                            window.location.href = "<c:url value="/event/image/read"/>"
                        }
                    }
                });
            }else{
                return;
            }
        });

        $('#modify_btn').on("click",function (){
            window.location.href = "<c:url value='/event/image/modify?evnt_img_pth=${param.evnt_img_pth}'/>";
        });
    });
</script>
</body>
</html>