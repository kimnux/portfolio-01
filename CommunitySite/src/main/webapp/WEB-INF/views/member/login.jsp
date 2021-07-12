<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="/member/loginOk" method="POST">
			<div>
				<label>ID</label>
				<input type="text" name="userId" >		
			</div>
			<div>
				<label>password</label>
				<input type="password" name="password" >
			</div>
			<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>