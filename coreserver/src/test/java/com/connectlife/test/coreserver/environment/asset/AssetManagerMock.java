/**
 *  AssetManagerMock.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-08-02.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment.asset;

import com.clapi.data.Asset;
import com.connectlife.coreserver.environment.asset.AssetManager;
import com.google.protobuf.ByteString;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-08-02
 */
public class AssetManagerMock implements AssetManager {

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#init()
	 */
	@Override
	public boolean init() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#isInit()
	 */
	@Override
	public boolean isInit() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#unInit()
	 */
	@Override
	public void unInit() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param _asset
	 * @param _data
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#addAsset(com.clapi.data.Asset, com.google.protobuf.ByteString)
	 */
	@Override
	public void addAsset(Asset _asset, ByteString _data) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @param _asset
	 * @param _data
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#updateAsset(com.clapi.data.Asset, com.google.protobuf.ByteString)
	 */
	@Override
	public void updateAsset(Asset _asset, ByteString _data) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @param _asset
	 * @return
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#getAssetUrl(com.clapi.data.Asset)
	 */
	@Override
	public String getAssetUrl(Asset _asset) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param _asset
	 * @throws Exception
	 * @see com.connectlife.coreserver.environment.asset.AssetManager#deleteAsset(com.clapi.data.Asset)
	 */
	@Override
	public void deleteAsset(Asset _asset) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
