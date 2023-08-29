
$(document).ready(function() {
    initialize();
    eventHandler();
    
    $(".clickable").on("click", function(e) { //클릭시 지도 화면 이동
		    
			var index =$(this).data("index");
			
			e.preventDefault();

		    map.panTo(latlngArr[index]);
		});
    
});

function initialize() {

    var rentList = JSON.parse('${jsonPlacelist}'); //model에 등록한 리스트 가져오기

    var HOME_PATH = window.HOME_PATH || '.'; //이벤트 실행 요소?
    var map = new naver.maps.Map('map', { //맵 위도, 경도 및 크기설정
        center: new naver.maps.LatLng(35.8648, 128.5935),
        zoom: 14
    });

//var rentList2 = ${rentList}; 컨트롤러에서 보낸 list, 마크에 쓰려는 자전거대여소 정보인데 지도가 안보임 해결필요....

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

	var contentString = [ // 마커 표시창 문구 선언

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
						    '   <button class="reserve-button-map" onclick="reservePopup()">대여하기</button>',
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

    naver.maps.Event.addListener(map, "click", function (e) { //지도 클릭하면 마커안내창닫음
        infowindow.close();
    });

}
