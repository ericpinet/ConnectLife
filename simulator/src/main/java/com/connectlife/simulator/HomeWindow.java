/**
 *  HomeWindow.java
 *  simulator
 *
 *  Created by ericpinet on 2015-09-16.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator;

// external
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.clapi.client.CLApiClient;
import com.clapi.data.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-16
 */
public class HomeWindow extends Dialog {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(HomeWindow.class);

	protected Object result;
	protected Shell shell;
	private Text txtUid;
	private Text txtName;
	private Home home;
	private StyledText styledTextDetails;
	private Tree tree;
	private Composite composite;
	private CLApiClient client;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public HomeWindow(Shell parent, int style, Home _home, CLApiClient _client) {
		super(parent, style);
		setText("Home");
		home = _home;
		client = _client;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	@SuppressWarnings("unused")
	private void createContents() {
		shell = new Shell();
		shell.setSize(650, 768);
		shell.setText(getText());
		shell.setLayout(new GridLayout(2, false));
		
		Label lblUid = new Label(shell, SWT.NONE);
		lblUid.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUid.setText("Uid:");
		
		txtUid = new Text(shell, SWT.BORDER);
		txtUid.setText(home.getUid());
		txtUid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name:");
		
		txtName = new Text(shell, SWT.BORDER);
		txtName.setText(home.getLabel());
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPicture = new Label(shell, SWT.NONE);
		lblPicture.setText("Picture:");
		
		Canvas canvasPicture = new Canvas(shell, SWT.NONE);
		// TODO: Load image
		
		Label lblZones = new Label(shell, SWT.NONE);
		lblZones.setText("Zones:");
		
		tree = new Tree(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] items = tree.getSelection();
				//only select the first
				if( items[0] != null ){
					Home home = null;
					Zone zone = null;
					Room room = null;
					Accessory acc = null;
					
					// check if item is a home
					try{
						home = (Home)items[0].getData();
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						String json = gson.toJson(home);
						styledTextDetails.setText(json);
						
						removeActions();
						createActionForHome();
					}
					catch(Exception e1){
						// invalid type, do nothing.
					}
					
					// check if item is a zone
					try{
						zone = (Zone)items[0].getData();
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						String json = gson.toJson(zone);
						styledTextDetails.setText(json);
						
						removeActions();
						createActionForZone();
					}
					catch(Exception e1){
						// invalid type, do nothing.
					}
					
					// check if item is a room
					try{
						room = (Room)items[0].getData();
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						String json = gson.toJson(room);
						styledTextDetails.setText(json);
						
						removeActions();
						createActionForRoom();
					}
					catch(Exception e2){
						// invalid type, do nothing.
					}
					
					// check if item is a accessory
					try{
						acc = (Accessory)items[0].getData();
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						String json = gson.toJson(acc);
						styledTextDetails.setText(json);
						
						removeActions();
						createActionForAccessory();
					}
					catch(Exception e3){
						// invalid type, do nothing.
					}
				}
			}
		});
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TreeColumn trclmnName = new TreeColumn(tree, SWT.NONE);
		trclmnName.setWidth(200);
		trclmnName.setText("Name");
		
		TreeColumn trclmnUid = new TreeColumn(tree, SWT.NONE);
		trclmnUid.setWidth(200);
		trclmnUid.setText("Uid");
		
		Label lblDetails = new Label(shell, SWT.NONE);
		lblDetails.setText("Details:");
		
		styledTextDetails = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.READ_ONLY);
		styledTextDetails.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblActions = new Label(shell, SWT.NONE);
		lblActions.setText("Actions:");
		new Label(shell, SWT.NONE);

		composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 10);
		gd_composite.heightHint = 100;
		gd_composite.widthHint = 600;
		composite.setLayoutData(gd_composite);
		
		UpdateData(home);
	}
	
	public void UpdateData(Home _home){
		this.home = _home;
		
		txtUid.setText(home.getUid());
		txtName.setText(home.getLabel());
		
		TreeItem root = tree.getTopItem();
		if (null == root) {
			root = new TreeItem(tree, SWT.NONE);
		}
		root.setData(home);
		root.setText(new String[] { "Home: " + home.getLabel(), home.getUid() });
		root.removeAll();
		
		Iterator<Zone> itr = home.getZones().iterator();
		while(itr.hasNext()){
			Zone zone = itr.next();
			TreeItem item = new TreeItem(root, SWT.NONE);
			item.setData(zone);
		    item.setText(new String[] { "Zone: " + zone.getLabel(), zone.getUid() });
		    
		    Iterator<Room> itrr = zone.getRooms().iterator();
		    while(itrr.hasNext()){
		    	Room room = itrr.next();
				TreeItem item2 = new TreeItem(item, SWT.NONE);
				item2.setData(room);
			    item2.setText(new String[] { "Room: " + room.getLabel(), room.getUid() });
			    
			    Iterator<Accessory> itra = room.getAccessories().iterator();
			    while(itra.hasNext()){
			    	Accessory acc = itra.next();
					TreeItem item3 = new TreeItem(item2, SWT.NONE);
					item3.setData(acc);
				    item3.setText(new String[] { "Accessory: " + acc.getLabel(), acc.getUid() });
			    }
			    
			    item2.setExpanded(true);
		    }
		    item.setExpanded(true);
		}
		
		tree.setTopItem(root);
		root.setExpanded(true);
		styledTextDetails.setText("");
		removeActions();
	}

	private void createActionForHome () {
		
		Button btnDelete = new Button(composite, SWT.BUTTON1);
		btnDelete.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnDelete.setText("Delete Home");

		Button btnEditHome = new Button(composite, SWT.BUTTON1);
		btnEditHome.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnEditHome.setText("Edit Home");
		
		Button btnAddZone = new Button(composite, SWT.BUTTON1);
		btnAddZone.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnAddZone.setText("Add Zone");
		
		composite.layout(true, true);
	}
	
	private void createActionForZone () {
		
		Button btnDelete = new Button(composite, SWT.BUTTON1);
		btnDelete.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnDelete.setText("Delete Zone");
		
		Button btnEditZone = new Button(composite, SWT.BUTTON1);
		btnEditZone.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnEditZone.setText("Edit Zone");
		
		Button btnAddRoom = new Button(composite, SWT.BUTTON1);
		btnAddRoom.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnAddRoom.setText("Add Room");
		
		composite.layout(true, true);
	}
	
	private void createActionForRoom () {
		
		Button btnDelete = new Button(composite, SWT.BUTTON1);
		btnDelete.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnDelete.setText("Delete Room");
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree.getSelection();
					if (null != items[0]) {

							client.deleteRoom(((Room)items[0].getData()).getUid());
					}
				}
				catch (Exception ex) {
					m_logger.error(ex.getMessage());
				}
			}
		});
		
		Button btnEditRoom = new Button(composite, SWT.BUTTON1);
		btnEditRoom.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnEditRoom.setText("Edit Room");
		btnEditRoom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree.getSelection();
					if (null != items[0]) {

						AddAccessoryDialog dialog = new AddAccessoryDialog(shell);
						// user pressed cancel
						if (dialog.open() == Window.OK) {
							String result = dialog.getResult(); 							
							client.addAccessory(result, ((Room)items[0].getData()).getUid());
						}
					}
				}
				catch (Exception ex) {
					m_logger.error(ex.getMessage());
				}
			}
		});
		
		Button btnAddAccessory = new Button(composite, SWT.BUTTON1);
		btnAddAccessory.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnAddAccessory.setText("Add Accessory");
		btnAddAccessory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree.getSelection();
					if (null != items[0]) {

						AddAccessoryDialog dialog = new AddAccessoryDialog(shell);
						// user pressed cancel
						if (dialog.open() == Window.OK) {
							String result = dialog.getResult(); 							
							client.addAccessory(result, ((Room)items[0].getData()).getUid());
						}
					}
				}
				catch (Exception ex) {
					m_logger.error(ex.getMessage());
				}
			}
		});
		
		composite.layout(true, true);
	}
	
	private void createActionForAccessory () {
		
		Button btnDelete = new Button(composite, SWT.BUTTON1);
		btnDelete.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		btnDelete.setText("Delete Accessory");
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					TreeItem[] items = tree.getSelection();
					if (null != items[0]) {
						client.deleteAccessory(((Accessory)items[0].getData()).getUid());
					}
				}
				catch (Exception ex) {
					m_logger.error(ex.getMessage());
				}
			}
		});
		
		composite.layout(true, true);
	}
	
	/**
	 * Remove the current characteristics from the user interface.
	 */
	private void removeActions(){
		Control[] controls = composite.getChildren();
		for(int i=0 ; i<controls.length ; i++){
			controls[i].dispose();
		}
		composite.layout(true, true);
	}

}
