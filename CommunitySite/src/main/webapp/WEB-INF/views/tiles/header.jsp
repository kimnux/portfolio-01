<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<span id="login_id"></span>
	<span id="login"> </span>
	<span id="join"> </span>
	
<script type="text/javascript">
$(function () {
	
	var user_info = '${sessionScope.user_info}';
	if( user_info.length > 0 || '${userInfo }'.length > 0 ) {
		if( user_info.length > 0 ) {
			$("#login_id").html(user_info.userId); // 일반 로그인시
			$("#login").html('<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>');
		}else {
			// 카카오 로그인시
			$("#login_id").html('${userInfo.nickname }'); 
			$("#login").html('<a href="${pageContext.request.contextPath}/logout">로그아웃</a>');
		}
	} else {
		$("#login").html('<a href="${pageContext.request.contextPath}/member/login">로그인</a>');
		$("#join").html('<a href="${pageContext.request.contextPath}/member/joinPage">회원가입</a>');
	}
	
	
});
</script>
</body>
</html>