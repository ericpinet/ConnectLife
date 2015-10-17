/**
 *  ApiProcessor.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-10-16.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.apiserver;

// external
import org.apache.thrift.TException;

// internal
import com.connectlife.clapi.CLApi;
import com.connectlife.clapi.Xception;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-10-16
 */
public class ApiProcessor implements CLApi.Iface {
	
	/**
	 * Default constructor
	 */
	public ApiProcessor(){
		
	}

	/**
	 * @return
	 * @throws Xception
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#getVersion()
	 */
	@Override
	public String getVersion() throws Xception, TException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param version
	 * @return
	 * @throws Xception
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#checkCompatibility(java.lang.String)
	 */
	@Override
	public boolean checkCompatibility(String version) throws Xception, TException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return
	 * @throws Xception
	 * @throws TException
	 * @see com.connectlife.clapi.CLApi.Iface#getEnvironmentDataJson()
	 */
	@Override
	public String getEnvironmentDataJson() throws Xception, TException {
		// TODO Auto-generated method stub
		return null;
	}
}
