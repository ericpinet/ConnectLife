/**
 *  CmdUpdateAsset.java
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
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.xnap.commons.i18n.I18n;

import com.clapi.data.Asset;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.asset.AssetManager;
import com.connectlife.coreserver.environment.data.DataManagerNodeFactory;
import com.google.api.client.util.Preconditions;

/**
 * Command to update a asset (file) in the environment.
 * 
 * @author ericpinet
 * <br> 2016-08-03
 */
public class CmdDeleteAsset extends CmdDefault {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(CmdDeleteAsset.class);
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
	/**
	 * Asset to add in the environment.
	 */
	private Asset m_asset;
	
	/**
	 * Default constructor.
	 *  
	 * @param _asset Asset to add in the environment.
	 */
	public CmdDeleteAsset (Asset _asset){
		m_asset = _asset;
	}
	
	/**
	 * Execute the cmd on the environment.
	 * 
	 * @throws Exception Exception when something goes wrong.
	 * @see com.connectlife.coreserver.environment.cmd.Cmd#execute()
	 */
	@Override
	public void execute() throws Exception {
		
		m_logger.info(i18n.tr("Execution start ..."));
		
		Preconditions.checkNotNull(m_asset, i18n.tr("Error! It's not possible to delete null asset in the environment."));
		Preconditions.checkArgument(false == m_asset.getUid().isEmpty(), i18n.tr("Error! It's not possible to delete a asset with a empty UID."));
				
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// get the asset manager
		AssetManager asset_manager = m_context.getAssetManager();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
						
			Node node_asset = graph.findNode( Consts.LABEL_ASSET, 
											Consts.UID, 
											m_asset.getUid() );
			
			if (null != node_asset) {
				
				// delete the data in the graph
				DataManagerNodeFactory.deleteNodeWithChildren(graph, Consts.LABEL_ASSET, m_asset.getUid());
				
				// delete binary data
				asset_manager.deleteAsset(m_asset);
			
				// set the data change
				this.m_data_is_changed = true;
				
			}
			else {
				m_logger.error(i18n.tr("Asset not found: ") + m_asset.toString());
				throw new Exception(i18n.tr("Asset not found: ") + m_asset.toString());
			}
			
			// display info in log
			m_logger.info(m_asset.toString());
			
			// set the data change
			this.m_data_is_changed = true;

			tx.success();
		}
		
		m_logger.info(i18n.tr("Execution completed."));
	}	
}
