/**
 *  EnvironmentManager.java
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
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.rits.cloning.Cloner;

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
import com.connectlife.coreserver.environment.cmd.Cmd;
import com.connectlife.coreserver.environment.data.DataManager;
import com.connectlife.coreserver.environment.device.DeviceManager;
import com.connectlife.coreserver.environment.find.FindProcessor;
import com.connectlife.coreserver.environment.find.FindProcessorReadOnly;
import com.connectlife.coreserver.environment.find.FindProcessorReadWrite;

/**
 * Manager of the environment of the automation.
 * 
 * @author ericpinet
 * <br> 2015-09-09
 */
public class EnvironmentManager extends Observable implements Environment, EnvironmentContext {
	
	/**
	 * Logger use for this class.
	 */
	private static Logger m_logger = LogManager.getLogger(EnvironmentManager.class);
	
	/**
	 * Flag to indicate if the module is correctly initialized.
	 */
	private boolean m_isInit;
	
	/**
	 * Device manager of the accessories in the environment
	 */
	private DeviceManager m_device_manager;
	
	/**
	 * Data manager for the environment data.
	 */
	private DataManager m_data_manager;
	
	/**
	 * Find processor linked with this environment.
	 */
	private FindProcessor m_find;
	
	/**
	 * Default constructor of the environment.
	 * 
	 * @param _datamngr DataManager at use in this Environment.
	 * @param _devicemngr DeviceManager at use in this Environment. 
	 */
	@Inject
	public EnvironmentManager(DataManager _datamngr, DeviceManager _devicemngr){
		m_data_manager = _datamngr;
		m_device_manager = _devicemngr;
		m_isInit = false;
	}
	
	/**
	 * Initialization of the EnvironmentManager.
	 * 
	 * @return True if initialization completed with success.
	 */
	public boolean init(){
		boolean ret_val = false;
		
		m_logger.info("Initialization in progress ...");
		
		if( true == m_data_manager.init() ){
			
			// check if environment directory exist and load if exist
			if( true == m_data_manager.checkEnvironmentExist() ){
				
				// file environment exist, try to load it.
				if( true == m_data_manager.loadEnvironment() ){
					// loading completed with success.
					ret_val = m_isInit = true;
					m_logger.info("Environment loaded.");
				}
				else{
					// loading failed. 
					m_logger.error("Environment load failed! Try to load backup.");
					
					// try to load last backup
					if( true == m_data_manager.loadEnvironmentBackup() ){
						
						m_logger.info("Environment backup loaded. Try to save the restored environment.");
						
						if(true == m_data_manager.saveEnvironment()){
							m_logger.info("Restore completed.");
							ret_val = m_isInit = true;
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
					
					m_data_manager.setData(temp_env);
					
					if( true == m_data_manager.saveEnvironment() ){
						ret_val = m_isInit = true;
						
						// generate a backup file
						if(false == m_data_manager.saveEnvironmentBackup()){
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
			
			// Init the find processor if all is start correctly
			if(true == ret_val){
				m_find = new FindProcessorReadWrite(m_data_manager.getData());
			}
		}

		return ret_val;
	}

	/**
	 * Return True is the EnvironmentManager is correctly initialized.
	 */
	public boolean isInit() {
		return m_isInit;
	}

	/**
	 * UnInitialize the EnvironmentManager. Return in empty state ready to initialize again.
	 */
	public void unInit() {
		
		m_logger.info("UnInitialization in progress ...");
		
		if( false == m_data_manager.isSaved() ){
			m_logger.warn("Environment is clossing when not saved. Force save environment before closing.");
			m_data_manager.saveEnvironment();
		}
		
		if(null != m_device_manager){
			m_device_manager.unInit();
		}

		m_logger.info("UnInitialization completed.");
	}
	
	/**
	 * Save the environment.
	 * 
	 * @return True if the environment is saved.
	 * @see com.connectlife.coreserver.environment.Environment#save()
	 */
	@Override
	public boolean save() {
		boolean ret_val = false;
		
		if( false == m_data_manager.isSaved() ){
			ret_val = m_data_manager.saveEnvironment();
		}
		else{
			m_logger.info("Environment is already saved. Don't execute save.");
			ret_val = true;
		}	
		
		return ret_val;
	}
	
	/**
	 * Return the device manager for the environment.
	 * 
	 * @return The device manager of the environment.
	 */
	public DeviceManager getDeviceManager(){
		return m_device_manager;
	}
	
	/**
	 * Return the data manager for the environment.
	 * 
	 * @return The data manager of the environment.
	 */
	public DataManager getDataManager(){
		return m_data_manager;
	}
	
	/**
	 * Return the find processor of this environment.
	 * 
	 * @return FindProcessor Return the find processor of this environment.
	 */
	public FindProcessor getFindProcessorReadOnly(){
		return new FindProcessorReadOnly(m_data_manager.getData());
	}
	
	/**
	 * Return the find processor of the context.
	 * 
	 * @return Find processor for the context.
	 */
	public FindProcessor getFindProcessorReadWrite(){
		return m_find;
	}
	
	/**
	 * Return a JSON string representing the environment.
	 * 
	 * @return JSON string of the environment.
	 */
	public String getJsonEnvironment(){
		String ret_val = "";
		if(true == m_isInit &&
		   null != m_data_manager.getData()){
			
			Gson gson = new Gson();
			ret_val = gson.toJson(m_data_manager.getData());
		}
		return ret_val;
	}
	
	/**
	 * Return a clone of the data environment. 
	 * Change this data will not affect the environment.
	 * 
	 * @return The all data in the environment.
	 * @see com.connectlife.coreserver.environment.Environment#getData()
	 */
	@Override
	public Data getData() {
		Cloner cloner = new Cloner();
		return cloner.deepClone(m_data_manager.getData());
	}
	
	/**
	 * Indicate that the environment was changes. All observers will be notified.
	 * TODO: Add automatic save of environment after modification.
	 */
	private void environmentChange(){
		setChanged();
		notifyObservers();
		m_data_manager.setUnsaved();
	}
	
	/**
	 * Execute the command on the environment data.
	 * 
	 * @param _cmd Command to execute. See the CmdFactory to build command.
	 * @throws Exception Exception if something goes wrong.
	 */
	public synchronized void executeCommand(Cmd _cmd) throws Exception{

		_cmd.setContext(this);	// set the context of the execution
		
		try {
			_cmd.validContext(); 	// validate the context
			_cmd.execute();			// execute command on the context
			
			// check if the data was changed by the execution of the context
			if( true == _cmd.isDataChanged() )
				environmentChange();
			
		}catch (Exception exception){
			throw exception;
		}
	}

	/**
	 * Add a person in the data. The uid of the person will be generated
	 * during the adding process.
	 * 
	 * @param _person Person to add in the environment.
	 * @return Person added to the environment whit his generated uid.
	 * @throws Exception If something goes wrong.
	 * @deprecated Use executeCommand(Cmd _cmd) throws Exception;
	 */
	public Person addPerson(Person _person) throws Exception {
		_person.setUid(UIDGenerator.getUID());
		m_data_manager.getData().getPersons().add(_person);
		environmentChange();
		return _person;
	}
	
	/**
	 * Update a person in the data.
	 * 
	 * @param _person Person to update in the environment.
	 * @return Person updated in the environment.
	 * @throws Exception If something goes wrong.
	 * @see com.connectlife.coreserver.environment.Environment#updatePerson(com.clapi.data.Person)
	 * @deprecated Use executeCommand(Cmd _cmd) throws Exception;
	 */
	@Override
	public Person updatePerson(Person _person) throws Exception {
		Person ret_person = null;
		ret_person = m_find.findPerson(_person);
		if(null != ret_person){
			// update the person information
			ret_person.updateInformation(_person);
			
			// indicate that the environment was changed.
			environmentChange();
		}
		else{
			throw new Exception("Person not found.");
		}
		return ret_person;
	}
	
	/**
	 * Delete a person in the data.
	 * 
	 * @param _person Person to delete in the environment.
	 * @return Person deleted in the environment.
	 * @throws Exception If something goes wrong.
	 * @see com.connectlife.coreserver.environment.Environment#deletePerson(com.clapi.data.Person)
	 * @deprecated Use executeCommand(Cmd _cmd) throws Exception;
	 */
	@Override
	public Person deletePerson(Person _person) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Generate the base environment on new system. 
	 * 
	 * @return Environment object build for the base system.
	 */
	private Data generateBaseEnvironnment(){
		
		Data ret_env = null;
		
		// Person
		List<Person> persons = new ArrayList<Person>();
		
		Person eric = new Person(UIDGenerator.getUID(), "Eric");
		eric.setLastname("Pinet");
		eric.addEmails(new Email(UIDGenerator.getUID(), "pineri01@gmail.com", EmailType.PERSONAL));
		eric.addEmails(new Email(UIDGenerator.getUID(), "eric.pinet@imagemsoft.com", EmailType.WORK));
		eric.addEmails(new Email(UIDGenerator.getUID(), "eric_pinet@hotmail.com", EmailType.OTHER));
		eric.addPhones(new Phone(UIDGenerator.getUID(), "418 998-2481", PhoneType.CELL));
		eric.addPhones(new Phone(UIDGenerator.getUID(), "418 548-1684", PhoneType.OTHER));
		Address ericadd = new Address(UIDGenerator.getUID(), AddressType.HOME, "2353 rue du cuir");
		ericadd.setCity("Québec");
		ericadd.setRegion("Québec");
		ericadd.setZipcode("G3E0G3");
		ericadd.setCountry("Canada");
		eric.addAddress(ericadd);
		persons.add(eric);
		
		Person qiaomei = new Person(UIDGenerator.getUID(), "Qiaomei");
		qiaomei.setLastname("Wang");
		qiaomei.addEmails(new Email(UIDGenerator.getUID(), "qiaomei.wang.wqm@gmail.com", EmailType.PERSONAL));
		qiaomei.addEmails(new Email(UIDGenerator.getUID(), "qiaomei.wang@frima.com", EmailType.WORK));
		qiaomei.addPhones(new Phone(UIDGenerator.getUID(), "438 348-1699", PhoneType.CELL));
		
		Address qiaomeiadd = new Address(UIDGenerator.getUID(), AddressType.HOME, "2353 rue du cuir");
		qiaomeiadd.setCity("Québec");
		qiaomeiadd.setRegion("Québec");
		qiaomeiadd.setZipcode("G3E0G3");
		qiaomeiadd.setCountry("Canada");
		qiaomei.addAddress(qiaomeiadd);
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
}
