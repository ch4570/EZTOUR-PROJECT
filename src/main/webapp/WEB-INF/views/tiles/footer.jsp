<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
    <body>

        <div class="aside">
            <button class="aside__btn" name="asideBtn">
                <em><i class="fas fa-ellipsis-v"></i></em>
            </button>
            <div class="aside__menu">
                <ul class="aside__menu--list">
                    <li>
                        <a class="aside__link" href="#">
                            <span><i class="fas fa-history"></i></span>
                            <span>최근 본 상품</span>
                        </a>
                    </li>
                    <li>
                        <a class="aside__link" href="#">
                            <span><i class="far fa-clone"></i></span>
                            <span>상품 비교함</span>
                        </a>
                    </li>
                    <li>
                        <a class="aside__link" href="#">
                            <span><i class="far fa-heart"></i></span>
                            <span>관심상품</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="foot">FOOTER</div>


    <script>
        $(document).ready(function () {
            // 자세히보기 버튼 클릭 시 클래스 'active' 추가
            $('button[name="asideBtn"]').on('click', function () {
                $(".aside").toggleClass('open');
                $(".aside__btn").toggleClass('open');

            })
        });
    </script>
    </body>
</html>