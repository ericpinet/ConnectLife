package com.connectlife.coreserver.webserver.request.data;

public class Modules {
	
	public String name;
	public String short_name;
	public String desc;
	public String status;
	
	public Modules( String _name, String _short_name, String _desc, String _status ) {
		name = _name;
		short_name = _short_name;
		desc = _desc;
		status = _status;
	}
}
