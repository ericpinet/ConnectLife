/*!
 *  panel_services_status.java
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
    $('#services_reload').on('click', function(e){
        e.preventDefault(); // prevent the default form submit action
        
        // reset table data
    	resetServicesTable();
    	
    	// start loading
        startLoader();
        
        $.ajax({ url: "/connectlife?query=list_services",
            context: document.body,
            success: function(responseJson) {
            	reloadServicesStatus(responseJson);
            },
        	error: function() {
            	showError("Server request failed.");
            }
        });
    });
});

/*!
 * On document ready
 */
$(document).ready(function() {
	
	// reset table data
	resetServicesTable();
	
	// start loading
	startLoader();
	
  	$.ajax({ url: "/connectlife?query=list_services",
      context: document.body,
      success: function(responseJson) {
      		reloadServicesStatus(responseJson);
      },
	  error: function() {
		  showError("Server request failed.");
	  }
  	});
});

/*!
 * Reload service status table
 */
function reloadServicesStatus(responseJson) {
	
	// show header
	var $table = $("#table_services");
	$("<tr>").appendTo($table)
	.append($("<td><strong>Short Name</strong>"))
	.append($("<td><strong>Name</strong>"))
	.append($("<td><strong>Description</strong>"))
	.append($("<td><strong>Status</strong>"));
	
	// Load table data for each json object
	$.each(responseJson, function(index, service) {
		$("<tr>").appendTo($table)
		.append($("<td>").text(service.short_name))
		.append($("<td>").text(service.name))
		.append($("<td>").text(service.desc))
		.append($("<td><span class=\"label label-success\">Started</span>"));
		
		// stop the loading
		stopLoader();
	});
}

/*!
 * Show error
 */
function showError(error) {
	
	// Show error
	var $table = $("#table_services");
	$("<tr>").appendTo($table)
	.append($("<td>Error! Unable to complete request.<br>Details:"+error));
	
	// stop the loading.
	stopLoader();
}

/*!
 * Reset Services Table
 */
function resetServicesTable() {
	$("#table_services > tbody").html("");
}

/*!
 * Start loading annimation.
 */
function startLoader() {
	document.getElementById('table_service_loader').style.height = "25";
	document.getElementById('table_service_loader').style.width = "25";
	document.getElementById('table_service_loader').style.visibility = 'visible';
}

/*!
 * Stop loading annimation.
 */
function stopLoader() {
	document.getElementById('table_service_loader').style.visibility = 'hidden';
	document.getElementById('table_service_loader').style.height = "0";
	document.getElementById('table_service_loader').style.width = "0";
}
