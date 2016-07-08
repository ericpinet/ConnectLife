/**
 *  AddAccessoryDialog.java
 *  simulator
 *
 *  Created by ericpinet on 2016-06-26.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator;

import java.util.Iterator;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.connectlife.simulator.device.Device;

import org.eclipse.swt.widgets.Combo;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-06-26
 */
public class AddAccessoryDialog extends Dialog {

	String accessory;

	/**
	 * @param parent
	 */
	public AddAccessoryDialog(Shell parent) {
		super(parent);
	}

	/**
	 * Open dialog
	 * 
	 * @return
	 */
	public int open() {
		Shell parent = getParent();
		final Shell shell =
				new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		shell.setText("Add Accessory");

		shell.setLayout(new GridLayout(2, true));

		Label label = new Label(shell, SWT.NULL);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("Please select an result:");

		Combo comboDevices = new Combo(shell, SWT.NONE);
		comboDevices.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		if (null != DeviceWindow.getRef() && null != DeviceWindow.getRef().getDevices()) {

			Iterator<Device> it = DeviceWindow.getRef().getDevices().iterator();
			while (it.hasNext()) {
				Device device = it.next();
				if (null != device.getServer()) {
					comboDevices.add(device.getSerialnumber());
				}
			}

		}

		final Button buttonOK = new Button(shell, SWT.PUSH);
		buttonOK.setText("Ok");
		buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		Button buttonCancel = new Button(shell, SWT.PUSH);
		buttonCancel.setText("Cancel");

		buttonOK.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				accessory = comboDevices.getText();
				shell.dispose();
			}
		});

		buttonCancel.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				accessory = "";  
				shell.dispose();
			}
		});

		shell.addListener(SWT.Traverse, new Listener() {
			public void handleEvent(Event event) {
				if(event.detail == SWT.TRAVERSE_ESCAPE)
					event.doit = false;
			}
		});
		shell.pack();
		shell.open();

		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		return (accessory.isEmpty() ? Window.CANCEL : Window.OK );
	}

	/**
	 * Return the result. 
	 * 
	 * @return
	 */
	public String getResult() {
		return accessory;
	}

}
