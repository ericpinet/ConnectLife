/**
 *  DataManagerFactoryTest.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-06-24.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.test.coreserver.environment.data;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.clapi.data.Data;
import com.clapi.data.Address;
import com.clapi.data.Address.AddressType;
import com.clapi.data.Asset;
import com.clapi.data.Asset.AssetMode;
import com.clapi.data.Asset.AssetType;
import com.clapi.data.Email;
import com.clapi.data.Email.EmailType;
import com.clapi.data.Home;
import com.clapi.data.Person;
import com.clapi.data.Phone;
import com.clapi.data.Phone.PhoneType;
import com.connectlife.coreserver.Consts;
import com.connectlife.coreserver.environment.data.DataManagerFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataManagerFactory.class)
@PowerMockIgnore("javax.management.*")
/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-06-24
 */
public class DataManagerFactoryTest {

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
	public void testprepareDataNullGraph() {
		try {
			DataManagerFactory.prepareData(null);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testprepareData() {
		
		PowerMockito.spy(DataManagerFactory.class);
		
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Transaction tx = Mockito.mock(Transaction.class);
		@SuppressWarnings("unchecked")
		ResourceIterator<Node> ipersons = (ResourceIterator<Node>) Mockito.mock(ResourceIterator.class);
		Node person = Mockito.mock(Node.class);
		@SuppressWarnings("unchecked")
		ResourceIterator<Node> ihomes = (ResourceIterator<Node>) Mockito.mock(ResourceIterator.class);
		Node home = Mockito.mock(Node.class);
		@SuppressWarnings("unchecked")
		ResourceIterator<Node> iasserts = (ResourceIterator<Node>) Mockito.mock(ResourceIterator.class);
		Node aassert = Mockito.mock(Node.class);
		
		Mockito.when(graph.beginTx()).thenReturn(tx);
		Mockito.when(graph.findNodes(Consts.LABEL_PERSON)).thenReturn(ipersons);
		Mockito.when(graph.findNodes(Consts.LABEL_HOME)).thenReturn(ihomes);
		Mockito.when(graph.findNodes(Consts.LABEL_ASSET)).thenReturn(iasserts);
		
		Mockito.when(ipersons.hasNext()).thenReturn(true,false);
		Mockito.when(ipersons.next()).thenReturn(person);
		
		Mockito.when(ihomes.hasNext()).thenReturn(true,false);
		Mockito.when(ihomes.next()).thenReturn(home);
		
		Mockito.when(iasserts.hasNext()).thenReturn(true,false);
		Mockito.when(iasserts.next()).thenReturn(aassert);
		
		try {
			PowerMockito.doReturn(new Person("123","eric")).when(DataManagerFactory.class, "buildPerson", graph, person);
			PowerMockito.doReturn(new Home("123","home")).when(DataManagerFactory.class, "buildHome", graph, home);
			PowerMockito.doReturn(new Asset("123","asset", null, null)).when(DataManagerFactory.class, "buildAsset", aassert);
			
		} catch (Exception e1) {
			fail();
		}
		
		try {
			Data data = DataManagerFactory.prepareData(graph);
			assertTrue(data.getPersons().size()==1);
			assertTrue(data.getHomes().size()==1);
			assertTrue(data.getAssets().size()==1);
			PowerMockito.verifyStatic();
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testprepareDataEmpty() {
		
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Transaction tx = Mockito.mock(Transaction.class);
		@SuppressWarnings("unchecked")
		ResourceIterator<Node> ipersons = (ResourceIterator<Node>) Mockito.mock(ResourceIterator.class);
		@SuppressWarnings("unchecked")
		ResourceIterator<Node> ihomes = (ResourceIterator<Node>) Mockito.mock(ResourceIterator.class);
		@SuppressWarnings("unchecked")
		ResourceIterator<Node> iasserts = (ResourceIterator<Node>) Mockito.mock(ResourceIterator.class);
		
		Mockito.when(graph.beginTx()).thenReturn(tx);
		Mockito.when(graph.findNodes(Consts.LABEL_PERSON)).thenReturn(ipersons);
		Mockito.when(graph.findNodes(Consts.LABEL_HOME)).thenReturn(ihomes);
		Mockito.when(graph.findNodes(Consts.LABEL_ASSET)).thenReturn(iasserts);
		
		Mockito.when(ipersons.hasNext()).thenReturn(false);
		Mockito.when(ihomes.hasNext()).thenReturn(false);
		Mockito.when(iasserts.hasNext()).thenReturn(false);
		
		try {
			Data data = DataManagerFactory.prepareData(graph);
			assertTrue(data.getPersons().size()==0);
			assertTrue(data.getHomes().size()==0);
			assertTrue(data.getAssets().size()==0);
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testbuildPerson() {
		
		PowerMockito.spy(DataManagerFactory.class);
		
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Node person = Mockito.mock(Node.class);
		Node email = Mockito.mock(Node.class);
		Node phone = Mockito.mock(Node.class);
		Node address = Mockito.mock(Node.class);
		Relationship relation = Mockito.mock(Relationship.class);
		@SuppressWarnings("unchecked")
		Iterator<Relationship> it = (Iterator<Relationship>) Mockito.mock(Iterator.class);
		@SuppressWarnings("unchecked")
		Iterable<Relationship> relations = (Iterable<Relationship>) Mockito.mock(Iterable.class);
		
		Mockito.when(person.hasLabel(Consts.LABEL_PERSON)).thenReturn(true);
		Mockito.when(person.getId()).thenReturn((long) -1);
		
		Mockito.when(person.getProperty(Consts.UID)).thenReturn("123");
		Mockito.when(person.getProperty(Consts.PERSON_FIRSTNAME)).thenReturn("qiaomei");
		Mockito.when(person.getProperty(Consts.PERSON_LASTNAME)).thenReturn("wang3");
		Mockito.when(person.getProperty(Consts.PERSON_IMAGEUID)).thenReturn("123.html");
		
		Mockito.when(graph.beginTx()).thenReturn(tx);
		Mockito.when(person.getRelationships(Consts.RelTypes.CONTAINS)).thenReturn(relations);
		Mockito.when(relations.iterator()).thenReturn(it);
		Mockito.when(it.hasNext()).thenReturn(true,true,true,false);
		Mockito.when(it.next()).thenReturn(relation, relation, relation);
		Mockito.when(relation.getEndNode()).thenReturn(email, phone, address);
		
		Mockito.when(email.hasLabel(Consts.LABEL_EMAIL)).thenReturn(true);
		Mockito.when(email.hasLabel(Consts.LABEL_PHONE)).thenReturn(false);
		Mockito.when(email.hasLabel(Consts.LABEL_ADDRESS)).thenReturn(false);
		
		Mockito.when(phone.hasLabel(Consts.LABEL_EMAIL)).thenReturn(false);
		Mockito.when(phone.hasLabel(Consts.LABEL_PHONE)).thenReturn(true);
		Mockito.when(phone.hasLabel(Consts.LABEL_ADDRESS)).thenReturn(false);
		
		Mockito.when(address.hasLabel(Consts.LABEL_EMAIL)).thenReturn(false);
		Mockito.when(address.hasLabel(Consts.LABEL_PHONE)).thenReturn(false);
		Mockito.when(address.hasLabel(Consts.LABEL_ADDRESS)).thenReturn(true);
		
		
		try {			
			PowerMockito.doReturn(new Email("","", EmailType.PERSONAL)).when(DataManagerFactory.class, "buildEmail", email);
			PowerMockito.doReturn(new Phone("","", PhoneType.HOME)).when(DataManagerFactory.class, "buildPhone", phone);
			PowerMockito.doReturn(new Address("", AddressType.HOME, "")).when(DataManagerFactory.class, "buildAddress", address);
			
		} catch (Exception e1) {
			fail();
		}
		
		try {
			Person aperson = DataManagerFactory.buildPerson(graph, person);
			assertTrue(aperson.getEmails().size()==1);
			assertTrue(aperson.getPhones().size()==1);
			assertTrue(aperson.getAddresses().size()==1);
			PowerMockito.verifyStatic();
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testbuildPersonException() {
		
		GraphDatabaseService graph = Mockito.mock(GraphDatabaseService.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Node person = Mockito.mock(Node.class);
		Node invalid = Mockito.mock(Node.class);
		Relationship relation = Mockito.mock(Relationship.class);
		@SuppressWarnings("unchecked")
		Iterator<Relationship> it = (Iterator<Relationship>) Mockito.mock(Iterator.class);
		@SuppressWarnings("unchecked")
		Iterable<Relationship> relations = (Iterable<Relationship>) Mockito.mock(Iterable.class);
		
		Mockito.when(person.hasLabel(Consts.LABEL_PERSON)).thenReturn(true);
		Mockito.when(person.getId()).thenReturn((long) -1);
		
		Mockito.when(person.getProperty(Consts.UID)).thenReturn("123");
		Mockito.when(person.getProperty(Consts.PERSON_FIRSTNAME)).thenReturn("qiaomei");
		Mockito.when(person.getProperty(Consts.PERSON_LASTNAME)).thenReturn("wang3");
		Mockito.when(person.getProperty(Consts.PERSON_IMAGEUID)).thenReturn("123.html");
		
		Mockito.when(graph.beginTx()).thenReturn(tx);
		Mockito.when(person.getRelationships(Consts.RelTypes.CONTAINS)).thenReturn(relations);
		Mockito.when(relations.iterator()).thenReturn(it);
		Mockito.when(it.hasNext()).thenReturn(true);
		Mockito.when(it.next()).thenReturn(relation);
		Mockito.when(relation.getEndNode()).thenReturn(invalid);
		
		try {
			DataManagerFactory.buildPerson(graph, person);
			fail();
			
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testBuildAddress() {
		
		Node address = Mockito.mock(Node.class);
		
		Mockito.when(address.hasLabel(Consts.LABEL_ADDRESS)).thenReturn(true);
		Mockito.when(address.getProperty(Consts.ADDRESS_TYPE)).thenReturn(Consts.ADDRESS_TYPE_HOME, Consts.ADDRESS_TYPE_WORK, Consts.ADDRESS_TYPE_OTHER);
		Mockito.when(address.getProperty(Consts.UID)).thenReturn("12345");
		Mockito.when(address.getProperty(Consts.ADDRESS_STREET)).thenReturn("2353");
		Mockito.when(address.getProperty(Consts.ADDRESS_CITY)).thenReturn("quebec");
		Mockito.when(address.getProperty(Consts.ADDRESS_REGION)).thenReturn("qc");
		Mockito.when(address.getProperty(Consts.ADDRESS_ZIPCODE)).thenReturn("111111");
		Mockito.when(address.getProperty(Consts.ADDRESS_COUNTRY)).thenReturn("canada");
		
		for (int i=0 ; i<3 ; i++ ) {
			try {
				Address add = DataManagerFactory.buildAddress(address);
				assertTrue(add.getUid().equals("12345"));
				assertTrue(add.getStreet().equals("2353"));
				assertTrue(add.getCity().equals("quebec"));
				assertTrue(add.getRegion().equals("qc"));
				assertTrue(add.getZipcode().equals("111111"));
				assertTrue(add.getCountry().equals("canada"));
			} catch (Exception e) {
				fail();
			}
		}
	}
	
	@Test
	public void testBuildAddressException1() {
		
		Node address = Mockito.mock(Node.class);
		
		Mockito.when(address.hasLabel(Consts.LABEL_ADDRESS)).thenReturn(false);
		
		try {
			DataManagerFactory.buildAddress(address);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testBuildAddressException2() {
		
		Node address = Mockito.mock(Node.class);
		
		Mockito.when(address.hasLabel(Consts.LABEL_ADDRESS)).thenReturn(true);
		Mockito.when(address.getProperty(Consts.ADDRESS_TYPE)).thenReturn("invalid");
	
		try {
			DataManagerFactory.buildAddress(address);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testBuildEmail() {
		
		Node email = Mockito.mock(Node.class);
		
		Mockito.when(email.hasLabel(Consts.LABEL_EMAIL)).thenReturn(true);
		Mockito.when(email.getProperty(Consts.EMAIL_TYPE)).thenReturn(Consts.EMAIL_TYPE_PERSONAL, Consts.EMAIL_TYPE_WORK, Consts.EMAIL_TYPE_OTHER);
		Mockito.when(email.getProperty(Consts.UID)).thenReturn("12345");
		Mockito.when(email.getProperty(Consts.EMAIL_EMAIL)).thenReturn("qiaomei.wang@gmail.com");
		
		for (int i=0 ; i<3 ; i++ ) {
			try {
				Email aemail = DataManagerFactory.buildEmail(email);
				assertTrue(aemail.getUid().equals("12345"));
				assertTrue(aemail.getEmail().equals("qiaomei.wang@gmail.com"));
				
			} catch (Exception e) {
				fail();
			}
		}
	}
	
	@Test
	public void testBuildEmailException1() {
		
		Node email = Mockito.mock(Node.class);
		
		Mockito.when(email.hasLabel(Consts.LABEL_EMAIL)).thenReturn(false);
		
		try {
			DataManagerFactory.buildEmail(email);
			fail();
			
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testBuildEmailException2() {
		
		Node email = Mockito.mock(Node.class);
		
		Mockito.when(email.hasLabel(Consts.LABEL_EMAIL)).thenReturn(true);
		Mockito.when(email.getProperty(Consts.EMAIL_TYPE)).thenReturn("invalid");
		
		try {
			DataManagerFactory.buildEmail(email);
			fail();
			
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testBuildPhone() {
		
		Node phone = Mockito.mock(Node.class);
		
		Mockito.when(phone.hasLabel(Consts.LABEL_PHONE)).thenReturn(true);
		Mockito.when(phone.getProperty(Consts.PHONE_TYPE)).thenReturn(Consts.PHONE_TYPE_HOME, Consts.PHONE_TYPE_WORK, Consts.PHONE_TYPE_OTHER, Consts.PHONE_TYPE_CELL);
		Mockito.when(phone.getProperty(Consts.UID)).thenReturn("12345");
		Mockito.when(phone.getProperty(Consts.PHONE_NUMBER)).thenReturn("4188881111");
		
		for (int i=0 ; i<4 ; i++ ) {
			try {
				Phone aphone = DataManagerFactory.buildPhone(phone);
				assertTrue(aphone.getUid().equals("12345"));
				assertTrue(aphone.getPhone().equals("4188881111"));
				
			} catch (Exception e) {
				fail();
			}
		}
	}
	
	@Test
	public void testBuildPhoneException1() {
		
		Node phone = Mockito.mock(Node.class);
		
		Mockito.when(phone.hasLabel(Consts.LABEL_PHONE)).thenReturn(false);
		
		try {
			DataManagerFactory.buildPhone(phone);
			fail();
			
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testBuildPhoneException2() {
		
		Node phone = Mockito.mock(Node.class);
		
		Mockito.when(phone.hasLabel(Consts.LABEL_PHONE)).thenReturn(true);
		Mockito.when(phone.getProperty(Consts.PHONE_TYPE)).thenReturn("invalid");
		
		try {
			DataManagerFactory.buildPhone(phone);
			fail();
			
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testBuildAssert() {
		
		Node a_assert = Mockito.mock(Node.class);
		
		Mockito.when(a_assert.hasLabel(Consts.LABEL_ASSET)).thenReturn(true);
		Mockito.when(a_assert.getProperty(Consts.UID)).thenReturn("12345");
		Mockito.when(a_assert.getProperty(Consts.ASSET_LABEL)).thenReturn("a assert");
		Mockito.when(a_assert.getProperty(Consts.ASSET_TYPE)).thenReturn(Consts.ASSET_TYPE_IMAGE, Consts.ASSET_TYPE_FILE, Consts.ASSET_TYPE_FILE, Consts.ASSET_TYPE_OTHER);
		Mockito.when(a_assert.getProperty(Consts.ASSET_MODE)).thenReturn(Consts.ASSET_MODE_SYSTEM, Consts.ASSET_MODE_SYSTEM, Consts.ASSET_MODE_USER);
		
		for (int i=0 ; i<3 ; i++ ) {
			try {
				Asset aassert = DataManagerFactory.buildAsset(a_assert);
				assertTrue(aassert.getUid().equals("12345"));
				assertTrue(aassert.getLabel().equals("a assert"));
				
				if (i==0) {
					assertTrue(aassert.getType().equals(AssetType.IMAGE));
					assertTrue(aassert.getMode().equals(AssetMode.SYSTEM));
				}
				else if (i==1) {
					assertTrue(aassert.getType().equals(AssetType.FILE));
					assertTrue(aassert.getMode().equals(AssetMode.SYSTEM));
				}
				else if (i==2) {
					assertTrue(aassert.getType().equals(AssetType.OTHER));
					assertTrue(aassert.getMode().equals(AssetMode.USER));
				}

			} catch (Exception e) {
				fail();
			}
		}
	}
	
	@Test
	public void testBuildAssertException1() {
		
		Node a_assert = Mockito.mock(Node.class);
		
		Mockito.when(a_assert.hasLabel(Consts.LABEL_ASSET)).thenReturn(false);
		
		try {
			DataManagerFactory.buildAsset(a_assert);
			fail();
			
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testBuildAssertException2() {
		
		Node a_assert = Mockito.mock(Node.class);
		
		Mockito.when(a_assert.hasLabel(Consts.LABEL_ASSET)).thenReturn(true);
		Mockito.when(a_assert.getProperty(Consts.UID)).thenReturn("12345");
		Mockito.when(a_assert.getProperty(Consts.ASSET_LABEL)).thenReturn("a assert");
		Mockito.when(a_assert.getProperty(Consts.ASSET_TYPE)).thenReturn("INVALID");
		Mockito.when(a_assert.getProperty(Consts.ASSET_MODE)).thenReturn(Consts.ASSET_MODE_SYSTEM);
		
		try {
			DataManagerFactory.buildAsset(a_assert);
			fail();
			
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testBuildAssertException3() {
		
		Node a_assert = Mockito.mock(Node.class);
		
		Mockito.when(a_assert.hasLabel(Consts.LABEL_ASSET)).thenReturn(true);
		Mockito.when(a_assert.getProperty(Consts.UID)).thenReturn("12345");
		Mockito.when(a_assert.getProperty(Consts.ASSET_LABEL)).thenReturn("a assert");
		Mockito.when(a_assert.getProperty(Consts.ASSET_TYPE)).thenReturn(Consts.ASSET_TYPE_IMAGE);
		Mockito.when(a_assert.getProperty(Consts.ASSET_MODE)).thenReturn("INVALID");
		
		try {
			DataManagerFactory.buildAsset(a_assert);
			fail();
			
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

}
