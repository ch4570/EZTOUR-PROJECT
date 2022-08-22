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
        <div class="foot">
            <div class="foot-menu">
                <a href="">여행정보</a>
                <a href="">홍보센터</a>
                <a href="">여행약관</a>
                <a href="">개인정보처리방침</a>
                <a href="">기업/단체문의</a>
                <a href="">대리점안내</a>
                <a href="">신용카드 무이자 할부안내</a>
                <a href="">EZ Tour 전화번호안내</a>
            </div>
            <div class="foot-Address">
                <dl>
                    <dt>Address & Contact</dt>
                    <dd>
                        "대표번호 : 5555-0000"
                        <br>
                        서울특별시 종로구 청계천로 51-1, 교원투어 빌딩
                    </dd>
                </dl>
            </div>
            <div class="foot-Information">
                <dl>
                    <dt>EzTour Information</dt>
                    <dd>
                        (주)EzTour 대표 : xxx
                        <br>
                        서울특별시 종로구 청계천로 51-1, 교원투어 빌딩
                        <br>
                        본사 사업자등록번호 : 000-0000-0000
                    </dd>
                </dl>
            </div>
            <div class="foot-FollowUs">
                <dl>
                    <dt>Follow Us</dt>
                    <dd>
                        여행자 배상책임보험 20억원 가입
                        <br>
                        일반여행업 보증금외 15억원 가입
                        <br>
                        xxxx@eztour.co.kr
                    </dd>
                </dl>
            </div>
        </div>
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