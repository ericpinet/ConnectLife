/**
 *  SystemFactoryAssetTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-08-03.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment.asset;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.clapi.data.*;
import com.clapi.data.Accessory.AccessoryType;
import com.connectlife.coreserver.environment.asset.SystemFactoryAsset;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-08-03
 */
public class SystemFactoryAssetTest {

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
	public void testAddress() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Address(null, null, null));
		assertTrue(uid.equals("b1f4d4c9-5e69-48db-9a78-c20475a6b9ec"));
	}
	
	@Test
	public void testAutomaticDoor() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.AUTOMATIC_DOOR, null));
		assertTrue(uid.equals("4490c19a-0a7f-4816-ba67-78f6eff65212"));
	}
	
	@Test
	public void testControlBoard() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.CONTROL_BOARD, null));
		assertTrue(uid.equals("0ad400fe-6a4c-4908-8480-767ccade713a"));
	}
	
	@Test
	public void testEmail() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Email(null, null, null));
		assertTrue(uid.equals("0a81f5fe-5275-460e-8d71-6338dae542ef"));
	}
	
	@Test
	public void testFan() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.FAN, null));
		assertTrue(uid.equals("2ffb35dd-4613-41bb-96dd-8f76bdc38ee6"));
	}
	
	@Test
	public void testHome() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Home(null, null));
		assertTrue(uid.equals("c7aa8c15-69f6-41aa-91be-b0b49774f0ad"));
	}
	
	@Test
	public void testLight() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.LIGHT, null));
		assertTrue(uid.equals("77f73780-7afa-4883-a284-e08055b15f31"));
		uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.LIGHT_COLORED, null));
		assertTrue(uid.equals("77f73780-7afa-4883-a284-e08055b15f31"));
		uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.LIGHT_COLORED_DIMMABLE, null));
		assertTrue(uid.equals("77f73780-7afa-4883-a284-e08055b15f31"));
		uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.LIGHT_DIMMABLE, null));
		assertTrue(uid.equals("77f73780-7afa-4883-a284-e08055b15f31"));
	}
	
	@Test
	public void testLock() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.LOCK_MECHANISM, null));
		assertTrue(uid.equals("e611bbdc-2c7f-4626-80a6-1d561f2646aa"));
	}
	
	@Test
	public void testPerson() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Person(null, null));
		assertTrue(uid.equals("6c2cc85b-3901-4b7d-a46c-3a0404595ea1"));
	}
	
	@Test
	public void testPhone() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Phone(null, null, null));
		assertTrue(uid.equals("4d6e4a77-3647-4ddd-8b45-6e706455fb71"));
	}
	
	@Test
	public void testRoom() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Room(null, null, null, null));
		assertTrue(uid.equals("592a9314-a8a7-47cc-99bd-8755cb640071"));
	}
	
	@Test
	public void testSwitch() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.SWITCH, null));
		assertTrue(uid.equals("e5bf7ed4-cc65-443e-957d-58987d4eb92f"));
	}
	
	@Test
	public void testThermostat() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.THERMOSTAT, null));
		assertTrue(uid.equals("aeea8b2b-ebfa-41df-b5ed-7cac1a7d308f"));
	}
	
	@Test
	public void testCam() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Accessory(null, null, null, null, null, null, null, AccessoryType.CAM, null));
		assertTrue(uid.equals("f41d60f1-5d75-4f33-b997-c49b4c74f6d4"));
	}
	
	@Test
	public void testZone() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Zone(null, null, null, null));
		assertTrue(uid.equals("5b7d6933-3d83-4529-8f8c-6a7d61d38d7d"));
	}
	
	@Test
	public void testObj() {
		String uid = SystemFactoryAsset.getAssetUidByClassType(new Object());
		assertTrue(uid.equals(""));
	}

}
