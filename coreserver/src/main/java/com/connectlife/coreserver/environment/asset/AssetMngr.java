/**
 *  AssetMngr.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-07-25.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.asset;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;

import com.connectlife.coreserver.Application;
import com.clapi.data.Asset;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.protobuf.ByteString;

/**
 * Asset manager for environment file of the application.
 * <p>
 * The storage was buil like that :<br>
 * - Base path of the application <br>
 * - data/asset folder <br>
 * - [uid]-[label] of the asset <br>
 * <p>
 * Sample: /opt/connectlife/coreserver/data/assets/1010187335252-image1.png<br>
 * 
 * <p>
 * The asset URL was build like that: <br>
 * - http://[hostname]:[server port]/[uid]-[label] <br>
 * <p>
 * Sample : http://connectlife:38888/192933736362-image1.png<br>
 * <br>
 * @author ericpinet
 * <br> 2016-07-25
 */
public class AssetMngr implements AssetManager {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(AssetMngr.class);
	
	/**
	 * Environment data path contain the binary data of the asset
	 */
	private static final String ASSET_DATA_PATH = "data/assets";
	
	/**
	 * Asset http handler for the asset environment data.
	 */
	private AssetHttpHandler m_handler;
	
	/**
	 * Http server for the asset environment data.
	 */
	private Server m_server;
	
	/**
	 * Flag to indicate if the HTTP server was initialized.
	 */
	private boolean m_is_init;
	
	/**
	 * Default constructor.
	 */
	public AssetMngr () {
		m_is_init = false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#init()
	 */
	@Override
	public boolean init() {
		boolean ret_val = false;
		
		if (null == m_server && false == m_is_init) {
		
			m_server = new Server(0);
			m_handler = new AssetHttpHandler();
			m_server.setHandler(m_handler);
	
	        Thread thread = new Thread(new Runnable()
	        {
	        	public void run()
	        	{
	        		// this will be run in a separate thread
	        		try {
	        			
	        			// Start the handle for http request 
	        			m_server.start();
	        			
	        			// Update the connection information (At this point the tcp/port is reserved)
	        			m_handler.updateConnectionInformaiton();
	        			
	        			m_logger.info("Service started: http://"+m_handler.getHostname()+":"+m_handler.getPort());
	        			
	        			// Ready to receive client request
	        			m_server.join();
	        			
	        			m_is_init = true;
	        			
	        		} catch (Exception e) {
	        			m_logger.error("Unable to start the asset http server.");
	        			m_logger.error(e.getMessage());
	        			StdOutErrLog.tieSystemOutAndErrToLog();
	        			e.printStackTrace();
	        		}
	        	}
	        });
	
	        // start the thread
	        thread.start();
		}
		else {
			m_logger.warn("Service already started.");
		}
		
		return ret_val;
	}

	/**
	 * Return true if the asset server was started.
	 * 
	 * @return True if the asset server was started.
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#isInit()
	 */
	@Override
	public boolean isInit() {
		return m_is_init;
	}

	/**
	 * UnInit the asset server. Ready to init again.
	 * 
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#unInit()
	 */
	@Override
	public void unInit() {
		try {
			m_server.stop();
			m_handler.stop();
		} catch (Exception e) {
			m_logger.error("Unable to start the asset http server.");
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		finally {
			m_server = null;
			m_handler = null;
		}
	}

	/**
	 * Add asset in the environment data file storage.
	 * 
	 * @param _asset Asset file information.
	 * @param _data Binary data of the file.
	 * @throws Exception If something goes wrong.
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#addAsset(com.clapi.data.Asset, com.google.protobuf.ByteString)
	 */
	@Override
	public void addAsset(Asset _asset, ByteString _data) throws Exception {
		saveAsset(_asset, _data, true);
	}

	/**
	 * Update asset in the environment data file storage.
	 * 
	 * @param _asset Asset file information.
	 * @param _data Binary data of the file.
	 * @throws Exception If something goes wrong.
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#addAsset(com.clapi.data.Asset, com.google.protobuf.ByteString)
	 */
	@Override
	public void updateAsset(Asset _asset, ByteString _data) throws Exception {
		saveAsset(_asset, _data, false);
	}
	
	/**
	 * Save the asset binary data in the environment data.
	 * 
	 * @param _asset Asset information. 
	 * @param _data Asset binary data.
	 * @param _add True if it's an add, False for an update.
	 * @throws Exception If something goes wrong.
	 */
	private void saveAsset(Asset _asset, ByteString _data, boolean _add) throws Exception {
		if (null == _asset) {
			throw new Exception("Asset file information cannot be null.");
		}
		
		if (null == _data) {
			throw new Exception("Asset file data cannot be null.");
		}
		
		// build filename full path
		String filename = Application.getApp().getBasePath() + "/" + 
						  ASSET_DATA_PATH + "/" + 
						  getAssetFilename(_asset);
		
		File file = new File(filename);
		if (true == _add) {
			// check if the file name does not already exist
			if (file.exists() && !file.isDirectory()) { 
			    throw new Exception("This file asset already exist. Use the updateAsset instead of addAsset. "+_asset.toString());
			}
		}
		else {
			// check if the file name already exist
			if (!file.exists() || file.isDirectory()) { 
			    throw new Exception("This file asset does not already exist. Use the addAsset instead of updateAsset. "+_asset.toString());
			}
		}
		
		try {
			// create directory
			File directory = new File(	Application.getApp().getBasePath() + "/" + ASSET_DATA_PATH + "/");
			directory.mkdirs();
			
			// save file in storage
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(_data.toByteArray(), 0, _data.size());
			fos.flush();
			fos.close();
		}
		catch (Exception e) {
			m_logger.error("Unable save asset file.");
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
	}

	/**
	 * Return the url of the asset.
	 * 
	 * @param _asset Asset information.
	 * @return Url of the binary data of the asset.
	 * @throws Exception If something goes wrong.
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#getAssetUrl(com.clapi.data.Asset)
	 */
	@Override
	public String getAssetUrl(Asset _asset) throws Exception {
		if (null == m_handler) {
			throw new Exception ("The asset server was not initialized.");
		}
		
		String ret_val = "";		
		ret_val = "http://" + m_handler.getHostname() + ":" + m_handler.getPort() + "/" + getAssetFilename(_asset);
		return ret_val;
		
	}
	
	/**
	 * Return the asset file name.
	 * 
	 * @param _asset Asset information.
	 * @return The asset file name.
	 */
	private String getAssetFilename(Asset _asset) {
		String ret_val = "";
		ret_val = _asset.getUid() + "-" + _asset.getLabel();
		return ret_val;
	}

}
