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

function initLogs( _request, _div_table_logs, _div_loading, _btn_reload ) {
	
	var $logspanel = new LogsPanel( _request, _div_table_logs, _div_loading, _btn_reload, true, true );
	
	/*!
	 * On click reload
	 */
	$(function () {
		$("#"+$logspanel.btn_reload).on('click', function(e){
			e.preventDefault(); // prevent the default form submit action
			$logspanel.sendQuery();
		});
		
		$("#"+$logspanel.div_loading).on('click', function(e){
			e.preventDefault(); // prevent the default form submit action
			$logspanel.auto_scroll = !$logspanel.auto_scroll;
		});
		
	});
	
	/*!
	 * On document ready
	 */
	$(document).ready(function () {
		$logspanel.autoRefresh($logspanel);
		$logspanel.startLoading();
	});
	
	
}


/*!
 * Logs panel
 */
function LogsPanel ( _request, _div_table_logs, _div_loading, _btn_reload, _auto_scroll, _auto_refresh ) {

	/*!
	 * Loading div id. (Position of loading)
	 */
	this.div_loading = _div_loading;
	
	/*!
	 * Panel data div id. (Position of the main data)
	 * Ex: "#logs_panel
	 */
	this.div_table_logs = _div_table_logs;
	
	/**
	 * Request ajax
	 */
	this.request = _request;
	
	/**
	 * Bouton reload
	 */
	this.btn_reload = _btn_reload;
	
	/**
	 * Keep tracking of the possition in log
	 */
	this.log_count = 0;
	
	/**
	 * Status of the auto scroll (true, false)
	 */
	this.auto_scroll = _auto_scroll;
	
	/**
	 * Status of the auto refresh (true, false)
	 */
	this.auto_refresh = _auto_refresh;
	
	/*!
	 * Send query to load panel data
	 */
	this.sendQuery = function() {
		
		$.ajax({ url: this.request + "?id="+this.log_count,
            context: this,
            success: function(responseJson) {
            	this.reloadData(responseJson);
            },
        	error: function() {
            	this.showError("Server request failed.");
            }
        });
	};
	
	/*!
	 * Auto refresh the logs data
	 */
	this.autoRefresh = function (_log_panel) {
		
		if (_log_panel.auto_refresh) {
			_log_panel.sendQuery();
		}
		
		setTimeout(function(){ 
	        _log_panel.autoRefresh(_log_panel);
	    },1000);
	}
	
	/*!
	 * Reload panel data
	 */
	this.reloadData = function (json) {
		
		// show header
		var $panel = $("#" + this.div_table_logs);
		var $log_count = this.log_count;
		
		// show logs data
		$.each(json, function(key, value) {			
			// if it's a map of string
			if (typeof(value) == "string") {
				// extract all section of logs
				
				// extract date
				const regex_date = /\d{2}:\d{2}:\d{2}.\d{3}/g;
				var date = regex_date.exec(value);
				if ((m = regex_date.exec(value)) !== null) {
				    
				    m.forEach((match, groupIndex) => {
				    	date = match;
				    });
				}
				
				// extract thread
				const regex_thread = / \[\w+\W+\w+\] /g;
				var thread = regex_thread.exec(value);
				if ((m = regex_thread.exec(value)) !== null) {
				    
				    m.forEach((match, groupIndex) => {
				    	thread = match;
				    });
				}
				
				$panel.append('<small><pre><strong>' + date + '</strong>' +  value + '</pre></small>')
				$log_count++;
			}
		});
		
		this.log_count = $log_count;
		
		// if auto scroll
		if (this.auto_scroll) {
			document.getElementById(this.div_loading).scrollIntoView();
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
	
	this.addLine = function(_line) {
		$("#" + this.div_table_logs).append(_line);
	}
	
	/*!
	 * Reset data
	 */
	this.resetPanel = function () {
		$("#" + this.div_table_logs + " > tbody").html("");
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