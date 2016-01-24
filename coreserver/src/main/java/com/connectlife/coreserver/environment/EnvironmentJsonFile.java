/**
 *  EnvironmentJsonFile.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-09-09.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.environment;

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

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.inject.Inject;
import java.util.Observable;

import com.clapi.data.*;
import com.clapi.data.Accessory.AccessoryProtocolType;
import com.clapi.data.Accessory.AccessoryType;
import com.clapi.data.Address.AddressType;
import com.clapi.data.Characteristic.CharacteristicAccessMode;
import com.clapi.data.Characteristic.CharacteristicEventType;
import com.clapi.data.Characteristic.CharacteristicType;
import com.clapi.data.Email.EmailType;
import com.clapi.data.Phone.PhoneType;
import com.connectlife.coreserver.tools.errormanagement.StdOutErrLog;
import com.connectlife.coreserver.Application;
import com.connectlife.coreserver.environment.device.DeviceManager;

/**
 * Manager of the environment of the automation.
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class EnvironmentJsonFile extends Observable implements Environment {
	
	/**
	 * Environment data path contain the data representing the user, home, 
	 * zones, rooms, accessories
	 */
	private static final String ENV_DATA_PATH = "data";
	
	/**
	 * Environment file name contain the data representing the user, home, 
	 * zones, rooms, accessories
	 */
	private static final String ENV_DATA_FILENAME = "env.data";
	
	/**
	 * Environment file name backup.
	 */
	private static final String ENV_DATA_FILENAME_BACKUP = "env.data.bk";
	
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
	 * Flag to indicate if the environment is loaded.
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
	 * Device manager of the accessories in the environment
	 */
	private DeviceManager m_device_manager;
	
	/**
	 * Default constructor of the environment.
	 * 
	 * @param _service DiscoveryService at use in this Environment. 
	 */
	@Inject
	public EnvironmentJsonFile(DeviceManager _service){
		m_device_manager = _service;
		m_is_loaded = false;
		m_is_saved = false;
		m_isInit = false;
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
		
		// Init the device manager if all is start correctly.
		if(true == ret_val){
			ret_val = m_device_manager.init();
			
			if(true == ret_val){
				m_logger.info("Initialization completed.");
			}
			else{
				m_logger.error("Unable to init the service manager of this environment. Environment initialization failed.");
			}
		}
		else{
			m_logger.error("Unable to init the environment.");
		}

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
		
		if( false == isSaved() ){
			m_logger.warn("Environment is clossing when not saved. Force save environment before closing.");
			saveEnvironment(m_path, ENV_DATA_FILENAME);
		}
		
		if(null != m_device_manager){
			m_device_manager.unInit();
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
			
			// prepare the data to save.
			// remove not valuable field to save.
			Data data_to_save = SaveProcessor.prepareSave(this);
		
			// convert to json.
			Gson gson = new Gson();
			String json = gson.toJson(data_to_save);  
			
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
		eric.addToEmails(new Email(UIDGenerator.getUID(), "pineri01@gmail.com", EmailType.PERSONAL));
		eric.addToEmails(new Email(UIDGenerator.getUID(), "eric.pinet@imagemsoft.com", EmailType.WORK));
		eric.addToEmails(new Email(UIDGenerator.getUID(), "eric_pinet@hotmail.com", EmailType.OTHER));
		eric.addToPhones(new Phone(UIDGenerator.getUID(), "418 998-2481", PhoneType.CELL));
		eric.addToPhones(new Phone(UIDGenerator.getUID(), "418 548-1684", PhoneType.OTHER));
		Address ericadd = new Address(UIDGenerator.getUID(), AddressType.HOME, "2353 rue du cuir");
		ericadd.setCity("Québec");
		ericadd.setRegion("Québec");
		ericadd.setZipcode("G3E0G3");
		ericadd.setCountry("Canada");
		eric.addToAddress(ericadd);
		persons.add(eric);
		
		Person qiaomei = new Person(UIDGenerator.getUID(), "Qiaomei");
		qiaomei.setLastname("Wang");
		qiaomei.addToEmails(new Email(UIDGenerator.getUID(), "qiaomei.wang.wqm@gmail.com", EmailType.PERSONAL));
		qiaomei.addToEmails(new Email(UIDGenerator.getUID(), "qiaomei.wang@frima.com", EmailType.WORK));
		qiaomei.addToPhones(new Phone(UIDGenerator.getUID(), "438 348-1699", PhoneType.CELL));
		
		Address qiaomeiadd = new Address(UIDGenerator.getUID(), AddressType.HOME, "2353 rue du cuir");
		qiaomeiadd.setCity("Québec");
		qiaomeiadd.setRegion("Québec");
		qiaomeiadd.setZipcode("G3E0G3");
		qiaomeiadd.setCountry("Canada");
		qiaomei.addToAddress(qiaomeiadd);
		persons.add(qiaomei);
		
		Characteristic boolean_light = new Characteristic(UIDGenerator.getUID(), "Light", CharacteristicAccessMode.READ_WRITE, CharacteristicType.BOOLEAN, CharacteristicEventType.EVENT, "false");
		Characteristic dimmable_light = new Characteristic(UIDGenerator.getUID(), "Dimmable", CharacteristicAccessMode.READ_WRITE, CharacteristicType.FLOAT, CharacteristicEventType.EVENT, "1.0");
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		characteristics.add(boolean_light);
		characteristics.add(dimmable_light);
		
		
		Service dimmable_light_service = new Service(UIDGenerator.getUID(), "light", characteristics);
		List<Service> services = new ArrayList<Service>();
		services.add(dimmable_light_service);
		
		Accessory light_leving = new Accessory(	UIDGenerator.getUID(),
												"Light",
												"Philips",
												"100w",
												"PL001-100-10009",
												services,
												"",
												AccessoryType.LIGHT_COLORED_DIMMABLE,
												AccessoryProtocolType.JSON_SIMULATION);
		
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
		ret_env.setHomes(homes);
		ret_env.setPersons(persons);
		
		return ret_env;
	}

	/**
	 * @return The all data in the environment.
	 * @see com.connectlife.coreserver.environment.Environment#getData()
	 */
	@Override
	public Data getData() {
		return m_data;
	}
	
	/**
	 * Indicate that the environment was changes. All observers will be notified.
	 * TODO: Add automatic save of environment after modification.
	 */
	private void environmentChange(){
		setChanged();
		notifyObservers();
		m_is_saved = false;
	}

	/**
	 * @param firstname First name of the person.
	 * @param lastname Last name of the person.
	 * @param imageurl Url of the image of this person.
	 * @return The uid of this new person.
	 * @see com.connectlife.coreserver.environment.Environment#addPerson(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addPerson(String firstname, String lastname, String imageurl) {
		Person person = new Person(UIDGenerator.getUID(), firstname, lastname, imageurl);
		m_data.addToPersons(person);
		environmentChange();
		return person.getUid();
	}
	
	/**
	 * Update the person in environment.
	 * @param uid UID of the person.
	 * @param firstname First name of the person.
	 * @param lastname  Last name of the person.
	 * @param imageurl  Image url of the person.
	 * @return UID of the person. 
	 * @see com.connectlife.coreserver.environment.Environment#addPerson(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String updatePerson(String uid, String firstname, String lastname, String imageurl) {
		m_data.updatePerson(uid, firstname, lastname, imageurl);
		environmentChange();
		return uid;
	}
}
