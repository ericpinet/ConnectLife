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
import com.clapi.data.Asset.AssetMode;
import com.clapi.data.Asset.AssetType;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.common.io.Files;
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
 * - http://[hostname]:[server port]/asset?uid=[uid]<br>
 * <p>
 * Sample : http://connectlife:38888/asset?uid=12345623432566<br>
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
	 * Flag to indicate if the initialization is in progress.
	 */
	private boolean m_init_inprogress;
	
	/**
	 * Default constructor.
	 */
	public AssetMngr () {
		m_is_init = false;
		m_init_inprogress = false;
	}

	/**
	 * Initialization of the asset manager. 
	 * 
	 * @return True if the asset manager is correctly loaded.
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#init()
	 */
	@Override
	public boolean init() {
		boolean ret_val = false;
		
		m_logger.info("Initialization starting ...");
		
		if (null == m_server && false == m_is_init) {
		
			m_server = new Server(0);
			m_handler = new AssetHttpHandler(this);
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
	        			
	        			// restore flag 
	        			m_is_init = true;
	        			m_init_inprogress = false;
	        			
	        			// Ready to receive client request
	        			m_server.join();
	        			
	        		} catch (Exception e) {
	        			m_logger.error("Unable to start the asset http server.");
	        			m_logger.error(e.getMessage());
	        			StdOutErrLog.tieSystemOutAndErrToLog();
	        			e.printStackTrace();
	        			
	        			// restore flag 
	        			m_init_inprogress = false;
	        		}
	        	}
	        });
	
	        // set the in progress flag
	        m_init_inprogress = true;
	        
	        // start the thread
	        thread.start();
	        
	        // wait flag return
	        while (true == m_init_inprogress) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					m_logger.error(e.getMessage());
        			StdOutErrLog.tieSystemOutAndErrToLog();
        			e.printStackTrace();
				}
	        }
	        
	        ret_val = m_is_init;
	        
	        if(m_is_init) {
	        	try {
					prepareSystemFactoryAsset();
				} catch (Exception e) {
					unInit();
					m_logger.error(e.getMessage());
					StdOutErrLog.tieSystemOutAndErrToLog();
					e.printStackTrace();
				}
	        }
	        
	        m_logger.info("Initialization completed.");
	        
		}
		else {
			m_logger.warn("Already initialized.");
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
		
		m_logger.info("UnInit ...");
		
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
			m_is_init = false;
		}
		
		m_logger.info("UnInit completed.");
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
		
		Preconditions.checkState(m_is_init, "Asset manager must be initialized before save asset.");
		Preconditions.checkArgument(null != _asset, "Asset file information cannot be null.");
		Preconditions.checkArgument(null != _data, "Asset data cannot be null.");
		Preconditions.checkArgument(false == _asset.getUid().isEmpty() && false == _asset.getLabel().isEmpty(), "Asset uid or label cannot be empty.");
		
		// build filename full path
		String filename = getAssetFullFilename(_asset);
		
		m_logger.debug("Asset : " + _asset.toString());
		
		File file = new File(filename);
		if (true == _add) {
			// check if the file name does not already exist
			if (file.exists() && !file.isDirectory()) { 
			    throw new Exception("This file asset already exist. Use the updateAsset instead of addAsset. "+_asset.toString() + "\nFilename: " + filename);
			}
		}
		else {
			// check if the file name already exist
			if (!file.exists() || file.isDirectory()) { 
			    throw new Exception("This file asset does not already exist. Use the addAsset instead of updateAsset. "+_asset.toString() + "\nFilename: " + filename);
			}
		}
		
		try {
			// create directory
			File directory = new File(Application.getApp().getBasePath() + "/" + ASSET_DATA_PATH + "/");
			directory.mkdirs();
			
			// save file in storage
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(_data.toByteArray(), 0, _data.size());
			fos.flush();
			fos.close();
			
			m_logger.debug("Asset save completed");
		}
		catch (Exception e) {
			m_logger.error("Unable save asset file. Filename: " + filename);
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete the asset binary data from the environment.
	 * 
	 * @param _asset Asset information to delete.
	 * @throws Exception If something goes wrong.
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#deleteAsset(com.clapi.data.Asset)
	 */
	@Override
	public void deleteAsset(Asset _asset) throws Exception {
		
		Preconditions.checkNotNull(_asset, "It's not possible to delete null asset.");
		
		// build filename full path
		String filename = getAssetFullFilename(_asset);
		
		m_logger.debug("Asset : " + _asset.toString());
		
		File file = new File(filename);
		if (file.exists() && !file.isDirectory()) { 
		    file.delete();
		    
		    m_logger.debug("File deleted : " + filename);
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
		
		String ret_val = "";
		Preconditions.checkNotNull(m_handler, "The asset server was not initialized.");
		
		// check if the file exist
		File asset_file = new File(getAssetFullFilename(_asset));
		if (false == asset_file.exists()) {
			throw new Exception ("The asset file doesn't exist.");
		}
		
		m_logger.debug("Asset: " + _asset.toString());
				
		ret_val = "http://" + m_handler.getHostname() + ":" + String.valueOf(m_handler.getPort()) + "/" + "asset?uid="+_asset.getUid();
		
		m_logger.debug("Url returned for the asset: " + ret_val);
		
		return ret_val;
		
	}
	
	/**
	 * Return the asset file name.
	 * 
	 * @param _asset Asset information.
	 * @return The asset file name.
	 */
	public String getAssetFilename(Asset _asset) {
		String ret_val = "";
		
		Preconditions.checkNotNull(_asset);
		Preconditions.checkArgument(false == _asset.getUid().isEmpty(), "The asset uid must not be empty.");
		Preconditions.checkArgument(false == _asset.getLabel().isEmpty(), "The asset label must not be empty.");
		
		ret_val = _asset.getUid() + "-" + _asset.getLabel();
		return ret_val;
	}
	
	/**
	 * Return the asset full file name.
	 * 
	 * @param _asset Asset information.
	 * @return The asset full file name with path.
	 */
	public String getAssetFullFilename(Asset _asset) {
		String ret_val = "";
		
		Preconditions.checkNotNull(_asset);
		Preconditions.checkArgument(false == _asset.getUid().isEmpty(), "The asset uid must not be empty.");
		Preconditions.checkArgument(false == _asset.getLabel().isEmpty(), "The asset label must not be empty.");
		
		ret_val = 	Application.getApp().getBasePath() + "/" + 
					ASSET_DATA_PATH + "/" + 
					getAssetFilename(_asset);
		return ret_val;
	}
	
	/**
	 * Prepare all system factory asset.
	 * 
	 * @throws Exception If somethings goes wrong.
	 */
	public void prepareSystemFactoryAsset() throws Exception {
		
		m_logger.info("Building the system factory asset ...");
		
		String assets [][] = SystemFactoryAsset.AssetItems;
		for (int i=0 ; i<assets.length ; i++) {
			Asset asset = new Asset(assets[i][SystemFactoryAsset.UID], 
									assets[i][SystemFactoryAsset.LABEL], 
									AssetType.IMAGE, 
									AssetMode.SYSTEM);
			
			// build filename full path
			String filename = getAssetFullFilename(asset);
			
			File file = new File(filename);
			if (false == file.exists()) { 
				
				// read file
				File image = new File(getClass().getResource("/" + asset.getLabel()).getFile());
				byte[] bytes = null;
				bytes = Files.toByteArray(image);
				
				// add asset
				addAsset(asset, ByteString.copyFrom(bytes));
				
				m_logger.info(asset.toString());
			}
		}
		
		m_logger.info("The system factory asset completed.");
	}
}
