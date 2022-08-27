<%--
  Created by IntelliJ IDEA.
  User: fhohf
  Date: 2022-08-25(025)
  Time: 오전 4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/resources/js/uploadAjax.js"></script>
    <title>업로드_Ajax</title>
</head>
<body>
<h3>업로드</h3>
<div>
    <input type="file" name="uploadFile" multiple>
</div>
<div>
    <input type="submit" id="uploadBtn" value="파일 전송!!">
</div>
<img src="/resources/img/loopy02.jpeg">
</body>
<script>
$(document).ready(function(){

    var reg = new RegExp("(.*?)\.(exe|zip|alz|js)$")

    var maxSize=5242880;

    function checkExtension(fileName,fileSize){

        if(fileSize > maxSize){
            alert("파일 사이즈 초과");
            return false;
        }

        if(reg.test(fileName)){
            alert("해당 종류의 파일은 업로드 할 수 없습니다.");
            return false;
        }
        return true;
    }


   $("#uploadBtn").on("click",function(){
      alert("버튼누르면 연결되는지 확인용");

      var formData = new FormData();

      var inputFile = $("input[name='uploadFile']");
      console.log(inputFile);
      var files = inputFile[0].files;
      console.log(files);

      for(var i=0; i<files.length; i++){
          formData.append("uploadFile",files[i]);
      }
      $.ajax({
          type:"post",
          url:"/uploadAjaxAction",
          data:formData,
          contentType:false,
          processData:false,
          success:function (result){
              console.log(result)
          }
      })
})
})
</script>
</html>
