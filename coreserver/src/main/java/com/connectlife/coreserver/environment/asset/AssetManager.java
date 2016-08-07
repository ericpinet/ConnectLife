/**
 *  AssetManager.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-07-25.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.asset;

import com.clapi.data.Asset;
import com.google.protobuf.ByteString;

/**
 * Manager of assets for the environment data. All files are managed here.
 * 
 * @author ericpinet
 * <br> 2016-07-25
 */
public interface AssetManager {
	
	/**
	 * Initialization the asset manager.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init();

	/**
	 * Return True is the asset manager is correctly initialized.
	 * 
	 * @return True if the asset manager is correctly initialized.
	 */
	public boolean isInit();

	/**
	 * UnInitialize the asset manager. Return in empty state ready to initialize again.
	 */
	public void unInit();
	
	/**
	 * Save binary data of the asset in the environment file storage.
	 * 
	 * @param _asset Asset information of the file.
	 * @param _data Binary data of the file.
	 * @throws Exception If something goes wrong.
	 */
	public void addAsset(Asset _asset, ByteString _data) throws Exception;
	
	/**
	 * Update the asset binary data in the environment file storage.
	 * 
	 * @param _asset Asset information of the file.
	 * @param _data Binary data of the file.
	 * @throws Exception If something goes wrong.
	 */
	public void updateAsset(Asset _asset, ByteString _data) throws Exception;
	
	/**
	 * Delete the asset binary data from the environment file storage.
	 * 
	 * @param _asset Asset information of the file.
	 * @throws Exception If somethings goes wrong.
	 */
	public void deleteAsset(Asset _asset) throws Exception;
	
	/**
	 * Return the URL of the binary data of the file.
	 *  
	 * @param _asset Asset information of the file.
	 * @return The url of the binary data.
	 * @throws Exception If something goes wrong.
	 */
	public String getAssetUrl(Asset _asset) throws Exception;
	
}
