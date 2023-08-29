
$(document).ready(function () {
    eventHandler();
    
     $(".reserve-go").click(function () {
		
        var formDataArray = $("#reserveForm").serializeArray();
        var formDataObject = {};
		
        $.each(formDataArray, function(i, item) {
            formDataObject[item.name] = item.value;
        });
        
        $.ajax({
            url: "reserveAjaxTest.do",// 서버에서 데이터를 가져올 URL
            type: "POST",
            contentType :"application/json",
            data: JSON.stringify(formDataObject),   // 화 다.
            success: function (response) {
                alert("예약 완료되었습니다.");
                location.reload();
            },
            error: function (e) {
                console.error(e);
            }
        });
    
    });
    
    
    
});


function eventHandler() { //리스트 검색 ajax

    $(".search-button").click(function () {

        var formDataArray = $("#form2").serializeArray();
        var formDataObject = {};

        $.each(formDataArray, function(i, item) {
            formDataObject[item.name] = item.value;
        });

        $.ajax({
            url: "search.do",// 서버에서 데이터를 가져올 URL
            type: "POST",
            contentType :"application/json",
            data: JSON.stringify(formDataObject),   // 화 다.
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

function reservePopup(pop) { //예약 팝업창 띄우기
	//$("#myModal").css("display", "block"); //효과없이 띄우기
	$("#reservePlaceIdInform").val($(pop).prev().val());
	$("#myModal").fadeIn(); //부드럽게 띄우기
	
}
