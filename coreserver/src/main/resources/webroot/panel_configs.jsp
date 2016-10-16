<%@page import="com.connectlife.coreserver.Application"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/panel.js"></script>
<script>
initPanel("/connectlife?query=list_configs",
		  "table_configs",
		  "configs_reload",
		  undefined,
		  "table_configs_loading",
		  ["Section", "Item", "Type", "Value"],
		  ["section", "item", "type", "value"]);

</script>

<div class="panel panel-default">
  <div class="panel-heading">
    <div class="btn-group pull-right">
        <button id="configs_reload" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Reload</button>
    </div>
    <h3 class="panel-title">Settings</h3>
  </div>
  <div class="panel-body" align="center">
  	<div class="loader" id="table_configs_loading"></div>
    <div class="table-responsive">
    	<table class="table table-condensed" id="table_configs"><tr></tr></table>
	</div>
  </div>
</div>
<br>
<br>