/**
 *  ShellCmdRegisterAccessory.java
 *  coreserver
 *
 *  Created by ericpinet on 2016-02-07.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.console;

import java.io.InterruptedIOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Shell command for unregister accessory in the environment.
 * 
 * @author ericpinet
 * <br> 2016-02-07
 */
public class ShellCmdUnregisterAccessory implements ShellCmd {
	
	/**
	 * Logger for the shell
	 */
    private static final Logger m_logger = LogManager.getLogger(ShellCmdUnregisterAccessory.class);

	/**
	 * Shell command.
	 */
	private static final String SHELL_CMD = "unregister accessory";
	
	/**
	 * Shell help string.
	 */
	private static final String SHELL_CMD_HELP = SHELL_CMD + " - Unregister accessory in the environment.\n";
	 
	/**
	 * Get the shell command.
	 * 
	 * @return Shell command.
	 * @see com.connectlife.coreserver.console.ShellCmd#getCommand()
	 */
	@Override
	public String getCommand() {
		return SHELL_CMD;
	}

	/**
	 * Return the help string of the shell command.
	 * 
	 * @return Help string.
	 * @see com.connectlife.coreserver.console.ShellCmd#getHelp()
	 */
	@Override
	public String getHelp() {
		return SHELL_CMD_HELP;
	}
	
	/**
	 * Check if the line is compatible with this command.
	 * 
	 * @param _line Line enter by the user.
	 * @return True if the line in compatible with this command.
	 */
	public boolean checkLineForCommandCompatibility(String _line){
		return _line.startsWith(SHELL_CMD);
	}

	/**
	 * Execute the command with this shell line. 
	 * 
	 * @param _line The shell line enter by the user.
	 * @return The shell string return to display at the user.
	 * @throws InterruptedIOException The execution can cause a inturruption of the console screen. (Exit/Quit/Shutdown)
	 * @see com.connectlife.coreserver.console.ShellCmd#execute(java.lang.String)
	 */
	@Override
	public String execute(String _line) throws InterruptedIOException {
		String response = "";
		
		m_logger.info(SHELL_CMD);
		
		response = SHELL_CMD + " not supported yet!";
    	
		/*
    	// this section is to get the accessory and room from the arg.
    	int minLength = (SHELL_CMD+" [*][*]").length();
    	int accessory_start_at 	= _line.indexOf("[");
    	int accessory_end_at   	= _line.indexOf("]");
    	int room_start_at    	= _line.indexOf("[",accessory_end_at);
    	int room_end_at      	= _line.indexOf("]", room_start_at);
    	
    	if((_line.length() < minLength) || (_line.charAt(SHELL_CMD.length()) != ' ') || (accessory_start_at + 1 >= accessory_end_at) || (accessory_end_at + 1 != room_start_at) || (room_start_at + 1 >= room_end_at) || (_line.length() != room_end_at + 1))
    	{
    		response = "Format error! Please use format like : " + SHELL_CMD+" [accessory serial number][room uid]";
    	}
    	else
    	{
        	String accessory_sn = _line.substring(accessory_start_at+1, accessory_end_at);
        	String room_uid     = _line.substring(room_start_at+1, room_end_at);
        	
        	Accessory accessory = null;
        	boolean notfound = true;
        	
        	// Find the accessory
        	List<Device> devices = Application.getApp().getEnvironment().getDeviceManager().getDevices();
        	Iterator<Device> it = devices.iterator();
        	while(it.hasNext() && notfound){
        		Device device = it.next();
        		if(device.getDefinition().getAccessory().getSerialnumber().equals(accessory_sn)){
        			accessory = device.getDefinition().getAccessory();
        			notfound = false;
        		}
        	}
        	
        	if(notfound){
        		response = "Accessory not found.";
        	}
        	else{
        	
            	// Find the room
            	Room room = FindProcessor.findRoomByUid(room_uid);
            	if(null == room){
            		response = "Room not found.";
            	}
            	else{
            		// Register the device in the room
            		try {
						//AccessoryProcessor.registerAccessory(accessory, room);
						//response = "Accessory unregisted.";
            			m_logger.error("Unregister is not supported yet!");
						
					} catch (Exception e) {
						m_logger.error(e.getMessage());
						response = e.getMessage();
					}
            	}
        	}
    	}*/
    	return response;
	}
}
