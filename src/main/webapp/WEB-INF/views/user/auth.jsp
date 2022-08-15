<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<button>휴대폰 본인인증 하기</button>


<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-{SDK-최신버전}.js"></script>
<script>
    var IMP = window.IMP; // 생략 가능
    IMP.init("{가맹점 식별코드}"); // 예: imp00000000
</script>
</body>
</html>
