/**
 *  CmdGetAssetUrl.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-08-03.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.cmd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.clapi.data.Asset;
import com.connectlife.coreserver.environment.asset.AssetManager;
import com.google.api.client.util.Preconditions;

/**
 * Command to get a asset url in the environment.
 * 
 * @author ericpinet
 * <br> 2016-08-03
 */
public class CmdGetAssetUrl extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdGetAssetUrl.class);
	
	/**
	 * Asset to add in the environment.
	 */
	private Asset m_asset;
	
	/**
	 * Asset url.
	 */
	private String m_url;
	
	/**
	 * Default constructor.
	 *  
	 * @param _asset Asset to add in the environment.
	 */
	public CmdGetAssetUrl (Asset _asset){
		m_asset = _asset;
		m_url = "";
	}
	
	/**
	 * Execute the cmd on the environment.
	 * 
	 * @throws Exception Exception when something goes wrong.
	 * @see com.connectlife.coreserver.environment.cmd.Cmd#execute()
	 */
	@Override
	public void execute() throws Exception {
		
		m_logger.info("Execution start ...");
		
		Preconditions.checkNotNull(m_asset, "Error! It's not possible to update null asset in the environment.");
		Preconditions.checkArgument(false == m_asset.getUid().isEmpty(), "Error! It's not possible to update a asset with a UID.");
		
		// get the asset manager
		AssetManager asset_manager = m_context.getAssetManager();
							
		// Update binary data
		m_url = asset_manager.getAssetUrl(m_asset);
		
		// display info in log
		m_logger.info(m_asset.toString() + "\nUrl:" + m_url);
		
		m_logger.info("Execution completed.");
	}	
	
	/**
	 * Return the url result of the command.
	 * 
	 * @return Url of the asset.
	 */
	public String getUrl() {
		return m_url;
	}
}
