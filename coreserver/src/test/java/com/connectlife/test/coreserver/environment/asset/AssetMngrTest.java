/**
 *  AssetMngrTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-08-02.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment.asset;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.clapi.data.Asset;
import com.clapi.data.Asset.AssetMode;
import com.clapi.data.Asset.AssetType;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.asset.AssetMngr;
import com.connectlife.test.coreserver.ApplicationInjectTest;
import com.google.common.io.Files;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.protobuf.ByteString;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-08-02
 */
public class AssetMngrTest {
	
	private static String _IMAGE_FILE_1_ = "home.png";
	
	private static String _IMAGE_FILE_2_ = "zone.png";
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitWork() {
		AssetMngr asset = new AssetMngr();
		assertTrue(asset.init());
	}
	
	@Test
	public void testInitTwice() {
		AssetMngr asset = new AssetMngr();
		assertTrue(asset.init());
		assertFalse(asset.init());
	}
	
	@Test
	public void testUnInit() {
		AssetMngr asset = new AssetMngr();
		asset.unInit();
		assertTrue(asset.init());
		asset.unInit();
		asset.unInit();
	}
	
	@Test
	public void testisInit() {
		AssetMngr asset = new AssetMngr();
		assertFalse(asset.isInit());
		assertTrue(asset.init());
		assertTrue(asset.isInit());
		asset.unInit();
		assertFalse(asset.isInit());
	}
	
	@Test
	public void testAddDeleteAsset() {
		
		// prepare app object
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		app.startupTest();
		
		// prepare asset file information
		Asset asset = new Asset("12345", _IMAGE_FILE_1_, AssetType.IMAGE, AssetMode.SYSTEM);
		
		// init asset manager
		AssetMngr asset_mngr = new AssetMngr();
		
		// get url
		try {
			asset_mngr.getAssetUrl(asset);
			fail();
		} catch (Exception e) {
		}
		
		assertTrue(asset_mngr.init());
		
		// read file
		File image = new File(getClass().getResource("/" + _IMAGE_FILE_1_).getFile());
		byte[] bytes = null;
		try {
			bytes = Files.toByteArray(image);
		} catch (IOException e) {
			fail();
		}
		
		// test save
		try {
			asset_mngr.addAsset(asset, ByteString.copyFrom(bytes));
			asset_mngr.deleteAsset(asset);
			asset_mngr.addAsset(asset, ByteString.copyFrom(bytes));
		} catch (Exception e) {
			fail();
		}
		
		// Get asset full file name
		String filename = asset_mngr.getAssetFullFilename(asset);
		
		// check if file exist
		File asset_file = new File(filename);
		assertTrue( asset_file.exists() && (false == asset_file.isDirectory()) );
		
		// get url
		try {
			assertNotNull(asset_mngr.getAssetUrl(asset));
		} catch (Exception e) {
			fail();
		}
		
		// test save
		try {
			asset_mngr.addAsset(asset, ByteString.copyFrom(bytes));
			fail();
		} catch (Exception e) {
			// must return exception because file already exist
		}
		
		// remove file
		asset_file.delete();
		
		// get url
		try {
			asset_mngr.getAssetUrl(asset);
			fail();
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testUpdateAsset() {
		
		// prepare app object
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		app.startupTest();
		
		// prepare asset file information
		Asset asset = new Asset("12345", _IMAGE_FILE_1_, AssetType.IMAGE, AssetMode.SYSTEM);
		
		// init asset manager
		AssetMngr asset_mngr = new AssetMngr();
		assertTrue(asset_mngr.init());
		
		// read file
		File image = new File(getClass().getResource("/" + _IMAGE_FILE_1_).getFile());
		byte[] bytes = null;
		try {
			bytes = Files.toByteArray(image);
		} catch (IOException e) {
			fail();
		}
		
		
		try {
			asset_mngr.updateAsset(asset, ByteString.copyFrom(bytes));
			fail();
		} catch (Exception e) {
			// throw exception cause file doesn't exist.
		}
		
		// test save
		try {
			asset_mngr.addAsset(asset, ByteString.copyFrom(bytes));
		} catch (Exception e) {
			fail();
		}
		
		// Get asset full file name
		String filename = asset_mngr.getAssetFullFilename(asset);
		
		// check if file exist
		File asset_file = new File(filename);
		assertTrue( asset_file.exists() && (false == asset_file.isDirectory()) );
		
		// read other file image
		image = new File(getClass().getResource("/" + _IMAGE_FILE_2_).getFile());
		try {
			bytes = Files.toByteArray(image);
		} catch (IOException e) {
			fail();
		}
		
		try {
			asset_mngr.updateAsset(asset, ByteString.copyFrom(bytes));
		} catch (Exception e) {
			fail();
		}
		
		// check if file exist
		asset_file = new File(filename);
		assertTrue( asset_file.exists() && (false == asset_file.isDirectory()) );
		
		// remove file
		asset_file.delete();
	}
	
	@Test
	public void testAddAssetUnit() {
		
		// prepare asset file information
		Asset asset = new Asset("12345", _IMAGE_FILE_1_, AssetType.IMAGE, AssetMode.SYSTEM);
		
		// init asset manager
		AssetMngr asset_mngr = new AssetMngr();
		
		// read file
		File image = new File(getClass().getResource("/" + _IMAGE_FILE_1_).getFile());
		byte[] bytes = null;
		try {
			bytes = Files.toByteArray(image);
		} catch (IOException e) {
			fail();
		}
		
		// test save
		try {
			asset_mngr.addAsset(asset, ByteString.copyFrom(bytes));
			fail();
		} catch (Exception e) {
			
		}	
	}
	
	@Test
	public void testAddAssetNull() {
		
		// prepare app object
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		app.startupTest();
		
		// init asset manager
		AssetMngr asset_mngr = new AssetMngr();
		assertTrue(asset_mngr.init());
		
		// read file
		File image = new File(getClass().getResource("/" + _IMAGE_FILE_1_).getFile());
		byte[] bytes = null;
		try {
			bytes = Files.toByteArray(image);
		} catch (IOException e) {
			fail();
		}
		
		// test save
		try {
			asset_mngr.addAsset(null, ByteString.copyFrom(bytes));
			fail();
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testAddDataNull() {
		
		// prepare app object
		Injector injector = Guice.createInjector(new ApplicationInjectTest());
		final Application app = injector.getInstance(Application.class);
		app.startupTest();
		
		// prepare asset file information
		Asset asset = new Asset("12345", _IMAGE_FILE_1_, AssetType.IMAGE, AssetMode.SYSTEM);
		
		// init asset manager
		AssetMngr asset_mngr = new AssetMngr();
		assertTrue(asset_mngr.init());
		
		// test save
		try {
			asset_mngr.addAsset(asset, null);
			fail();
		} catch (Exception e) {
		}
	}
	
	
}
