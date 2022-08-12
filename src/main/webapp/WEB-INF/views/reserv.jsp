<%--
  Created by IntelliJ IDEA.
  User: hka
  Date: 2022-08-12
  Time: 오전 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>상품 예약하기</h1>
<p>예약하시면 빠른 시간 내에 담당 직원이 연락드리겠습니다.</p><br>
<div>
    <dl>
        <dt>행사코드</dt>
        <dd>상품상세코드입력</dd><!-- $ {rid.prdDtlCd}-->
    </dl>
    <div>여행상품명</div><!--$ {rid.prdNm}-->
</div>
<div>
    약관동의
</div>
<div>
    <h2>예약정보</h2>
    <div>
        <table>
            <colgroup>
                <col>
            </colgroup>
            <tr>
                <th>상품명</th>
                <td>${rid.prdDtlCd}</td>
            </tr>
            <tr>
                <th>이용항공</th>
                <td>${''}</td>
            </tr>
            <tr>
                <th>여행기간</th>
                <td>${''}</td>
            </tr>
            <tr>
                <th>일정</th>
                <td>추후구현</td>
            </tr>
            <tr>
                <th>성인요금</th>
                <td>${''}원[만12세 이상](기본상품가:${''}원, 유류할증료:0원, 제세공과금0원)</td>
            </tr>
            <tr>
                <th>아동요금</th>
                <td>${''}원 [만 12세 미만](기본상품가:${''}원, 유류할증료: 0원,제세공과금 0원)</td>
            </tr>
            <tr>
                <th>유아요금</th>
                <td>${''}원 [24개월 미만](기본상품가:${''}원,유류할증료:0원,제세공과금 0원)</td>
            </tr>
        </table>
    </div>
    <div>
        <h2>예약자 정보</h2>
        <div>
            <form>
                <table>
                    <colgroup><col></colgroup>
                    <tr>
                        <th>대표 예약자명</th>
                        <td><input type="text" name="mnRsvtNm" value=""></td>
                    </tr>
                    <tr>
                        <th>휴대폰 번호</th>
                        <td><input type="text" value="" name="phn" placeholder="01012341234"></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="text" name="emailFirst" value="">@<input type="text" name="emailLast" value=""></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div>
        <h2>여행자정보</h2>
        <div>
            <dl>
                <dt>성인</dt>
                <dd>
                    <p>만 12세 이상</p>
                    <input type="text" name="adtCnt" value="">
                    <p>성인요금 입력</p>
                </dd>
            </dl>
            <dl>
                <dt>아동</dt>
                <dd>
                    <p>만 12세 미만</p>
                    <input type="text" name="chdCnt" value="">
                    <p>아동요금 입력</p>
                </dd>
            </dl>
            <dl>
                <dt>유아</dt>
                <dd>
                    <p>만 2세 이상</p>
                    <input type="text" name="bbCnt" value="">
                    <p>유아요금 입력</p>
                </dd>
            </dl>
        </div>
    </div>
    <div>
        <h2>기타 요청사항</h2>
        <div>
            <textarea placeholder="1000자 이내로 입력해 주시기 바랍니다."></textarea>
        </div>
    </div>
</div>

</body>
</html>
