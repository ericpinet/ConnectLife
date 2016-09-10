/**
 *  ImageResize.java
 *  simulator
 *
 *  Created by ericpinet on 2016-09-05.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator.image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-09-05
 */
public abstract class ImageResize {
	
	public static Image resize(Image image, int width, int height) {
		
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, 
		image.getBounds().width, image.getBounds().height, 
		0, 0, width, height);
		gc.dispose();
		image.dispose();
		return scaled;
	}

}
