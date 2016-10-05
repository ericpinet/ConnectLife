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
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.xnap.commons.i18n.I18n;

import com.clapi.data.Asset;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.asset.AssetManager;
import com.connectlife.coreserver.environment.data.DataManagerFactory;
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
	private Logger m_logger = LogManager.getLogger(getClass().getName());
	
	/**
	 * Initialization of translation system.
	 */
	private static I18n i18n = Application.i18n;
	
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
		
		m_logger.info(i18n.tr("Execution start ..."));
		
		Preconditions.checkNotNull(m_asset, i18n.tr("Error! It's not possible to get url of null asset in the environment."));
		Preconditions.checkArgument(false == m_asset.getUid().isEmpty(), i18n.tr("Error! It's not possible to get url of asset with a empty UID."));
		
		// get the graph data
		GraphDatabaseService graph = m_context.getDataManager().getGraph();
		
		// get the asset manager
		AssetManager asset_manager = m_context.getAssetManager();
		
		// begin transaction
		try ( Transaction tx = graph.beginTx() ) {
						
			Node node_asset = graph.findNode( 	Consts.LABEL_ASSET, 
												Consts.UID, 
												m_asset.getUid() );
			
			if (null != node_asset) {
				
				// Update the data in the graph
				m_asset = DataManagerFactory.buildAsset(node_asset);
				
				// Update binary data
				m_url = asset_manager.getAssetUrl(m_asset);
				
				// display info in log
				m_logger.info(m_asset.toString() + i18n.tr("\nUrl:") + m_url);
			}
			else {
				m_logger.error(i18n.tr("Asset not found: ") + m_asset.toString());
				throw new Exception(i18n.tr("Asset not found: ") + m_asset.toString());
			}
			
			// display info in log
			m_logger.info(m_asset.toString());

			tx.success();
		}
		
		m_logger.info(i18n.tr("Execution completed."));
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
