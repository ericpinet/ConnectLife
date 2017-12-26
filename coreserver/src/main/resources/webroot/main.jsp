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
	
<%
	//Check if the session is valid
	if (session.getAttribute("logged") != "true") {
		// if the user isn't logged
		// open the login_screen
	   	String login_screen = new String("index.jsp");
	   	response.sendRedirect(login_screen);
	   	return;
	}
%>	
	
	<body>
	
		<!-- NAVBAR -->
		<jsp:include page="navbar.jsp" />
		
		<div class="container">

		<% if (request.getParameter("nav") == null) { %>
					
			<jsp:include page="header.jsp" />
			<jsp:include page="panel_system_info.jsp" />
			<jsp:include page="panel_configs.jsp" />
			<jsp:include page="panel_modules.jsp" />
			<br>
					
		<% } else if (request.getParameter("nav").equals("system")) { %>
			
			<br>		
			<jsp:include page="panel_system_info.jsp" />
			<jsp:include page="panel_configs.jsp" />
			<jsp:include page="panel_modules.jsp" />	
			<br>
		
		<% } else if (request.getParameter("nav").equals("logs")) { %>

			<br>
			<jsp:include page="logs.jsp" />
			<br>
	
		<% } else if (request.getParameter("nav").equals("settings")) { %>

			<br>
			<jsp:include page="panel_configs.jsp" />
			<br>
	
		<% } %>
		
	    </div><!-- /.container -->
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	   	<script src="js/jquery-3.1.1.min.js"></script>
	   	<!-- Include all compiled plugins (below), or include individual files as needed -->
	   	<script src="js/bootstrap.min.js"></script>
	   	<script src="js/bootstrap-table.min.js"></script>
	</body>
	
</html>