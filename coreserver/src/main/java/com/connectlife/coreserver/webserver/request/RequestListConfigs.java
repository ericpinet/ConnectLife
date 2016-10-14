/**
 *  RequestListConfig.java
 *  coreserver
 *
 *  Created by ericpinet on 13 oct. 2016.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.webserver.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.config.ConfigItem;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.api.client.util.Preconditions;
import com.google.gson.Gson;

/**
 * 
 * 
 * @author ericpinet
 * <br> 13 oct. 2016
 */
public class RequestListConfigs extends RequestBase {
	
	/**
	 * Query of the request. 
	 */
	private final static String QUERY = "list_configs";
	
	/**
	 * Config section 
	 */
	private final static String SECTION = "section";
	
	/**
	 * Config item 
	 */
	private final static String ITEM = "item";
	
	/**
	 * Config type
	 */
	private final static String TYPE = "type";
	
	/**
	 * MConfig value
	 */
	private final static String VALUE = "value";
	
	/**
	 * Default constructor.
	 */
	public RequestListConfigs() {
		
	}
	
	/**
	 * @param _request
	 * @return
	 * @see com.connectlife.coreserver.webserver.request.RequestBase#requestCompatibility(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public boolean requestCompatibility(HttpServletRequest _request) {
		
		Preconditions.checkNotNull(_request, i18n.tr("Error! It's not possible to check compatibility of null http request."));
		
		boolean ret_val = false;
		try {
			Map<String, List<String>> params = getParameters(_request.getQueryString());
			List<String> queries = params.get("query");
			
			if ( queries.contains(QUERY) ) {
				ret_val = true;
			}
			
		} catch (UnsupportedEncodingException e) {
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		
		return ret_val;
	}

	/**
	 * Process the request. 
	 * 
	 * @param _request
	 * @param _response
	 * @throws ServletException
	 * @throws IOException
	 * @throws Exception
	 * @see com.connectlife.coreserver.webserver.request.RequestBase#process(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void process(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException, Exception {
		
		Preconditions.checkArgument(requestCompatibility(_request), i18n.tr("Error! This request is invalid: "+_request.getQueryString()));
		
		@SuppressWarnings("rawtypes")
		List<Map> list = new ArrayList<Map>();
		
		List<ConfigItem> configs = Application.getApp().getConfig().getConfigs();
		
		Iterator<ConfigItem> it = configs.iterator();
		while (it.hasNext()) {
			ConfigItem item = it.next();
			list.add(buildMap(item.getSection(), item.getItem(), item.getType().toString(), item.getValueToString()));
		}
		
	    String json = new Gson().toJson(list);

	    _response.setContentType("application/json");
	    _response.setCharacterEncoding("UTF-8");
	    _response.getWriter().write(json);
	}
	
	/**
	 * Build map for a module. 
	 * 
	 * @param _name
	 * @param _short_name
	 * @param _description
	 * @param _status
	 * @return
	 */
	private Map<String, String> buildMap(String _section, String _item, String _type, String _value) {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put(SECTION, _section);
		ret.put(ITEM, _item);
		ret.put(TYPE, _type);
		ret.put(VALUE, _value);
		return ret;
	}

}
