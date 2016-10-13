<%@page import="com.connectlife.coreserver.Application"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/panel_modules.js"></script>

<div class="panel panel-default">
  <div class="panel-heading">
    <div class="btn-group pull-right">
        <button id="modules_details" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> Details</button>
        <button id="modules_reload" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Reload</button>
    </div>
    <h3 class="panel-title">Modules</h3>
  </div>
  <div class="panel-body" align="center">
  	<div class="loader" id="table_modules_loader"></div>
    <div class="table-responsive">
    	<table class="table table-condensed" id="table_modules"><tr></tr></table>
	</div>
  </div>
</div>
<br>
<br>