/**
 *  ConnectLifeApi.thrift
 *  coreserver
 *
 *  Created by Eric Pinet <pineri01@gmail.com> on 2015-09-07.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
 


namespace c_glib ConnectLifeApi
namespace java com.connectlife.clapi
namespace cpp com.connectlife.clapi
namespace rb ConnectLife.ClApi
namespace perl ConnectLifeApi
namespace csharp ConnectLife.Api
namespace js ConnectLifeApi
namespace st ConnectLifeApi
namespace py ConnectLifeApi
namespace py.twisted ConnectLifeApi
namespace go connectlifeapi
namespace php ConnectLifeApi
namespace delphi ConnectLife.clapi
namespace cocoa ConnectLifeApi
namespace lua ConnectLifeApi

namespace * com.connectlife.clapi

/**
 * Notification type
 */
enum Type{
  ENV_UPDATED = 1,
  SYS_SHUTDOWN
}

/**
 * Notification
 */
struct Notification {
  1: required Type id,
  2: list<string> data
}

/**
 * Email Type
 */
enum EmailType{
	PERSONAL = 1,
	WORK,
	OTHER
}

/**
 * Email
 */
struct Email {
	1: required EmailType type,
	2: required string email
}

/**
 * Phone Type
 */
enum PhoneType{
	HOME = 1,
	CELL,
	WORK,
	OTHER
}

/**
 * Phone
 */
struct Phone {
	1: required PhoneType type,
	2: required string phone
}

/**
 * Address Type
 */
enum AddressType{
	HOME = 1,
	WORK,
	OTHER
}

/**
 * Address
 */
struct Address {
	1: required AddressType type,
	2: required string street,
	3: optional string city,
	4: optional string region,
	5: optional string zipcode,
	6: optional string country
}

/**
 * Person
 */
struct Person {
	1: required string uid,
	2: required string firstname,
	3: optional string lastname,
	4: optional list<Email> emails,
	5: optional list<Phone> phones,
	6: optional list<Address> address,
	7: optional string imageurl
}
 

/**
 * CharacteristicAccessMode
 */
enum CharacteristicAccessMode {
	READ_ONLY = 1,
	WRITE_ONLY,
	READ_WRITE
}

/**
 * CharacteristicType
 */ 
enum CharacteristicType {
	BOOLEAN = 1,
	ENUM,
	FLOAT,
	INTEGER,
	STATIC_STRING,
	WRITE_ONLY_BOOLEAN
}

/**
 * CharacteristicEventType
 */
enum CharacteristicEventType {
	NO_EVENT = 1,
	EVENT
}

/**
 * Charateristic
 */
struct Characteristic {
	1: required string uid,
	2: required CharacteristicAccessMode mode,
	3: required CharacteristicType type,
	4: required CharacteristicEventType event,
	5: required string data 
}

/**
 * Service
 */
struct Service {
	1: required string uid,
	2: required list<Characteristic> characteristics
}

/**
 * AccessoryType
 */
enum AccessoryType {
	LIGHT = 1,
	LIGHT_DIMMABLE,
	LIGHT_COLORED,
	LIGHT_COLORED_DIMMABLE,
	FAN,
	AUTOMATIC_DOOR,
	LOCK_MECHANISM,
	THERMOSTAT,
	SWITCH
}

/**
 * Accessory
 */
struct Accessory {
	1: required string uid,
	2: required string label,
	3: required string manufacturer,
	4: required string model,
	5: required string serialnumber,
	6: required list<Service> services,
	7: optional string imageurl
}

/**
 * Room
 */
struct Room {
	1: required string uid,
	2: required string label,
	3: optional list<Accessory> accessories,
	4: optional string imageurl
}

/**
 * Zone
 */
struct Zone {
	1: required string uid,
	2: required string label,
	3: optional list<Room> rooms,
	4: optional string imageurl
}

/**
 * Home
 */
struct Home {
	1: required string uid,
	2: required string label,
	3: optional list<Zone> zones,
	4: optional string imageurl
}

/**
 * Data
 */
struct Data {
	1: optional list<Person> persons,
	2: optional list<Home> home
}

/**
 * CLApi - Api of server
 */
service CLApi
{
  /**
   * Get the service version.
   *
   * @return string - Service version. 
   */
  string         getVersion(),
  
  /**
   * Check compatibility of the client with the server version. 
   *
   * @param string version - Version of the client api. 
   * @return bool - Return true if the client version is compatible with the server.
   */
  bool			 checkCompatibility(1: string version), 	
  
  /**
   * Get the environment data in Json format.
   *
   * @return string - Json string representing the environment.
   */
  string 		getEnvironmentDataJson(),
  
}


/**
 * Service use to send notification to clients application.
 */
service CLApiPush
{
  /**
   * Push notification to client.
   * @param Notification - Notification to send at client.
   */
  oneway void 		push(1: Notification notification),
  
}


