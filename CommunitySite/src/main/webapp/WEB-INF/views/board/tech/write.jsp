<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- include libraries(jQuery, bootstrap) -->
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/editor/summernote.css" />
<script src="${pageContext.request.contextPath }/resources/editor/summernote.js" ></script>

</head>
<body>

<h2>Tech 작성</h2>
<div style="width: 70%;">
	<form action="${pageContext.request.contextPath }/tech/writeOk" method="post" onsubmit="return formCheck();">
		<div class="form-group">
	     <input type="text" class="form-control" id="title" name="title" >
	   </div>
		<div style="margin-top: 15px;">
			<textarea id="summernote" name="content" ></textarea>
		</div>
		<div align="right">
			<input class="btn btn-light" type="button" value="취소" />
			<input class="btn btn-light" type="submit" value="글등록" />
		</div>
	</form>
</div>

<script>
function formCheck() {
	var title = $("#title").val();
	title = ConvertSystemSourcetoHtml(title);
	$("#title").val(title);

	if( title.trim().length === 0 ) {
		return false;
	}
	
	return true;
}

function ConvertSystemSourcetoHtml(str){
	str = str.replace(/</g,"&lt;");
	str = str.replace(/>/g,"&gt;");
	str = str.replace(/\"/g,"&quot;");
	str = str.replace(/\'/g,"&#39;");
	str = str.replace(/\n/g,"<br />");
	
	return str;
}

$(function() {
	$('#summernote').summernote({
	  // 에디터 높이
	  height: 550,
	  // 에디터 한글 설정
	  lang: "ko-KR",
	  // 에디터에 커서 이동 (input창의 autofocus라고 생각하시면 됩니다.)
	  focus : true,
	  toolbar: [
		    // 글꼴 설정
		    ['fontname', ['fontname']],
		    // 글자 크기 설정
		    ['fontsize', ['fontsize']],
		    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		    // 글자색
		    ['color', ['forecolor','color']],
		    // 표만들기
		    ['table', ['table']],
		    // 글머리 기호, 번호매기기, 문단정렬
		    ['para', ['ul', 'ol', 'paragraph']],
		    // 줄간격
		    ['height', ['height']],
		    // 그림첨부, 링크만들기, 동영상첨부
		    ['insert',['picture','link','video']],
		    // 코드보기, 확대해서보기, 도움말
		    ['view', ['codeview','fullscreen', 'help']]
		  ],
		  // 추가한 글꼴
		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
		 // 추가한 폰트사이즈
		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
  	});
});


</script>
</body>
</html>