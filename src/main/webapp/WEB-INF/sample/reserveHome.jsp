<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>       
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>대여소지도</title>

    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=bazkpg4jtl"></script> <!-- 네이버지도api설정  -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.7.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/rent.js"></script>
	<script src="${pageContext.request.contextPath}/js/map.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rent.css" /> <!-- css 파일 -->

</head>                             
<body>
<div id="map" style="width: 100%; height: 880px"></div> <!-- 지도 div 크기 설정  -->

	<div class="scrolle">
 	
 		<form id="form2">
				<input name="reservePlaceName" class="search-input" type="text"><input
					class="search-button" type="button" value="검색">
		</form>

 	<table><!-- 목록창 -->
        <thead>
            <tr>
                <th>주소</th>
                <th>대여소명</th>
                <th>대여가능대수</th>
            	<th>예약</th>
            </tr>
        </thead>
	
	<tbody>
        <c:forEach items="${rentList}" var="res"> <!--  -->
				<tr>
					<td>${res.reservePlaceName}</td>
					<td>${res.reservePlaceAddr}</td>
					<td>카운트 구현필요</td>
					<td><button class="reserve-button">예약</button></td>
				</tr>
		</c:forEach>

        </tbody>
    </table>

	</div>

	<div id="myModal" class="modal">
	  	<div class="modal-content">
		    <span class="close">&times;</span>
		    <!-- 팝업 내용을 추가하세요 -->
		    <h2>예약 하기</h2>
		    <!-- 예약에 관한 내용을 표시할 부분 -->
			<select>
				<option value="1">1시간</option>
				<option value="2">2시간</option>
				<option value="3">3시간</option>
			</select>
		    <button>예약하기</button>
		    
  		</div>
	</div>

</body>
</html>