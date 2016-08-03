/**
 *  CmdAddAsset.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-08-03.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment.cmd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import com.clapi.data.Asset;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.environment.asset.AssetManager;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.api.client.util.Preconditions;
import com.google.protobuf.ByteString;

/**
 * Command to add a new asset (file) in the environment.
 * 
 * @author ericpinet
 * <br> 2016-08-03
 */
public class CmdAddAsset extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdAddAsset.class);
	
	/**
	 * Asset to add in the environment.
	 */
	private Asset m_asset;
	
	/**
	 * Asset binary data.
	 */
	private ByteString m_data;
	
	/**
	 * Default constructor.
	 *  
	 * @param _asset Asset to add in the environment.
	 * @param _data Binary data of the asset.
	 */
	public CmdAddAsset (Asset _asset, ByteString _data){
		m_asset = _asset;
		m_data = _data;
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
		
		Preconditions.checkNotNull(m_asset, "Error! It's not possible to add null asset in the environment.");
		Preconditions.checkNotNull(m_data, "Error! It's not possible to add null data asset in the environment.");
		Preconditions.checkArgument(m_asset.getUid().isEmpty(), "Error! It's not possible to add a asset with a UID.");
		Preconditions.checkArgument(false == m_asset.getLabel().isEmpty(), "Error! It's not possible to add a asset with empty label.");
				
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// get the asset manager
		AssetManager asset_manager = m_context.getAssetManager();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
						
			// create the uid for the asset
			m_asset.setUid(UIDGenerator.getUID());
			
			try {
				// build asset node
				DataManagerNodeFactory.buildAssetNode(graph, m_asset);
				
				// save asset data
				asset_manager.addAsset(m_asset, m_data);
			} 
			catch (Exception e) {
				// if something wrong happened, delete node and file
				DataManagerNodeFactory.deleteNodeWithChildren(graph, Consts.LABEL_ASSET, m_asset.getUid());
				asset_manager.deleteAsset(m_asset);
				throw e;
			}
			
			// display info in log
			m_logger.info(m_asset.toString());
			
			// set the data change
			this.m_data_is_changed = true;

			tx.success();
		}
		
		m_logger.info("Execution completed.");
	}	
}
