
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

}

function updateTable(data) {

    const tbody = $("table tbody"); //table요소 <tbody>선택
    tbody.empty();

    $.each(data, function (index, rent) {
            var row = $("<tr>");
            row.append($("<td>").text(rent.reservePlaceName));
            row.append($("<td>").text(rent.reservePlaceAddr));
            row.append($("<td>").text("카운트 구현필요"));
            tbody.append(row);
        }
    );
}