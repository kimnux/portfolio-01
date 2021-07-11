<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#header {
	width: 100%;
	height: 50px;
	text-align: center;
	background-color: aqua;
}

#menu {
	float: left;
	width: 15%;
	background-color: gray;
}

#content {
	float: left;
	width: 85%;
	background-color: lime;
}

#footer {
	width: 100%;
	height: 50px;
	text-align: center;
	background-color: orange;
	clear: both;
}

#menu, #content {
	min-height: 600px;
}
</style>
</head>
<body>
	<div style="width:100%; height:100%;">
	    <div id="header"><tiles:insertAttribute name="header" /></div>
	    <div id="menu"><tiles:insertAttribute name="menu" /></div>
	    <div id="body"><tiles:insertAttribute name="body" /></div>    
	    <div id="footer"><tiles:insertAttribute name="footer" /></div>
	</div>
</body>
</html>