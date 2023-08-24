<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/pro2/js/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		/* $('#btn_register').click(function() {
			var sendData = $('#form_test').serialize();
			alert(sendData);

			$.ajax({
				url : "/pro2/insertTest.do",
				type : "POST",
				data : sendData,
				dataType : "text",
				success : function(data) {
					if (data == "ok") {
						alert('저장성공!');
						location.href = "/pro3/list.do";
					} else {
						alert('저장실패!');
					}
				},
				error : function() {
					alert('오류발생!');
				}
			});

		}); */
	});
</script>
</head>
<body>
 <form id="form_test" action="insertTest.do" method="post" encType="multipart/form-data">
            <table class="table table-bordered">
                <tbody>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" placeholder="제목을 입력하세요." name="testTitle" class="form-control" /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea placeholder="내용을 입력하세요 ." name="testContent" class="form-control" style="height: 200px;"></textarea></td>
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td><input type="file" name="uploadFile"></td>    
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button id="btn_register" type="submit" class="btn_register">등록</button>
                            <button id="btn_previous" type="button" class="btn_previous">이전</button>
                    </tr>
 
                </tbody>
            </table>
        </form>

</body>
</html>