
$(document).ready(function () {
    eventHandler();
});

function eventHandler() {

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

function updateTable(data) {

    const tbody = $("table tbody"); //table요소 <tbody>선택
    tbody.empty();

    $.each(data, function (index, rent) {
            var row = $("<tr>");
            row.append($("<td>").text(rent.reservePlaceName));
            row.append($("<td>").text(rent.reservePlaceAddr));
            row.append($("<td>").text(rent.count));
            if (rent.count > 0) {
            var reserveButton = $("<button>")
                .text("예약")
                .addClass("reserve-button")
                .click(function () {
                    // 예약 버튼 클릭 시 동작 추가
                    alert("예약 버튼 클릭");
                });
            row.append($("<td>").append(reserveButton));
        } else {
            var disabledButton = $("<button>")
                .text("예약불가")
                .addClass("reserve-button-disabled")
                .prop("disabled", true); // 버튼 비활성화 처리
            row.append($("<td>").append(disabledButton));
        }
            tbody.append(row);
        }
    );
}

function reservePopup() {
	$("#myModal").css("display", "block");
}
$(document).ready(function () {
    $(".reserve-button").click(function () {
    	
        var reservePlaceIdInform = $(this).closest("tr").find("td:eq(2)").text();
        $("#reservePlaceIdInform").val(reservePlaceIdInform); // reservePlaceId 값을 숨겨진 필드에 설정
    });
});



