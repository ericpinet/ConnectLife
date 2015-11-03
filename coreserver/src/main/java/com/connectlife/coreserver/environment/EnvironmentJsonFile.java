/**
 *  EnvironmentJsonFile.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

// external
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.jmdns.ServiceEvent;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.inject.Inject;
import java.util.Observable;

// internal
import com.connectlife.clapi.*;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.UIDGenerator;
import com.connectlife.coreserver.environment.discover.DiscoveryListner;
import com.connectlife.coreserver.environment.discover.DiscoveryService;

/**
 * Manager of the environment of the automation.
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class EnvironmentJsonFile extends Observable implements Environment, DiscoveryListner {
	
	/**
	 * Environment data path contain the data representing the user, home, 
	 * zones, rooms, accessories
	 */
	private static final String ENV_DATA_PATH = "data";
	
	/**
	 * Environment file name contain the data representing the user, home, 
	 * zones, rooms, accessories
	 */
	private static final String ENV_DATA_FILENAME = "environnement.data";
	
	/**
	 * Environment file name backup.
	 */
	private static final String ENV_DATA_FILENAME_BACKUP = "environnement.data.bk";
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(EnvironmentJsonFile.class);
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * Environment data system. This is the main data 
	 * saved and loaded from environment data file.
	 */
	private Data m_data;
	
	/**
	 * Flag to indicate if the envrionment is loaded.
	 */
	private boolean m_is_loaded;
	
	/**
	 * Flag to indicate if the environment is saved.
	 */
	private boolean m_is_saved;
	
	/**
	 * Path for the environment file.
	 */
	private String m_path;
	
	/**
	 * Discovery manager of the accessories in the environment
	 */
	private DiscoveryService m_discovery_manager;
	
	/**
	 * Default constructor of the environment.
	 */
	@Inject
	public EnvironmentJsonFile(DiscoveryService _service){
		
		m_discovery_manager = _service;
		m_is_loaded = false;
		m_is_saved = false;
	}
	
	/**
	 * Initialization of the EnvironmentJsonFile.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		m_path = Application.getApp().getBasePath() + "/" + ENV_DATA_PATH + "/";
		
		// check if environment directory exist and load if exist
		if( true == checkEnvironmentExist(m_path, ENV_DATA_FILENAME) ){
			
			// file environment exist, try to load it.
			if( true == loadEnvironment(m_path, ENV_DATA_FILENAME) ){
				// loading completed with success.
				ret_val = m_is_loaded = m_is_saved = m_isInit = true;
				m_logger.info("Environment loaded.");
			}
			else{
				// loading failed. 
				m_logger.error("Environment load failed! Try to load backup.");
				
				// try to load last backup
				if( true == loadEnvironment(m_path, ENV_DATA_FILENAME_BACKUP) ){
					
					m_logger.info("Environment backup loaded. Try to save the restored environment.");
					
					if(true == saveEnvironment(m_path, ENV_DATA_FILENAME)){
						m_logger.info("Restore completed.");
						ret_val = m_is_loaded = m_is_saved = m_isInit = true;
					}
					else{
						m_logger.error("Unable to restore backup.");
					}
				}
				else{
					m_logger.error("Environment backup load failed!");
				}
			}
		}
		else{
			// create the initial environment
			m_logger.info("No environment file exist. Create the base environment.");
			
			Data temp_env = this.generateBaseEnvironnment();
			if(null != temp_env){
				
				m_data = temp_env;
				
				if( true == saveEnvironment(m_path, ENV_DATA_FILENAME) ){
					ret_val = m_is_loaded = m_is_saved = m_isInit = true;
					
					// generate a backup file
					if(false == saveEnvironment(m_path, ENV_DATA_FILENAME_BACKUP)){
						m_logger.warn("Unable to save backup environment file.");
					}
				}
			}
			else{
				m_logger.error("Unable to generate the base environment.");
			}
		}
		
		if( true==ret_val ){
			
			if(null != m_discovery_manager){
				m_discovery_manager.addListner(this);
				m_discovery_manager.start();
			}
			else{
				m_logger.warn("No discovery manager set in the environment.");
			}
			
			m_logger.info("Initialization completed.");
		}
		else
			m_logger.error("Initialization failed.");
		
		return ret_val;
	}

	/**
	 * Return True is the EnvironmentJsonFile is correctly initialized.
	 */
	public boolean isInit() {
		return m_isInit;
	}
	
	/**
	 * Return True is the EnvironmentJsonFile is correctly Loaded.
	 * 
	 * @return True if this module is loaded.
	 */
	public boolean isLoaded() {
		return m_is_loaded;
	}
	
	/**
	 * Return True is the EnvironmentJsonFile is correctly saved.
	 * 
	 * @return True is the environmment is saved.
	 */
	public boolean isSaved() {
		return m_is_saved;
	}

	/**
	 * UnInitialize the EnvironmentJsonFile. Return in empty state ready to initialize again.
	 */
	public void unInit() {
		m_logger.info("UnInitialization in progress ...");
		
		// TODO UnInit Environment Manager
		
		if(null != m_discovery_manager){
			m_discovery_manager.stop();
			m_discovery_manager = null;
		}

		m_logger.info("UnInitialization completed.");
	}
	
	/**
	 * Return a JSON string representing the environment.
	 * @return JSON string of the environment.
	 */
	public String getJsonEnvironment(){
		String ret_val = "";
		if(true == m_isInit &&
		   null != m_data){
			
			Gson gson = new Gson();
			ret_val = gson.toJson(m_data);
		}
		return ret_val;
	}
	
	/**
	 * Check if environment file already exist. 
	 * @param _path Path of the environment file.
	 * @param _filename File name of the environment file.
	 * @return True if environment file exist.
	 */
	private boolean checkEnvironmentExist(String _path, String _filename){
		
		boolean ret_val = false;
		
		File f = new File(m_path, ENV_DATA_FILENAME);
		if(f.exists() && !f.isDirectory()){
			ret_val = true;
		}
		
		return ret_val;
			
	}
	
	/**
	 * Load environment data from file.
	 * 
	 * @param _path Path of the environment file.
	 * @param _filename File name of the environment file.
	 * @return True if the load successed.
	 */
	private boolean loadEnvironment(String _path, String _filename){
		boolean ret_value = false;

		m_logger.info("Loading environment file: " + _path + _filename + "...");
		
		BufferedReader br;
		try {
			
			br = new BufferedReader( new FileReader( _path + _filename) );
			
			// load json file and convert to object
			Gson gson = new Gson();
			Data env_obj = gson.fromJson(br, Data.class);
			
			if(null != env_obj){
				m_data = env_obj;
				ret_value = true;
			}
			else{
				m_logger.warn("Nothing to load in environment file : " + _path + _filename);
			}
			
		} 
		catch (FileNotFoundException e) {
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		catch (JsonParseException e){
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
		catch (Exception e){
			m_logger.error(e.getMessage());
			StdOutErrLog.tieSystemOutAndErrToLog();
			e.printStackTrace();
		}
			     
		return ret_value;
	}
	
	/**
	 * Save the environment in file.
	 * 
	 * @param _path Paht of the environment file.
	 * @param _filename File name of the environment file.
	 * @return True if save succeded.
	 */
	private boolean saveEnvironment(String _path, String _filename){
		
		boolean ret_value = false;
		
		if( null != m_data){
		
			Gson gson = new Gson();
			String json = gson.toJson(m_data);  
			
			try {  
				
				m_logger.info("Saving file environment: " + _path + _filename);
				
				// ensure the path exist before saving file
				File directory = new File(_path);
				directory.mkdirs();
				
				FileWriter writer = new FileWriter( _path + _filename);  
				writer.write(json);  
				writer.close();
			   
				ret_value = true;
			} 
			catch (IOException e) {  
				m_logger.error(e.getMessage());
				StdOutErrLog.tieSystemOutAndErrToLog();
				e.printStackTrace(); 
			}
		}
		else{
			m_logger.info("Notthing to save in environment");
		}
		  
		return ret_value;
	}
	
	/**
	 * Generate the base environment on new system. 
	 * @return Environment object build for the base system.
	 */
	private Data generateBaseEnvironnment(){
		
		Data ret_env = null;
		
		// Person
		List<Person> persons = new ArrayList<Person>();
		Person eric = new Person(UIDGenerator.getUID(), "Eric");
		eric.setLastname("Pinet");
		eric.addToEmails(new Email(EmailType.PERSONAL, "pineri01@gmail.com"));
		eric.addToEmails(new Email(EmailType.WORK, "eric.pinet@imagemsoft.com"));
		eric.addToEmails(new Email(EmailType.OTHER, "eric_pinet@hotmail.com"));
		eric.addToPhones(new Phone(PhoneType.CELL, "418 998-2481"));
		eric.addToPhones(new Phone(PhoneType.OTHER, "418 548-1684"));
		Address ericadd = new Address(AddressType.HOME, "2353 rue du cuir");
		ericadd.setCity("Québec");
		ericadd.setRegion("Québec");
		ericadd.setZipcode("G3E0G3");
		ericadd.setCountry("Canada");
		eric.addToAddress(ericadd);
		persons.add(eric);
		
		Person qiaomei = new Person(UIDGenerator.getUID(), "Qiaomei");
		qiaomei.setLastname("Wang");
		qiaomei.addToEmails(new Email(EmailType.PERSONAL, "qiaomei.wang.wqm@gmail.com"));
		qiaomei.addToEmails(new Email(EmailType.WORK, "qiaomei.wang@frima.com"));
		qiaomei.addToPhones(new Phone(PhoneType.CELL, "438 348-1699"));
		Address qiaomeiadd = new Address(AddressType.HOME, "2353 rue du cuir");
		qiaomeiadd.setCity("Québec");
		qiaomeiadd.setRegion("Québec");
		qiaomeiadd.setZipcode("G3E0G3");
		qiaomeiadd.setCountry("Canada");
		qiaomei.addToAddress(ericadd);
		persons.add(qiaomei);
		
		Characteristic boolean_light = new Characteristic(UIDGenerator.getUID(), CharacteristicAccessMode.READ_WRITE, CharacteristicType.BOOLEAN, CharacteristicEventType.EVENT, "false");
		Characteristic dimmable_light = new Characteristic(UIDGenerator.getUID(), CharacteristicAccessMode.READ_WRITE, CharacteristicType.FLOAT, CharacteristicEventType.EVENT, "1.0");
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		characteristics.add(boolean_light);
		characteristics.add(dimmable_light);
		
		
		Service dimmable_light_service = new Service(UIDGenerator.getUID(), characteristics);
		List<Service> services = new ArrayList<Service>();
		services.add(dimmable_light_service);
		
		Accessory light_leving = new Accessory(	UIDGenerator.getUID(),
												"Light",
												"Philips",
												"100w",
												"PL001-100-10009",
												services);
		
		List<Accessory> accessories_leving = new ArrayList<Accessory>();
		accessories_leving.add(light_leving);
		
		// Create room
		Room leving = new Room(UIDGenerator.getUID(), "Leving room");
		leving.setAccessories(accessories_leving);
		
		List<Room> rooms_first_floor = new ArrayList<Room>();
		rooms_first_floor.add(leving);
		
		// Create zone
		Zone first_floor = new Zone(UIDGenerator.getUID(), "First floor");
		first_floor.setRooms(rooms_first_floor);
		
		List<Zone> home1_zones = new ArrayList<Zone>();
		home1_zones.add(first_floor);
		
		// Create home
		Home home1 = new Home(UIDGenerator.getUID(), "Home");
		home1.setZones(home1_zones);
		
		List<Home> homes = new ArrayList<Home>();
		homes.add(home1);
		
		// Create base data
		ret_env = new Data();
		ret_env.addToHome(home1);
		ret_env.setPersons(persons);
		
		return ret_env;
	}

	/**
	 * Callback called when service is discover.
	 * 
	 * @param _service Service informations.
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceDiscover(javax.jmdns.ServiceEvent)
	 */
	public void serviceDiscover(ServiceEvent _service) {
		// TODO
		m_logger.info("Accessory discovered: "+ _service.getName() + " - " + _service.getType());
	}

	/**
	 * Callback called when service is removed.
	 * 
	 * @param _service Service information.
	 * @see com.connectlife.coreserver.environment.discover.DiscoveryListner#serviceRemove(javax.jmdns.ServiceEvent)
	 */
	public void serviceRemove(ServiceEvent _service) {
		// TODO
		m_logger.info("Accessory removed: "+ _service.getName() + " - " + _service.getType());
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getData()
	 */
	@Override
	public Data getData() {
		return m_data;
	}

	/**
	 * @param person
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#addPerson(com.connectlife.clapi.Person)
	 */
	@Override
	public boolean addPerson(Person person) {
		boolean ret_val = false;
		
		if(null != person){	
			boolean found = false;
			int pos = 0;

			// try to find the person to update.
			while(pos<m_data.persons.size() && false == found){
				Person per = m_data.persons.get(pos);
				
				if(per.getUid().equals(person.getUid())){
					found = true;
					
					m_data.persons.set(pos, person);
				
					environmentChange();
					
					ret_val = true;
					
					m_logger.debug("addPerson: "+person.firstname + " (Update)");
				}
				pos++;
			}
			
			// if not found, add person.
			if(false == found){
				m_data.persons.add(person);
				ret_val = true;
				m_logger.debug("addPerson: "+person.firstname + " (Add)");
			}
		}
		return ret_val;
	}

	/**
	 * @param person
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#deletePerson(com.connectlife.clapi.Person)
	 */
	@Override
	public boolean deletePerson(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param home
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#addHome(com.connectlife.clapi.Home)
	 */
	@Override
	public boolean addHome(Home home) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param home
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#deleteHome(com.connectlife.clapi.Home)
	 */
	@Override
	public boolean deleteHome(Home home) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param home
	 * @param zone
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#addZone(com.connectlife.clapi.Home, com.connectlife.clapi.Zone)
	 */
	@Override
	public boolean addZone(Home home, Zone zone) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param zone
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#deleteZone(com.connectlife.clapi.Zone)
	 */
	@Override
	public boolean deleteZone(Zone zone) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param zone
	 * @param room
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#addRoom(com.connectlife.clapi.Zone, com.connectlife.clapi.Room)
	 */
	@Override
	public boolean addRoom(Zone zone, Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param room
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#deleteRoom(com.connectlife.clapi.Room)
	 */
	@Override
	public boolean deleteRoom(Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#getNotMatchedAccessories()
	 */
	@Override
	public List<Accessory> getNotMatchedAccessories() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param room
	 * @param accessory
	 * @return
	 * @see com.connectlife.coreserver.environment.Environment#attachAccessory(com.connectlife.clapi.Room, com.connectlife.clapi.Accessory)
	 */
	@Override
	public boolean attachAccessory(Room room, Accessory accessory) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Indicate that the environment was changes. All observers will be notified.
	 */
	private void environmentChange(){
		setChanged();
		notifyObservers();
		m_is_saved = false;
	}
}
