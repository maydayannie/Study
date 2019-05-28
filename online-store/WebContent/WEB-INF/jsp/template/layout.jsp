<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<%-- <script src='<c:url value="res/js/jquery-3.4.1.min.js"/>' type="javascript/text"></script> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- <script crossorigin src="https://unpkg.com/react@16/dist/react.min.js"></script> -->
<!-- <script crossorigin src="https://unpkg.com/react-dom@16/dist/react-dom.min.js"></script> -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<style>
#header-div {
	background-color: #4286f4;
}
#menu-div {
	background-color: #cfff96;
}
#body-div {
	background-color: #ffef96;
}
#foot-div {
	background-color: #f4ca41;
}
</style>

</head>
<body>
	<div class="container-fluid">
		<div class="row text-center">
			<div class="col-12"  id="header-div">
				<tiles:insertAttribute name="header" />
			</div>
		</div>
		<div class="row" >
			<div class="col-2" id="menu-div">
				<tiles:insertAttribute name="menu" />
			</div>
			<div class="col-10" id="body-div">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<div class="row text-center" >
			<div class="col-12" id="foot-div">
				<tiles:insertAttribute name="foot" />
			</div>
		</div>
	</div>
</body>
</html>