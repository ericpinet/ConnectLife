<%@page import="com.connectlife.coreserver.Application"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/panel.js"></script>
<script>
initPanel("/api/system",
		  "table_system_info",
		  "system_info_reload",
		  undefined,
		  "loading_system_placeholder",
		  ["Item", "Value"],
		  undefined);

</script>
<div class="panel panel-default">
  <div class="panel-heading">
    <div class="btn-group pull-right">
        <button id="system_info_reload" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Reload</button>
    </div>
    <strong>System informations</strong>
  </div>
  <div id="loading_system_placeholder" align="center"></div>
  <table class="table table-condensed" id="table_system_info"><tr></tr></table>
  <div class="panel-footer"></div>
</div>

<br>
<br>