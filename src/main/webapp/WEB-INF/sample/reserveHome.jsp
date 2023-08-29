<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>대여소지도</title>
<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${mapKey}"></script> <!-- 네이버지도api설정  -->
<!-- 네이버지도api설정  -->
<script src="${pageContext.request.contextPath}/js/lib/jquery/jquery-3.7.0.min.js"></script> <!-- jquery 파일 -->
<script src="${pageContext.request.contextPath}/js/service/rent.js"></script><!-- jsp파일 -->
<%-- <script src="${pageContext.request.contextPath}/js/components/public/map.js"></script> map파일 작동안함 --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/custom/rent.css" /><!-- css 파일 -->

<style>
.modal { /* 팝업창 css파일에 옮기면 작동안함 */
	display: none; /* 클릭전 안보이게한다 */
	position: fixed;
	z-index: 1;
	left: 40%;
	top: 20%;
	width: "200px";
	height: "200px";
	/* overflow: auto; */
}

.rental-info {
	animation: fadein 1s;
	-moz-animation: fadein 1s; /* Firefox */
	-webkit-animation: fadein 1s; /* Safari and Chrome */
	-o-animation: fadein 1s; /* Opera */
}

@keyframes fadein {
	from { 
		opacity:0;
	}
	to {
		opacity: 1;
	}
}

@-moz-keyframes fadein { /* Firefox */
	from {
		opacity:0;
	}
	to {
		opacity: 1;
	}
}

@-webkit-keyframes fadein { /* Safari and Chrome */
	from {
		opacity:0;
	}
	to {
		opacity: 1;
	}
}

@-o-keyframes fadein { /* Opera */
	from {
		opacity:0;
	}
	to {
		opacity: 1;
	}
}
</style>

</head>
<body>
	<div id="map" style="width: 100%; height: 880px"></div>
	<!-- 지도 div 크기 설정  -->

	<div class="scrolle">

		<form id="form2">
			<input name="reservePlaceName" class="search-input" type="text" placeholder="주소 또는 대여소 이름을 입력하세요"><input
				class="search-button" type="button" value="검색">
		</form>

		<table>
			<!-- 목록창 -->
			<thead>
				<tr>
					<th>대여소명</th>
					<th>주소</th>
					<th>대여가능대수</th>
					<th>예약</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach items="${rentList}" var="res" varStatus="loop">
					<!--  -->
					<tr>
						<td class="clickable" data-index="${loop.index}">${res.reservePlaceName}</td>
						<td class="clickable" data-index="${loop.index}">${res.reservePlaceAddr}</td>
						<td style="display: none">${res.reservePlaceId}</td>
						<td>${res.count}</td>
						<td><c:choose>
								<c:when test="${res.count == 0}">
									<button class="reserve-button-disabled" disabled>예약불가</button>
								</c:when>

								<c:otherwise>
									<button class="reserve-button" onclick="reservePopup()">예약</button>
								</c:otherwise>
							</c:choose></td>

					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>
	<form id="reserveForm">
		<div id="myModal" class="modal">
			<div class="modal-content">
				<span class="close">&times;</span>
				<!-- 팝업 내용을 추가하세요 -->
				<h2>예약 하기</h2>
				<input type="hidden" id="reservePlaceIdInform"
					name="bikeReservePlaceId">
				<!-- 선택한 대여소id -->
				<!-- 예약에 관한 내용을 표시할 부분 -->
				<select class="selectTime" name="textPeriod">
					<option value="01:00:00">1시간</option>
					<option value="02:00:00">2시간</option>
					<option value="03:00:00">3시간</option>
				</select>
				<input class="reserve-go" type="button" value="예약하기">
			</div>
		</div>
	</form>

	<script> /* map파일은 작동을 안함.... */
	
		
		var rentList = JSON.parse('${jsonPlacelist}');

		var HOME_PATH = window.HOME_PATH || '.'; //이벤트 실행 요소?
		var map = new naver.maps.Map('map', { //맵 위도, 경도 및 크기설정
			center : new naver.maps.LatLng(35.8648, 128.5935),
			zoom : 14
		});

		

		var latlngArr = []; //마커 위도,경도 
		var markerName = []; //마커 이름
		
		latlngArr.push(new naver.maps.LatLng(35.8569, 128.5553));//두류역
		latlngArr.push(new naver.maps.LatLng(35.8043, 128.5005));//화원역
		latlngArr.push(new naver.maps.LatLng(35.8792, 128.6273));//동대구역
		latlngArr.push(new naver.maps.LatLng(35.8648, 128.5935));//반월당역
		latlngArr.push(new naver.maps.LatLng(35.8459, 128.625));//어린이회관역
		latlngArr.push(new naver.maps.LatLng(35.8363, 128.7525));//영남대역
		latlngArr.push(new naver.maps.LatLng(35.8418, 128.68));//대공원역
		latlngArr.push(new naver.maps.LatLng(35.8374, 128.5572));//서부정류장역
		latlngArr.push(new naver.maps.LatLng(35.8889, 128.5682));//팔달시장역
		latlngArr.push(new naver.maps.LatLng(35.8699, 128.5823));//서문시장역

		/* latlngArr.push(new naver.maps.LatLng(35.8751, 128.5954));//대구역 */
		
		markerName.push("두류역");
		markerName.push("화원역");
		markerName.push("동대구역");
		markerName.push("반월당역");
		markerName.push("어린이회관역");
		markerName.push("영남대역");
		markerName.push("대공원역"); 
		markerName.push("서부정류장역");
		markerName.push("팔달시장역");
		markerName.push("서문시장역");

		var contentString = [ // 마커 표시창 문구 

		].join('');

		var infowindow = new naver.maps.InfoWindow({ //정보표시창
			content : contentString
		});

		var markers = []; //마커 배열

		for (var i = 0; i < latlngArr.length; i++) { //마커 등록

			markers.push(new naver.maps.Marker({
				map : map,
				title : markerName[i],
				position : latlngArr[i]

			}));

		}
		
		$(".clickable").on("click", function(e) { //클릭시 지도 화면 이동
		    
			var index =$(this).data("index");
			
			e.preventDefault();

		    map.panTo(latlngArr[index]);
		    map.setZoom(15);
		});
		
		
		
		for (let i = 0; i < markers.length; i++) { //마커 띄우기 
			(function(index) {
				naver.maps.Event.addListener(markers[index], "click", function(
						e) {

					if (!infowindow.getMap()
							|| infowindow.marker !== markers[index]) { //클릭되지않은 마커 안내창연다.

						contentString = [
						    '<div class="rental-info">', // 대여 정보 컨테이너 열기
						    '   <h4>대여소명 : ' + rentList[index].reservePlaceName + '</h4>',
						    '   <p>대여가능대수 : ' + rentList[index].count + ' <br />',
						    '   <input type="hidden" class="reserve-place-id" value="' + rentList[index].reservePlaceId + '">', // 숨겨진 데이터 추가
						    '   <button class="reserve-button-map" ' + (rentList[index].count > 0 ? 'onclick="reservePopup2(this)"' : 'disabled') + '>'
						    + (rentList[index].count > 0 ? '대여하기' : '예약불가') + '</button>',
						    '</div>' // 대여 정보 컨테이너 닫기
						].join('');
						
							
						infowindow.setContent(contentString);

						infowindow.open(map, markers[index]);
					} else {
						infowindow.close();
					}
					infowindow.marker = markers[index];
				});
			})(i);
		}

		naver.maps.Event.addListener(map, "click", function(e) { //지도 클릭하면 마커안내창닫음
			infowindow.close();
		});
	</script>
</body>
</html>