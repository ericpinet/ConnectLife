<%@page import="com.connectlife.coreserver.Application"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/panel.js"></script>
<script>
initPanel("/api/config",
		  "table_configs",
		  "configs_reload",
		  undefined,
		  "loading_config_placeholder",
		  ["Section", "Item", "Type", "Value"],
		  ["m_section", "m_item", "m_type", "m_string_value"]);

</script>

<div class="panel panel-default">
  <div class="panel-heading">
    <div class="btn-group pull-right">
        <button id="configs_reload" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Reload</button>
    </div>
    <h3 class="panel-title">Settings</h3>
  </div>
  <div id="loading_config_placeholder" align="center"></div>
  <table class="table table-condensed" id="table_configs"><tr></tr></table>
  <div class="panel-footer"></div>
</div>
<br>
<br>