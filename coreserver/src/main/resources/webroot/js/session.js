/*!
 *  session.js
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
 * On click login
 */
$(function () {
	$("#btn-login").on('click', function(e){
		e.preventDefault(); // prevent the default form submit action
		
		$('#alert_placeholder').html("");
		
		// get user and password
		var username = document.getElementById('user').value;
		var password = document.getElementById('pass').value;
		
		if (username == "") {
			$("#user-group").attr("class", "form-group has-error");
		}
		else {
			$("#user-group").attr("class", "form-group");
		}
		
		if (password == "") {
			$("#pass-group").attr("class", "form-group has-error");
		}
		else {
			$("#pass-group").attr("class", "form-group");
		}
		
		// if username and password is not empty
		if (username != "" && password != "") {
			// send login request
			$.ajax({ url: "connectlife?query=login&user="+username+"&password="+password,
		        context: this,
		        success: function(json) {
	
	        		if (json['status'] == '200') {
	        			document.location.href="main.jsp"
	        		}
	        		else {
	        			$('#alert_placeholder').html('<div class="alert alert-danger">'+
	        										 '<a class="close" data-dismiss="alert">×</a><span>'+
	        										 json['error']+
	        										 '</span></div>');
	        		}
		        },
		    	error: function() {
		    		$('#alert_placeholder').html('<div class="alert alert-danger">'+
												 '<a class="close" data-dismiss="alert">×</a><span>'+
												 'Server request failed!'+
												 '</span></div>');
		        }
			}); // ajax
		}
		else {
			$('#alert_placeholder').html('<div class="alert alert-danger">'+
										 '<a class="close" data-dismiss="alert">×</a><span>'+
										 'Username or password cannot be empty!'+
										 '</span></div>');
		}
	}); // click
});