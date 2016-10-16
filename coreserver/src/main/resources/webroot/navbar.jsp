<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/navbar.js"></script>

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
	        <li class="active"><a href="#"><span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span> Dashboard <span class="sr-only">(current)</span></a></li>
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-wrench Settings" aria-hidden="true"></span> Settings<span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">General</a></li>
	            <li><a href="#">Something else here</a></li>
	          </ul>
	        </li>
	        <li class="inactive"><a href="#"><span class="glyphicon glyphicon-bell" aria-hidden="true"></span></a></li>
	        <li class="active"></li>
	      </ul>
	      
	      <ul class="nav navbar-nav pull-right">
	        <li class="inactive">
	          <a href="#" id="btn-logout" role="button" aria-expanded="false"> Logout</a>
	        </li>
	      </ul>
	      
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

<div id = "alert_placeholder"></div>