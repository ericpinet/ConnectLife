/*!
 *  panel_modules.js
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2016-10-10.
 *  Copyright (c) 2015-2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */

if (typeof jQuery === 'undefined') {
  throw new Error('ConnectLifet\'s JavaScript requires jQuery')
}

/*!
 * On click reload
 */
$(function(){
    $('#configs_reload').on('click', function(e){
        e.preventDefault(); // prevent the default form submit action
        
        // reset table data
    	resetConfigTable();
    	
    	// start loading
        startConfigLoader();
        
        $.ajax({ url: "/connectlife?query=list_configs",
            context: document.body,
            success: function(responseJson) {
            	reloadConfigData(responseJson);
            },
        	error: function() {
            	showConfigError("Server request failed.");
            }
        });
    });
});

/*!
 * On document ready
 */
$(document).ready(function() {
	
	// reset table data
	resetConfigTable();
	
	// start loading
	startConfigLoader();
	
  	$.ajax({ url: "/connectlife?query=list_configs",
      context: document.body,
      success: function(responseJson) {
      		reloadConfigData(responseJson);
      },
	  error: function() {
		  showConfigError("Server request failed.");
	  }
  	});
});

/*!
 * Reload table data
 */
function reloadConfigData(responseJson) {
	
	// stop the loading
	stopConfigLoader();
	
	// show header
	var $table = $("#table_configs");
	$("<tr>").appendTo($table)
	.append($("<td><strong>Section</strong>"))
	.append($("<td><strong>Item</strong>"))
	.append($("<td><strong>Type</strong>"))
	.append($("<td><strong>value</strong>"));
	
	// Load table data for each json object
	$.each(responseJson, function(index, config) {
		$("<tr>").appendTo($table)
		.append($("<td>").text(config.section))
		.append($("<td>").text(config.item))
		.append($("<td>").text(config.type))
		.append($("<td>").text(config.value));
	});
}

/*!
 * Show error
 */
function showConfigError(error) {
	
	// stop the loading.
	stopLoader();
	
	// Show error
	var $table = $("#table_configs");
	$("<tr>").appendTo($table)
	.append($("<td>Error! Unable to complete request.<br>Details:"+error));
}

/*!
 * Reset table data
 */
function resetConfigTable() {
	$("#table_configs > tbody").html("");
}

/*!
 * Start loading animation.
 */
function startConfigLoader() {
	document.getElementById('table_configs_loader').style.height = "25";
	document.getElementById('table_configs_loader').style.width = "25";
	document.getElementById('table_configs_loader').style.visibility = 'visible';
}

/*!
 * Stop loading animation.
 */
function stopConfigLoader() {
	document.getElementById('table_configs_loader').style.visibility = 'hidden';
	document.getElementById('table_configs_loader').style.height = "0";
	document.getElementById('table_configs_loader').style.width = "0";
}
