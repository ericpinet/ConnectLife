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

// Presence of namespaces and sub-namespaces for which there is
// no generator should compile with warnings only
namespace noexist ConnectLifeApi
namespace cpp.noexist ConnectLifeApi

namespace * com.connectlife.clapi

/**
 * Notification type
 */
enum Type
{
  ENV_UPDATED = 1,
  SYS_SHUTDOWN
}

/**
 * Notification in api
 */
struct Notification {
  1: Type id,
  2: string data
}

/**
 * Exception in api.
 */
exception Xception {
  1: i32 errorCode,
  2: string message
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
  string         getVersion() throws (1:Xception e),
  
  /**
   * Check compatibility of the client with the server version. 
   *
   * @param string version - Version of the client api. 
   * @return bool - Return true if the client version is compatible with the server.
   */
  bool			 checkCompatibility(1: string version) throws (1:Xception e), 	
  
  /**
   * Get the environment data in Json format.
   *
   * @return string - Json string representing the environment.
   */
  string 		getEnvironmentDataJson() throws (1:Xception e),
  
}


/**
 * CLApiPush - Api for push notification to clients
 */
service CLApiPush
{
	void 		Push(1: Notification notification) throws (1:Xception e),
}


