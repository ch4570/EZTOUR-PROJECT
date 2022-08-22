<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h2>rvwCmtTest</h2>
comment: <input type="text" name="cmt_cont"><br>
<button id="sendBtn" type="button">SEND</button>
<button id="modBtn" type="button">수정</button>

<div id="rvwCmtList"></div>
<div id="replyForm" style="display:none">
  <input type="text" name="replyCmt">
  <button id="wrtRepBtn" type="button">등록</button>
</div>
<script>

  let rvw_no = 2;


  let showList = function (rvw_no){
    $.ajax({
      type:'GET',       // 요청 메서드
      url: '/comments?rvw_no='+rvw_no,  // 요청 URI
      success : function(result){
        $("#rvwCmtList").html(toHtml(result));
      },
      error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
    }); // $.ajax()

    alert("the request is sent")
  }


  $(document).ready(function() { // ajax start
    showList(rvw_no);

    $("#modBtn").click(function () {
      let cmt_no= $(this).attr("data-cmt_no");
      let cmt_cont = $("input[name=cmt_cont]").val();


      if (cmt_cont.trim() == '') {
        alert("댓글을 입력해주세요.")
        $("input[name=cmt_cont]").focus()
        return;
      }

      $.ajax({
        type: 'PATCH',       // 요청 메서드
        url: '/comments/'+cmt_no,  // 요청 URI // eztour/comments?rvw_no=2 POST
        headers: {"content-type": "application/json"}, // 요청 헤더
        data: JSON.stringify({cmt_no: cmt_no, cmt_cont: cmt_cont}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success: function (result) {
          alert(result)
          showList(rvw_no);
        },
        error: function () {
          alert("error")
        } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()
    });

    $("#wrtRepBtn").click(function () {
      let cmt_cont = $("input[name=replyCmt]").val();
      let pcmt_no = $("#replyForm").parent().attr("data-pcmt_no") // replyForm의 부모 = li, 답글의 부모


      if (cmt_cont.trim() == '') {
        alert("댓글을 입력해주세요.")
        $("input[name=replyCmt]").focus()
        return;
      }

      $.ajax({
        type: 'POST',       // 요청 메서드
        url: '/comments?rvw_no=' + rvw_no,  // 요청 URI // eztour/comments?rvw_no=2 POST
        headers: {"content-type": "application/json"}, // 요청 헤더
        data: JSON.stringify({pcmt_no: pcmt_no, rvw_no: rvw_no, cmt_cont: cmt_cont}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success: function (result) {
          alert(result)
          showList(rvw_no);
        },
        error: function () {
          alert("error")
        } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()

      // 답글 입력 후 초기화 작업
      $("#replyForm").css("display", "none")
      $("input[name=replyCmt]").val('');
      $("#replyForm").appendTo("body");
    });

    $("#sendBtn").click(function () {
      let cmt_cont = $("input[name=cmt_cont]").val();


      if (cmt_cont.trim() == '') {
        alert("댓글을 입력해주세요.")
        $("input[name=cmt_cont]").focus()
        return;
      }

      $.ajax({
        type: 'POST',       // 요청 메서드
        url: '/comments?rvw_no=' + rvw_no,  // 요청 URI // eztour/comments?rvw_no=2 POST
        headers: {"content-type": "application/json"}, // 요청 헤더
        data: JSON.stringify({rvw_no: rvw_no, cmt_cont: cmt_cont}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success: function (result) {
          alert(result)
          showList(rvw_no);
        },
        error: function () {
          alert("error")
        } // 에러가 발생했을 때, 호출될 함수
      }); // $.ajax()
    });

    $("#rvwCmtList").on("click", ".modBtn", function () { // 1) 각 comment 붙어 있는 [수정] 버튼
      let cmt_no = $(this).parent().attr("data-cmt_no");
      let cmt_cont = $("span.cmt_cont", $(this).parent()).text() // 수정 버튼(this)의 부모는 li 이다. li의 span를 가져옴

      // 1) 에 각 comment 붙어 있는 [수정] 버튼 클릭 하면, 밑의 두 가지 정보를 준다.
      // 1. cmt_cont의 내용을 input에 뿌려주기
      $("input[name=cmt_cont]").val(cmt_cont);
      // 2. cmt_no 전달하기
      $("#modBtn").attr("data-cmt_no", cmt_no);
    });

      $("#rvwCmtList").on("click", ".replyBtn", function () {
        // 1. replyForm을 옮기고
        $("#replyForm").appendTo($(this).parent()); // 버튼의 부모 = li 태그, li 태그 뒤에 replyForm 붙임

        // 2. 답글을 입력할 폼을 보여주고,
        $("#replyForm").css("display", "block");
      });

      // $(".delBtn").click(function(){
      $("#rvwCmtList").on("click", ".delBtn", function () {
        let cmt_no = $(this).parent().attr("data-cmt_no");
        let rvw_no = $(this).parent().attr("data-rvw_no");


        $.ajax({
          type: 'DELETE',       // 요청 메서드
          url: '/comments/' + cmt_no + '?rvw_no=' + rvw_no,  // 요청 URI
          success: function (result) {
            alert(result)
            showList(rvw_no);
          },
          error: function () {
            alert("error")
          } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()

      });
    }); // Ajax end

    let toHtml = function (comments) {
      let tmp = "<ul>";

      comments.forEach(function (comment) {
        tmp += '<li data-cmt_no=' + comment.cmt_no
        tmp += ' data-pcmt_no=' + comment.pcmt_no
        tmp += ' data-rvw_no=' + comment.rvw_no + '>'
      if(comment.cmt_no!=comment.pcmt_no)
        tmp += 'ㄴ'
        tmp += ' usr_nm=<span class="usr_nm">' + comment.usr_nm + '</span>'
        tmp += ' cmt_cont=<span class="cmt_cont">' + comment.cmt_cont + '</span>'
        tmp += ' mdf_date=' + comment.mdf_date
        tmp += '<button class="delBtn">삭제</button>'
        tmp += '<button class="modBtn">수정</button>'
        tmp += '<button class="replyBtn">답글</button>'
        tmp += '</li>'
      })
      return tmp + "</ul>";
    }
</script>
</body>
</html>