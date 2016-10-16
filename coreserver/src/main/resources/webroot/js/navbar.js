/*!
 *  navbar.js
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2016-10-15.
 *  Copyright (c) 2015-2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */

if (typeof jQuery === 'undefined') {
  throw new Error('ConnectLifet\'s JavaScript requires jQuery')
}

/*!
 * On click reload
 */
$(function () {
	$("#btn-logout").on('click', function(e){
		e.preventDefault(); // prevent the default form submit action
		
		$.ajax({ url: "connectlife?query=logout",
            context: this,
            success: function(json) {
            	document.location.href = "index.jsp"
            },
        	error: function() {
        		$('#alert_placeholder').html('<div class="alert alert-danger">'+
											 '<a class="close" data-dismiss="alert">×</a><span>'+
											 'Server request error!'+
											 '</span></div>');
            }
        });
		
		
	});
});