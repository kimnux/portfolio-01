<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
	<form name="form" action="${pageContext.request.contextPath }/tech/writeOk" method="post" onsubmit="return formCheck();">
		<div class="form-group">
	     <input type="text" class="form-control" id="title" name="title" value="${detail.title }">
	   </div>
		<div style="margin-top: 15px;">
			<textarea id="summernote" name="content" ><c:out value="${detail.content }" /></textarea>
		</div>
		<div align="right">
			<input class="btn btn-light" type="button" value="취소" />
			<c:choose>
				<c:when test="${flag eq 'edit' }">
					<input type="hidden" name="idx" value="${detail.idx }" />
					<input class="btn btn-light" type="button" onclick="editOk()" value="글수정" />
				</c:when>
				<c:otherwise>
					<input class="btn btn-light" type="submit" value="글등록" />
				</c:otherwise>
			</c:choose>
			
		</div>
	</form>
</div>

<script>

function editOk() {
	var form = document.form;
	form.action = "${pageContext.request.contextPath}/tech/editOk";
	form.submit();
}

function formCheck() {
	var title = $("#title").val();
	
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
	  codeviewFilter: false,
	  codeviewIframeFilter: false,
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
		// onImageUpload callback
		callbacks: {
		    onImageUpload: function(files) {
		    	console.log("callbacks files ======> ", files);
		    },
		    onPaste: function (e) {
				var clipboardData = e.originalEvent.clipboardData;
				console.log("clipboardData =====>",clipboardData);
				if (clipboardData && clipboardData.items && clipboardData.items.length) {
					var item = clipboardData.items[0];
					if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
						e.preventDefault();
					}
				}
			}
		  },
		codeviewFilter: true,
	  	codeviewIframeFilter: true
  	});

	// summernote.image.upload
	$('#summernote').on('summernote.image.upload', function(we, files) {
		console.log("we ======> ", we);
		console.log("files ======> ", files);
		console.log("this =======> ",this);
		
		var data = new FormData();
		data.append("file", files[0]);
		console.log('data ======++> ', files[0]);
		$.ajax({
			data : data,
			type : "POST",
			url : "${pageContext.request.contextPath}/uploadSummernoteImageFile",
			contentType : false,
			processData : false,
			success : function(data) {
				console.log('success : ',data);
	        	//항상 업로드된 파일의 url이 있어야 한다.
				//$(this).summernote('insertImage', data.url);
				$('#summernote').summernote('insertImage', data.url);
			},
			error:function(request,status,error){
			    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		   }
		});
	});

});



</script>
</body>
</html>