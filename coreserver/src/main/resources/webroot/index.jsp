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
	</head>
	
	<body>
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
			    <div class="navbar-header">
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			      <a class="navbar-brand" href="#">ConnectLife</a>
			    </div>
			
				<!-- Collect the nav links, forms, and other content for toggling -->
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li class="active"><a href="#">Dashboard <span class="sr-only">(current)</span></a></li>
			        <li><a href="#"><span class="glyphicon glyphicon-bell" aria-hidden="true"></span><span class="badge">4</span></a></li>
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Settings <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a href="#">General</a></li>
			            <li><a href="#">Something else here</a></li>
			          </ul>
			        </li>
			      </ul>
			      <form class="navbar-form navbar-right">
			        <div class="form-group">
			          <input type="text" class="form-control" placeholder="Login">
			          <input type="password" class="form-control" placeholder="Password">
			        </div>
			        <button type="submit" class="btn btn-default">Sign In</button>
			      </form>
			    </div><!-- /.navbar-collapse -->
		    </div> <!-- /.container-fluid -->
		</nav>
		<nav class="navbar navbar-default navbar-fixed-bottom">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
			    <div class="navbar-header navbar-right">
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			      <a class="navbar-text" href="#">ConnectLife 1.0.0.0</a>
			    </div>
			</div> <!-- /.container-fluid -->
		</nav>
		
		<div class="container theme-showcase" role="main">
			<div class="jumbotron">
			  <h1>ConnectLife</h1>
			  <p>Home automation system. Please enter your user name and <br>password to continue.</p>
			  <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
			</div>
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	   	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	   	<!-- Include all compiled plugins (below), or include individual files as needed -->
	   	<script src="js/bootstrap.min.js"></script>
	</body>
	
</html>