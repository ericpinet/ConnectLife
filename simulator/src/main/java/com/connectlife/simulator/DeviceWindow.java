/**
 *  DeviceWindow.java
 *  simulator
 *
 *  Created by ericpinet on 2016-01-10.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.connectlife.simulator.device.Device;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-10
 */
public class DeviceWindow extends Dialog {

	protected Object result;
	protected Shell shlDevices;
	private org.eclipse.swt.widgets.List list;
	private Text textUid;
	private Text textLabel;
	private Text textManufacturer;
	private Text textModel;
	private Text textSerialNumber;
	private Text textSetupInRoom;
	private Text textString;
	private List<Device> devices;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DeviceWindow(Shell parent, int style, List<Device> devices) {
		super(parent, SWT.DIALOG_TRIM);
		setText("SWT Dialog");
		this.devices = devices;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlDevices.open();
		shlDevices.layout();
		Display display = getParent().getDisplay();
		while (!shlDevices.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlDevices = new Shell(getParent(), getStyle());
		//shlAccessories.setSize(531, 331);
		shlDevices.setText("Devices");
		shlDevices.setLayout(new GridLayout(3, false));
		
		list = new org.eclipse.swt.widgets.List(shlDevices, SWT.BORDER | SWT.SINGLE);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));
		list.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		    	  updateDeviceInformation();
		      }
		});
		
		Label lblUid = new Label(shlDevices, SWT.NONE);
		lblUid.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUid.setText("UID :");
		
		textUid = new Text(shlDevices, SWT.BORDER);
		textUid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblLabel = new Label(shlDevices, SWT.NONE);
		lblLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLabel.setAlignment(SWT.RIGHT);
		lblLabel.setText("Label :");
		
		textLabel = new Text(shlDevices, SWT.BORDER);
		textLabel.setText("");
		textLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblManufacturer = new Label(shlDevices, SWT.NONE);
		lblManufacturer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblManufacturer.setAlignment(SWT.RIGHT);
		lblManufacturer.setText("Manufacturer :");
		
		textManufacturer = new Text(shlDevices, SWT.BORDER);
		textManufacturer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblModel = new Label(shlDevices, SWT.NONE);
		lblModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModel.setAlignment(SWT.RIGHT);
		lblModel.setText("Model :");
		
		textModel = new Text(shlDevices, SWT.BORDER);
		textModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblSerialNumber = new Label(shlDevices, SWT.NONE);
		lblSerialNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSerialNumber.setAlignment(SWT.RIGHT);
		lblSerialNumber.setText("Serial Number :");
		
		textSerialNumber = new Text(shlDevices, SWT.BORDER);
		textSerialNumber.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnAdd = new Button(shlDevices, SWT.NONE);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
		});
		btnAdd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnAdd.setText("Add");
		
		Label lblSetupInRoom = new Label(shlDevices, SWT.NONE);
		lblSetupInRoom.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSetupInRoom.setAlignment(SWT.RIGHT);
		lblSetupInRoom.setText("Setup in room :");
		
		textSetupInRoom = new Text(shlDevices, SWT.BORDER);
		textSetupInRoom.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnDel = new Button(shlDevices, SWT.NONE);
		btnDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
		});
		btnDel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnDel.setText("Del");
		
		Label label = new Label(shlDevices, SWT.SEPARATOR | SWT.HORIZONTAL);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		
		Label lblBoolean = new Label(shlDevices, SWT.NONE);
		lblBoolean.setAlignment(SWT.RIGHT);
		lblBoolean.setText("Boolean :");
		
		Button btnYes = new Button(shlDevices, SWT.RADIO);
		btnYes.setText("Yes");
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		
		Button btnNo = new Button(shlDevices, SWT.RADIO);
		btnNo.setText("No");
		new Label(shlDevices, SWT.NONE);
		
		Label lblFloat = new Label(shlDevices, SWT.NONE);
		lblFloat.setAlignment(SWT.RIGHT);
		lblFloat.setText("Float :");
		
		Scale scaleFloat = new Scale(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		
		Label lblInteger = new Label(shlDevices, SWT.NONE);
		lblInteger.setAlignment(SWT.RIGHT);
		lblInteger.setText("Integer :");
		
		Scale scaleInteger = new Scale(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		
		Label lblString = new Label(shlDevices, SWT.NONE);
		lblString.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblString.setText("String :");
		
		textString = new Text(shlDevices, SWT.BORDER);
		textString.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		refreshDeviceList();
	}
	
	/**
	 * Refresh the data in the dialog. Reload the device.
	 */
	public void refreshDeviceList(){
		// load device in the list view
		list.removeAll();
		Iterator<Device> it = devices.iterator();
		while(it.hasNext()){
			Device device = it.next();
			list.add(device.getLabel() + " " + device.getSerialnumber());
		}
		
		// remove all information
		this.textUid.setText("");
	}
	
	/**
	 * Selection is changed in the list of devices.
	 */
	private void updateDeviceInformation(){
		// remove all information
		this.textUid.setText("");
		this.textLabel.setText("");
		this.textManufacturer.setText("");
		this.textModel.setText("");
		this.textSerialNumber.setText("");
		
		// check if an item is selected
		int selected_idx = list.getSelectionIndex();
		if(selected_idx>=0){
			// show information for the current selected device
			Device device = devices.get(selected_idx);
			this.textUid.setText(device.getUid());
			this.textLabel.setText(device.getLabel());
			this.textManufacturer.setText(device.getManufacturer());
			this.textModel.setText(device.getModel());
			this.textSerialNumber.setText(device.getSerialnumber());
			
		}// ELSE: No selected item. Do nothing.
	}
}
