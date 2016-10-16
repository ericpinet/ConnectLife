/*!
 *  panel.js
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2016-10-10.
 *  Copyright (c) 2015-2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */

if (typeof jQuery === 'undefined') {
  throw new Error('ConnectLifet\'s JavaScript requires jQuery')
}

function initPanel( _request, _div_panel, _btn_reload, _btn_open, _div_loading, _headers, _datas ) {
	var $apanel = new Panel(	_request,
								_div_panel,
								_btn_reload,
								_btn_open,
								_div_loading,
								_headers,
								_datas);
	
	/*!
	 * On click reload
	 */
	$(function () {
		$("#"+$apanel.btn_reload).on('click', function(e){
			e.preventDefault(); // prevent the default form submit action
			$apanel.sendQuery();
		});
	});
	
	/*!
	 * On document ready
	 */
	$(document).ready(function () {
		$apanel.sendQuery();
	});
}


/*!
 * Panel generic class
 */
function Panel ( _request, _div_panel, _btn_reload, _btn_open, _div_loading, _headers, _datas ) {

	/*!
	 * Loading div id. (Position of loading)
	 */
	this.div_loading = _div_loading;
	
	/*!
	 * Panel data div id. (Position of the main data)
	 * Ex: "#table_system_info
	 */
	this.div_panel = _div_panel;
	
	/*!
	 * Button reload for the panel
	 * Ex: "#btn_reload"
	 */
	this.btn_reload = _btn_reload;
	
	/*!
	 * Button open for the panel
	 * Ex: "#btn_open"
	 */
	this.btn_open = _btn_open;
	
	/*!
	 * Request query.
	 * Ex: "/connectlife?query=list_system_info"
	 */
	this.request = _request;
	
	/*!
	 * List of the data to show
	 */
	this.datas = _datas;
	
	/*!
	 * Headers of the table
	 */
	this.headers = _headers;
	
	/*!
	 * Send query to load panel data
	 */
	this.sendQuery = function() {
		
		this.resetPanel(); 		// reset table
		this.startLoading(); 	// start loading
		
		$.ajax({ url: this.request,
            context: this,
            success: function(responseJson) {
            	this.stopLoading();
            	this.reloadData(responseJson);
            },
        	error: function() {
        		this.stopLoading();
            	this.showError("Server request failed.");
            }
        });
	};
	
	/*!
	 * Reload panel data
	 */
	this.reloadData = function (json) {
		
		// show header
		var $panel = $("#" + this.div_panel);
		
		// build header if header is defined
		if (this.headers != undefined) {
			var header_row = "<tr>";
			for (i=0 ; i<this.headers.length ; i++) {
				header_row += "<td><strong>" + this.headers[i] + "</strong></td>";
			}
			header_row += "</tr>";
			$panel.append(header_row);
		}
		
		// build data table
		// if datas undefined, string array
		if (this.datas == undefined) {
			$.each(json, function(key, value) {			
				// if it's a map of string
				if (typeof(value) == "string") {
					$("<tr>").appendTo($panel)
					.append($("<td>").text(key))
					.append($("<td>").text(value));
				}
			});
		}
		else {
			// load item data
			for (i=0 ; i<json.length ; i++) { 
					
				// load all data
				var row = "<tr>";
				for(j=0 ;j<this.datas.length ; j++) {
					row += "<td>" + json[i][this.datas[j]] + "</td>";
				}
				row += "</tr>";
				$panel.append(row);
			}
		}	
	};
	
	/*!
	 * Show error
	 */
	this.showError = function (error) {
		
		// Show error
		$('#alert_placeholder').html('<div class="alert alert-danger">'+
									 '<a class="close" data-dismiss="alert">×</a><span>'+
									 error+" (Request: "+this.request+")"+
									 '</span></div>');
	};
	
	/*!
	 * Reset data
	 */
	this.resetPanel = function () {
		$("#" + this.div_panel + " > tbody").html("");
	};
	
	/*!
	 * Start loading animation.
	 */
	this.startLoading = function () {
		$('#'+this.div_loading).html('<br><div class="loader"></div>');
	};

	/*!
	 * Stop loading animation.
	 */
	this.stopLoading = function () {
		$('#'+this.div_loading).html('');
	};
}