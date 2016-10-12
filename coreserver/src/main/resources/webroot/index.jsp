<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>ConnectLife</title>
	
		<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		
		 <!-- Custom styles for this template -->
    	<link href="css/connectlife.css" rel="stylesheet">
    	
    	<!-- For loading font -->
    	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
		
	</head>
	
	<body>
		<!-- NAVBAR -->
		<jsp:include page="jsp/navbar.jsp" />
		
		<div class="container">
		
			<!-- HEADER -->
			<jsp:include page="jsp/header.jsp" />
		
			<!-- SERVICE STATUS -->
			<jsp:include page="jsp/panel_services.jsp" />
		
	    </div><!-- /.container -->
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	   	<script src="js/jquery-3.1.1.min.js"></script>
	   	<!-- Include all compiled plugins (below), or include individual files as needed -->
	   	<script src="js/bootstrap.min.js"></script>
	   	<script src="js/bootstrap-table.min.js"></script>
	</body>
	
</html>