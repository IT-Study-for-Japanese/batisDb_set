
$(document).ready(function () {
    eventHandler();
    
     $(".reserve-go").click(function () { //예약 처리 ajax
		
        var formDataArray = $("#reserveForm").serializeArray();
        var formDataObject = {};
		
        $.each(formDataArray, function(i, item) {
            formDataObject[item.name] = item.value;
        });
        
        $.ajax({
            url: "reserveAjaxTest.do",// 서버에서 데이터를 가져올 URL
            type: "POST",
            contentType :"application/json",
            data: JSON.stringify(formDataObject),  
            success: function (response) {
            	if (response) {
                alert("예약 완료되었습니다.");
                location.reload();
			} else {
				alert("이미 예약하신 내역이 존재 합니다.\n * 1인 1대의 예약만 가능합니다. *");
				 location.reload();
			}

            },
            error: function (e) {
                console.error(e);
            }
        });
    
    });
    
    
});


function eventHandler() { //리스트 검색 ajax
	
	$(".search-input").keydown(function (e) {
        if (e.keyCode === 13) { 
            e.preventDefault(); // 기본 동작 중지 (페이지 새로고침 방지)
            $(".search-button").click(); // 검색 버튼 클릭 이벤트 발생
        }
    });
	
	
    $(".search-button").click(function () {

        var formDataArray = $("#form2").serializeArray();//
        var formDataObject = {};

        $.each(formDataArray, function(i, item) {
            formDataObject[item.name] = item.value;
        });
		
        $.ajax({
            url: "search.do",// 서버에서 데이터를 가져올 URL
            type: "POST",
            contentType :"application/json",
            data: JSON.stringify(formDataObject),   
            success: function (response) {
                updateTable(response);
            },
            error: function (e) {
                console.error(e);
            }
        });
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

}

function updateTable(data) { //검색 리스트 띄우기

    const tbody = $("table tbody"); //table요소 <tbody>선택
    tbody.empty();
	
    $.each(data, function (index, rent) {
            var row = $("<tr>");
            row.append($("<td>").text(rent.reservePlaceName));
            row.append($("<td>").text(rent.reservePlaceAddr));
            row.append($("<td>").text(rent.count));
            
            var hiddenTd = $("<td>")
            .css("display", "none") // Set inline style
            .text(rent.reservePlaceId); // Set the content
        	row.append(hiddenTd);
            
            if (rent.count > 0) {
            var reserveButton = $("<button>")
                .text("예약")
                .addClass("reserve-button")
                .click(function () {
                    // 예약 버튼 클릭 시 동작 추가
                    
                    var row = $(this).closest("tr");
               		var reservePlaceIdInform = row.find("td:hidden").text();
               		var reservePlaceName = row.find("td:eq(0)").text();
               		
               		$("#reservePlaceName").val(reservePlaceName);
               		$("#reservePlaceNameCell").text(reservePlaceName);
               		$("#reservePlaceIdInform").val(reservePlaceIdInform);
               		reservePopup();
                })
                .attr("onclick", "reservePopup()");
            row.append($("<td>").append(reserveButton));
        } else {
            var disabledButton = $("<button>")
                .text("예약불가")
                .addClass("reserve-button-disabled")
                .prop("disabled", true); // 버튼 비활성화 처리
            row.append($("<td>").append(disabledButton));
        }
            tbody.append(row);
        });
}
function reservePopup2(pop) { //예약 팝업창 띄우기
	//$("#myModal").css("display", "block"); //효과없이 띄우기
	$("#reservePlaceIdInform").val($(pop).prev().val());
	$("#myModal").fadeIn(); //부드럽게 띄우기
	
	var reservePlaceName = $(pop).closest('.rental-info').find('.reserve-place-name-td').text();
    $("#reservePlaceNameCell").text(reservePlaceName);
}

function reservePopup() { //예약 팝업창 띄우기
	//$("#myModal").css("display", "block"); //효과없이 띄우기
	$("#myModal").fadeIn(); //부드럽게 띄우기
	
}
$(document).ready(function () { //예약정보 팝업창에 전달
    $(".reserve-button").click(function () {
    	
        var reservePlaceIdInform = $(this).closest("tr").find("td:eq(2)").text();
        $("#reservePlaceIdInform").val(reservePlaceIdInform); // reservePlaceId 값을 숨겨진 필드에 설정
    
    	var reservePlaceName= $(this).closest("tr").find("td:eq(0)").text();
        $("#reservePlaceName").val(reservePlaceName);
    	$("#reservePlaceNameCell").text(reservePlaceName); //스판안에 대여소 이름 입력
    	
    });
});

