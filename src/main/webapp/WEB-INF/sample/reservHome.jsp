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
    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=ug0avytojj"></script> <!-- 네이버지도api설정  -->
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-3.7.0.min.js"></script> <!-- jquery 3.7버전 -->
	<script src="${pageContext.request.contextPath}/js/rent.js"></script> <!-- jquery 파일 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rent.css" /> <!-- css 파일 -->
<style>
.modal {
  display: none; /* 클릭전 안보이게한다 */
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: "200px";
  height: "200px";
  /* overflow: auto; */
 
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 50%;
}

/* 닫기 버튼 스타일 */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

</style>

<script type="text/javascript">

$(document).ready(function() {

$(".reserve-button").click(function () {
  // 팝업 열기
  $("#myModal").css("display", "block");
});

// 모달 팝업 닫기
$(".close").click(function () {
  // 팝업 닫기
  $("#myModal").css("display", "none");
});

// 모달 외부 클릭 시 팝업 닫기
$(window).click(function (e) {
  if (e.target == $("#myModal")[0]) {
    // 팝업 닫기
    $("#myModal").css("display", "none");
  }
});
});

</script>
  	
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

<script>

var rentList2 = '${rentList}';

var HOME_PATH = window.HOME_PATH || '.'; //이벤트 실행 요소?
var map = new naver.maps.Map('map', { //맵 위도, 경도 및 크기설정
	center: new naver.maps.LatLng(35.8648, 128.5935),
    zoom: 14
});

//var rentList2 = ${rentList}; 컨트롤러에서 보낸 list, 마크에 쓰려는 자전거대여소 정보인데 지도가 안보임 해결필요....

var latlngArr = []; //마커 위도,경도 
var markerName = []; //마커 이름

latlngArr.push(new naver.maps.LatLng(35.8418, 128.68));//대공원역
latlngArr.push(new naver.maps.LatLng(35.8363, 128.7525));//영남대역
latlngArr.push(new naver.maps.LatLng(35.8569, 128.5553));//두류역
latlngArr.push(new naver.maps.LatLng(35.8043, 128.5005));//화원역
latlngArr.push(new naver.maps.LatLng(35.8792, 128.6273));//동대구역

latlngArr.push(new naver.maps.LatLng(35.8648, 128.5935));//반월당역
latlngArr.push(new naver.maps.LatLng(35.8699, 128.5823));//서문시장역
/* latlngArr.push(new naver.maps.LatLng(35.8751, 128.5954));//대구역 */
latlngArr.push(new naver.maps.LatLng(35.8889, 128.5682));//팔달시장역
latlngArr.push(new naver.maps.LatLng(35.8374, 128.5572));//서부정류장역
latlngArr.push(new naver.maps.LatLng(35.8459, 128.625));//어린이회관역

markerName.push("대공원역"); //마커 이름등록필요
markerName.push("영남대역");
markerName.push("두류역");
markerName.push("화원역");
markerName.push("동대구역");
markerName.push("반월당역");
markerName.push("서문시장역");
markerName.push("팔달시장역");
markerName.push("서부정류장역");
markerName.push("어린이회관역");

var contentString = [ // 마커 표시창 문구 
    '<div class="iw_inner">',
    '   <h4>대공원역대여소</h4>',
    '   <p>대여가능대수  : 10 <br />',
    '   </p>',
    '<button>대여하기</button>',
    
    
    '</div>'
].join(''); 

var infowindow = new naver.maps.InfoWindow({ //정보표시창
    content: contentString
});

var markers = []; //마커 배열


for(var i=0; i<latlngArr.length; i++){ //마커 등록
	
	markers.push(new naver.maps.Marker({
	    map: map,
	    title: markerName[i],
	    position: latlngArr[i]
	    
	}));	
	
}

for(let i=0; i<markers.length; i++){ //마커 띄우기 
    (function(index){
	naver.maps.Event.addListener(markers[index], "click", function(e) {
        
    	if (!infowindow.getMap() || infowindow.marker !== markers[index]) { //클릭되지않은 마커 안내창연다.
            
    		contentString = [ 
    		    '<div class="iw_inner">',
    		    '   <h4>대여소명 : ' + rentList2[index].reservePlaceName + '</h4>',
    		    '   <p>대여가능대수  : 10(카운트구현필요) <br />',
    		    '   </p>',
    		    '<button>대여하기</button>',
    		    '</div>'
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

naver.maps.Event.addListener(map, "click", function (e) { //지도 클릭하면 마커안내창닫음
    infowindow.close();
});

</script>
</body>
</html>