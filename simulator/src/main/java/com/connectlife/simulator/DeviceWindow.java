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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Scale;

import com.clapi.data.Characteristic;
import com.clapi.data.Characteristic.CharacteristicType;
import com.clapi.data.Service;
import com.connectlife.simulator.device.Device;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-10
 */
public class DeviceWindow extends Dialog {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(MainWindow.class);

	protected Object result;
	protected Shell shlDevices;
	private org.eclipse.swt.widgets.List list;
	private Text textLabel;
	private Text textManufacturer;
	private Text textModel;
	private Text textSerialNumber;
	private List<Device> devices;
	private Composite composite;

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
		shlDevices.setSize(650, 500);
		shlDevices.setText("Devices");
		shlDevices.setLayout(new GridLayout(3, false));
		
		list = new org.eclipse.swt.widgets.List(shlDevices, SWT.BORDER | SWT.SINGLE);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));
		list.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		    	  updateDeviceInformation();
		      }
		});
		list.setSize(150, 200);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		
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
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		
		Button btnDel = new Button(shlDevices, SWT.NONE);
		btnDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
			}
		});
		btnDel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnDel.setText("Del");
		
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		new Label(shlDevices, SWT.NONE);
		
		composite = new Composite(shlDevices, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 10);
		gd_composite.heightHint = 300;
		gd_composite.widthHint = 600;
		composite.setLayoutData(gd_composite);
		
		refreshDeviceList();
	}
	
	public void UpdateData(List<Device> _devices){
		devices = _devices;
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
	}
	
	/**
	 * Selection is changed in the list of devices.
	 */
	private void updateDeviceInformation(){
		// remove all information
		this.textLabel.setText("");
		this.textManufacturer.setText("");
		this.textModel.setText("");
		this.textSerialNumber.setText("");
		
		// check if an item is selected
		int selected_idx = list.getSelectionIndex();
		if(selected_idx>=0){
			// show information for the current selected device
			Device device = devices.get(selected_idx);
			this.textLabel.setText(device.getLabel());
			this.textManufacturer.setText(device.getManufacturer());
			this.textModel.setText(device.getModel());
			this.textSerialNumber.setText(device.getSerialnumber());
			
			// TODO: Manage more than 1 services
			Service service = device.getServices().get(0);
			updateDeviceCharacteristics(service.getCharacteristics());
			
		}
		else{
			// No device selected
			// remove all characteristics showed.
			removeChracteristics();
		}
	}
	
	/**
	 * Update the device characteristics in the user interface.
	 * Create the control for each characteristics.
	 */
	private void updateDeviceCharacteristics(List<Characteristic> _characteristics){
		
		// remove the old characteristics from previous device.
		removeChracteristics();
		
		// add new characteristics
		Iterator<Characteristic> it = _characteristics.iterator();
		while(it.hasNext()){
			Characteristic charc = it.next();
			
			if(charc.getType() == CharacteristicType.BOOLEAN){
				createBoolean(charc);
			}
			else if( charc.getType() == CharacteristicType.INTEGER ||
				charc.getType() == CharacteristicType.FLOAT ){
				
				createIntegerFloat(charc);
			}
			else{
				m_logger.error("This Type of characteristic is not supported yet!");
			}
			
		}
		
		composite.layout(true, true);
	}
	
	/**
	 * Remove the current characteristics from the user interface.
	 */
	private void removeChracteristics(){
		Control[] controls = composite.getChildren();
		for(int i=0 ; i<controls.length ; i++){
			controls[i].dispose();
		}
		composite.layout(true, true);
	}
	
	/**
	 * Create a boolean characteristic in the user interface.
	 * @param _characteristic
	 */
	private void createBoolean(Characteristic _characteristic){
		
		Label lblBoolean = new Label(composite, SWT.NONE);
		lblBoolean.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBoolean.setText(_characteristic.getLabel() + " :");
		
		Button btnYes = new Button(composite, SWT.RADIO);
		btnYes.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnYes.setText("Yes");
		try {
			btnYes.setSelection( _characteristic.getDataBoolean() );
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		Button btnNo = new Button(composite, SWT.RADIO);
		btnNo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnNo.setText("No");
		try {
			btnNo.setSelection( !_characteristic.getDataBoolean() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create an Integer of Float characteristic in the user interface.
	 * @param _characteristic
	 */
	private void createIntegerFloat(Characteristic _characteristic){
		Label lbl = new Label(composite, SWT.NONE);
		lbl.setAlignment(SWT.RIGHT);
		lbl.setText(_characteristic.getLabel()+" :");
		
		Scale scale = new Scale(composite, SWT.NONE);
		scale.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		try {
			
			if(_characteristic.getType() == CharacteristicType.INTEGER){
				scale.setSelection(_characteristic.getDataInteger());
			}
			else{
				scale.setSelection((int) _characteristic.getDataFloat());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
