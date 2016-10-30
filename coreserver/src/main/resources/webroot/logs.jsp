<%@page import="com.connectlife.coreserver.Application"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/logs.js"></script>
<!-- Custom styles for this template -->
<link href="css/connectlife.css" rel="stylesheet">

<script>
initLogs("/api/log",
		  "logs_data",
		  "loading_logs_placeholder",
		  "logs_reload");
</script>

<table class="table table-condensed borderless" id="table_logs"><tr></tr></table>

<div id="logs_data"></div>

<div id="loading_logs_placeholder" align="center" data-toggle="tooltip" data-placement="top" title="Click to stop/start auto scrolling"></div>

<br>
<br>
<br>
<br>