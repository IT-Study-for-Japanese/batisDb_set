
$(document).ready(function () {

    $(".search-button").click(function () {
		
        var formDataArray = $("#form2").serializeArray();
        var formDataObject = {};
		
        $.each(formDataArray, function(i, item) {
            formDataObject[item.name] = item.value;
        });
		alert(formDataObject);
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
}); 