/**
 *  PersonWindow.java
 *  simulator
 *
 *  Created by ericpinet on 2015-09-13.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator;

// external
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.graphics.Image;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.clapi.client.CLApiClient;
import com.clapi.data.*;
import com.clapi.data.Address.AddressType;
import com.clapi.data.Email.EmailType;
import com.clapi.data.Phone.PhoneType;
import com.connectlife.simulator.cache.CacheFile;
import com.connectlife.simulator.image.ImageResize;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-13
 */
public class PersonWindow extends Dialog {
	
	/**
	 * Init logger instance for this class
	 */
	private Logger m_logger = LogManager.getLogger(getClass().getName());

	protected Object result;
	protected Shell shell;
	private Text txtUid;
	private Text txtFirstname;
	private Text txtLastname;
	private Table tableEmail;
	private Table tablePhoneNumbers;
	private Table tableAddress;
	
	private Person person;
	private CLApiClient client;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PersonWindow(Shell parent, int style, Person _person, CLApiClient _client) {
		super(parent, style);
		setText("Person");
		person = _person;
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
		Display display = Display.getDefault();
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
	private void createContents() {
		shell = new Shell();
		shell.setSize(600, 650);
		shell.setText(getText());
		shell.setLayout(new GridLayout(2, false));
		
		Label lblUid = new Label(shell, SWT.NONE);
		lblUid.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUid.setText("UID:");
		
		txtUid = new Text(shell, SWT.BORDER);
		txtUid.setText(person.getUid());
		txtUid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFirstName.setText("First name:");
		
		txtFirstname = new Text(shell, SWT.BORDER);
		txtFirstname.setText(person.getFirstname());
		txtFirstname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLastName.setText("Last name:");
		
		txtLastname = new Text(shell, SWT.BORDER);
		if(null != person.getLastname())
			txtLastname.setText(person.getLastname());
		txtLastname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblEmails = new Label(shell, SWT.NONE);
		lblEmails.setText("Emails:");
		
		tableEmail = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tableEmail = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tableEmail.heightHint = 89;
		tableEmail.setLayoutData(gd_tableEmail);
		tableEmail.setHeaderVisible(true);
		tableEmail.setLinesVisible(true);
		
		TableColumn tblclmnEmail = new TableColumn(tableEmail, SWT.NONE);
		tblclmnEmail.setWidth(300);
		tblclmnEmail.setText("Email");
		
		TableColumn tblclmnType = new TableColumn(tableEmail, SWT.NONE);
		tblclmnType.setWidth(100);
		tblclmnType.setText("Type");
		
		// load table informations.
		Iterator<Email> itr = person.getEmails().iterator();
		if(null != itr){
			while(itr.hasNext()){
				Email email = itr.next();
				String type = "";
				if(EmailType.PERSONAL == email.getType())
					type = "Personal";
				else if (EmailType.WORK == email.getType())
					type = "Work";
				else if (EmailType.OTHER == email.getType())
					type = "Other";
				
				TableItem item = new TableItem(tableEmail, SWT.NONE);
				item.setText(new String[]{email.getEmail(), type});
			}
		}
		
		Label lblPhoneNumbers = new Label(shell, SWT.NONE);
		lblPhoneNumbers.setText("Phone numbers:");
		
		tablePhoneNumbers = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tablePhoneNumbers = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tablePhoneNumbers.heightHint = 71;
		tablePhoneNumbers.setLayoutData(gd_tablePhoneNumbers);
		tablePhoneNumbers.setHeaderVisible(true);
		tablePhoneNumbers.setLinesVisible(true);
		
		TableColumn tblclmnPhoneNumber = new TableColumn(tablePhoneNumbers, SWT.NONE);
		tblclmnPhoneNumber.setWidth(300);
		tblclmnPhoneNumber.setText("Phone number");
		
		TableColumn tblclmnType_1 = new TableColumn(tablePhoneNumbers, SWT.NONE);
		tblclmnType_1.setWidth(100);
		tblclmnType_1.setText("Type");
		
		// load table informations.
		Iterator<Phone> itrp = person.getPhones().iterator();
		if(null != itrp){
			while(itrp.hasNext()){
				Phone phone = itrp.next();
				String type = "";
				if(PhoneType.CELL == phone.getType())
					type = "Cellular";
				else if(PhoneType.HOME == phone.getType())
					type = "Home";
				else if (PhoneType.WORK == phone.getType())
					type = "Work";
				else if (PhoneType.OTHER == phone.getType())
					type = "Other";
				
				TableItem item = new TableItem(tablePhoneNumbers, SWT.NONE);
				item.setText(new String[]{phone.getPhone(), type});
			}
		}
		
		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setText("Address:");
		
		tableAddress = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tableAddress = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tableAddress.heightHint = 73;
		tableAddress.setLayoutData(gd_tableAddress);
		tableAddress.setHeaderVisible(true);
		tableAddress.setLinesVisible(true);
		
		TableColumn tblclmnStreet = new TableColumn(tableAddress, SWT.NONE);
		tblclmnStreet.setWidth(100);
		tblclmnStreet.setText("Street");
		
		TableColumn tblclmnCity = new TableColumn(tableAddress, SWT.NONE);
		tblclmnCity.setWidth(100);
		tblclmnCity.setText("City");
		
		TableColumn tblclmnRegion = new TableColumn(tableAddress, SWT.NONE);
		tblclmnRegion.setWidth(100);
		tblclmnRegion.setText("Region");
		
		TableColumn tblclmnZipcode = new TableColumn(tableAddress, SWT.NONE);
		tblclmnZipcode.setWidth(100);
		tblclmnZipcode.setText("ZipCode");
		
		TableColumn tblclmnCountry = new TableColumn(tableAddress, SWT.NONE);
		tblclmnCountry.setWidth(100);
		tblclmnCountry.setText("Country");
		
		TableColumn tblclmnType_2 = new TableColumn(tableAddress, SWT.NONE);
		tblclmnType_2.setWidth(100);
		tblclmnType_2.setText("Type");
		
		// load table informations.
		Iterator<Address> itra = person.getAddresses().iterator();
		if(null != itra){
			while(itra.hasNext()){
				Address address = itra.next();
				String type = "";
				if(AddressType.HOME == address.getType())
					type = "Home";
				else if(AddressType.WORK == address.getType())
					type = "Work";
				else if (AddressType.OTHER == address.getType())
					type = "Other";
				
				TableItem item = new TableItem(tableAddress, SWT.NONE);
				item.setText(new String[]{address.getStreet(), address.getCity(), address.getRegion(), address.getZipcode(), address.getCountry() , type});
			}
		}
		
		// TODO Load image
		Canvas canvas = new Canvas(shell, SWT.NONE);		
		GridData gd_canvas = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_canvas.heightHint = 15;
		gd_canvas.widthHint = 19;
		canvas.setLayoutData(gd_canvas);
		
		new Label(shell, SWT.NONE);
		
		Label lblPicture = new Label(shell, SWT.NONE);
		lblPicture.setText("Picture:");
		CacheFile file = new CacheFile(client.getAssetUrl(person.getImageuid()), person.getImageuid());
		Image image = new Image(canvas.getDisplay(), file.getFile().getAbsolutePath());
		Label lblImage = new Label(shell, SWT.NONE);
		lblImage.setImage(ImageResize.resize(image,100,100));
		image.dispose();
		new Label(shell, SWT.NONE);
		
		Button btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try {
					person.setFirstname("Eric2");
					client.addPerson(person.getFirstname(), person.getLastname(), "");
					
				} catch (Exception e1) {
					m_logger.error(e1.getMessage());
					e1.printStackTrace();
				}
				
			}
		});
		btnAdd.setText("Add");
		new Label(shell, SWT.NONE);
		
		Button btnUpdate = new Button(shell, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try {
					person.setFirstname(txtFirstname.getText());
					person.setLastname(txtLastname.getText());
					//person.setImageurl();
					client.updatePerson(person.getUid(), person.getFirstname(), person.getLastname(), "");
					
				} catch (Exception e1) {
					m_logger.error(e1.getMessage());
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setText("Update");
		new Label(shell, SWT.NONE);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					person.setFirstname(txtFirstname.getText());
					person.setLastname(txtLastname.getText());
					//person.setImageurl();
					client.deletePerson(person.getUid());
					
				} catch (Exception e1) {
					m_logger.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		button.setText("Delete");
		new Label(shell, SWT.NONE);
		
		Button btnAddEmail = new Button(shell, SWT.NONE);
		
		btnAddEmail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					EmailWindow emailWin = new EmailWindow(shell, getStyle(), person, -1, client);
					emailWin.open();
				} catch (Exception e1) {
					m_logger.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		
		btnAddEmail.setText("Add Email");
		new Label(shell, SWT.NONE);
		
		Button btnUpdateEmail = new Button(shell, SWT.NONE);
		btnUpdateEmail.setText("Update Email");
		new Label(shell, SWT.NONE);
		btnUpdateEmail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					EmailWindow emailWin = new EmailWindow(shell, getStyle(), person, tableEmail.getSelectionIndex(), client);
					emailWin.open();
				} catch (Exception e1) {
					m_logger.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		
		
		Button btnDeleteEmail = new Button(shell, SWT.NONE);
		btnDeleteEmail.setText("Delete Email");
	}
	
	
}
