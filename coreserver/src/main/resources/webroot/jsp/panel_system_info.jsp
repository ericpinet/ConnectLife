<%@page import="com.connectlife.coreserver.Application"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/panel.js"></script>
<script>
initPanel("/connectlife?query=list_system_info",
		  "table_system_info",
		  "system_info_reload",
		  undefined,
		  "table_system_info_loading",
		  ["System Information", "Value"],
		  undefined);

</script>
<div class="panel panel-default">
  <div class="panel-heading">
    <div class="btn-group pull-right">
        <button id="system_info_reload" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Reload</button>
    </div>
    <h3 class="panel-title">System Informations</h3>
  </div>
  <div class="panel-body" align="center">
  	<div class="loader" id="table_system_info_loading"></div>
    <div class="table-responsive">
    	<table class="table table-condensed" id="table_system_info"><tr></tr></table>
	</div>
  </div>
</div>

<br>
<br>