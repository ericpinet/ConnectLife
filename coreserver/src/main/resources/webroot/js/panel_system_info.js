/*!
 *  panel_system_info.js
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
    $('#system_info_reload').on('click', function(e){
        e.preventDefault(); // prevent the default form submit action
        
        // reset table data
    	resetSystemInfoTable();
    	
    	// start loading
        startSystemInfoLoader();
        
        $.ajax({ url: "/connectlife?query=list_system_info",
            context: document.body,
            success: function(responseJson) {
            	reloadSystemInfoData(responseJson);
            },
        	error: function() {
            	showSystemInfoError("Server request failed.");
            }
        });
    });
});

/*!
 * On document ready
 */
$(document).ready(function() {
	
	// reset table data
	resetSystemInfoTable();
	
	// start loading
	startSystemInfoLoader();
	
  	$.ajax({ url: "/connectlife?query=list_system_info",
      context: document.body,
      success: function(responseJson) {
      		reloadSystemInfoData(responseJson);
      },
	  error: function() {
		  showSystemInfoError("Server request failed.");
	  }
  	});
});

/*!
 * Reload table data
 */
function reloadSystemInfoData(responseJson) {
	
	// stop the loading
	stopSystemInfoLoader();
	
	// show header
	var $table = $("#table_system_info");
	$("<tr>").appendTo($table)
	.append($("<td><strong>System information</strong>"))
	.append($("<td><strong>Value</strong>"));
	
	// Load table data for each json object
	$.each(responseJson, function(key, value) {
		$("<tr>").appendTo($table)
		.append($("<td>").text(key))
		.append($("<td>").text(value));
	});
}

/*!
 * Show error
 */
function showSystemInfoError(error) {
	
	// stop the loading.
	stopSystemInfoLoader();
	
	// Show error
	var $table = $("#table_system_info");
	$("<tr>").appendTo($table)
	.append($("<td>Error! Unable to complete request.<br>Details:"+error));
}

/*!
 * Reset table data
 */
function resetSystemInfoTable() {
	$("#table_system_info > tbody").html("");
}

/*!
 * Start loading animation.
 */
function startSystemInfoLoader() {
	document.getElementById('table_system_info_loader').style.height = "25";
	document.getElementById('table_system_info_loader').style.width = "25";
	document.getElementById('table_system_info_loader').style.visibility = 'visible';
}

/*!
 * Stop loading animation.
 */
function stopSystemInfoLoader() {
	document.getElementById('table_system_info_loader').style.visibility = 'hidden';
	document.getElementById('table_system_info_loader').style.height = "0";
	document.getElementById('table_system_info_loader').style.width = "0";
}
