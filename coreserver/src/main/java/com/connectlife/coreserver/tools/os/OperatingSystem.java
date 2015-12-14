/**
 *  OperatingSystem.java
 *  coreserver
 *
 *  Created by ericpinet on 2015-12-13.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.coreserver.tools.os;

/**
 * The OperatingSystem can return the Operating System that currently running.
 * 
 * The supported operating system are : 
 *  - Windows
 *  - Mac m_os_name X
 *  - Unix
 *  - Linux
 *  - Raspbian
 * 
 * @author ericpinet
 * <br> 2015-12-13
 */
public class OperatingSystem {

	/**
	 * Enum of supported operating system.
	 * 
	 * @author ericpinet
	 * <br> 2015-12-13
	 */
	public enum OperatingSystemType {
		WINDOWS,
		MACOSX,
		LINUX,
		UNKWOWN
	}

	/**
	 * Return the m_os_name type.
	 * 
	 * @return Type of the currently running m_os_name.
	 */
	public static OperatingSystemType getOS() {
		if (isWindows()) {
			return OperatingSystemType.WINDOWS;
        } else if (isMacOSX()) {
        	return OperatingSystemType.MACOSX;
        } else if (isLinux()) {
        	return OperatingSystemType.LINUX;
        } else {
        	return OperatingSystemType.UNKWOWN;
        }
	}
	
	/**
	 * Check if the current Operating System is Windows.
	 * @return True if is Windows.
	 */
	public static boolean isWindows(){
		return (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0);
	}
	
	/**
	 * Check if the current Operating System is MacOSX.
	 * @return True if is MacOSX.
	 */
	public static boolean isMacOSX(){
		return (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0);
	}
	
	/**
	 * Check if the current Operating System is Linux.
	 * @return True if is Linux.
	 */
	public static boolean isLinux(){
		return (System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0);
	}
}
