/**
 *  CacheFile.java
 *  simulator
 *
 *  Created by ericpinet on 2016-09-05.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator.cache;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.connectlife.simulator.PersonWindow;

/**
 * Cache m_file from a url.
 * 
 * @author ericpinet
 * <br> 2016-09-05
 */
public class CacheFile {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(PersonWindow.class);
	
	/**
	 * Local m_file
	 */
	private File m_file;
	
	/**
	 * Property for the temporary directory
	 */
	private final String _PROPERTY_ = "java.io.tmpdir";
	
	/**
	 * Default constructor
	 * 
	 * @param _url Url of the remote image.
	 * @param _filename Filename of the image (ex: image.png)
	 */
	public CacheFile(String _url, String _filename) {
	    
		m_file = new File(System.getProperty(_PROPERTY_) + "/" + _filename);
		
		// check if image already exist in cache
		if (false == m_file.exists()) {
			
			// if image is not already cached, cache it.
			URL url = null;
			try {
				url = new URL(_url);
			} catch (MalformedURLException e1) {
				m_logger.error(e1.getMessage());
				e1.printStackTrace();
			}
			try {
				FileUtils.copyURLToFile(url, m_file);
			} catch (IOException e1) {
				m_logger.error(e1.getMessage());
				e1.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Return the m_file
	 * 
	 * @return
	 */
	public File getFile() {
		return m_file;
	}
}
