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

import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.connectlife.coreserver.environment.data.Address;
import com.connectlife.coreserver.environment.data.Email;
import com.connectlife.coreserver.environment.data.Person;
import com.connectlife.coreserver.environment.data.PhoneNumber;


/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-13
 */
public class PersonWindow extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtUid;
	private Text txtFirstname;
	private Text txtLastname;
	private Table tableEmail;
	private Table tablePhoneNumbers;
	private Table tableAddress;
	
	private Person person;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PersonWindow(Shell parent, int style, Person _person) {
		super(parent, style);
		setText("Person");
		person = _person;
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
		shell.setSize(600, 399);
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
		txtFirstname.setText(person.getFirstName());
		txtFirstname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblLastName = new Label(shell, SWT.NONE);
		lblLastName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLastName.setText("Last name:");
		
		txtLastname = new Text(shell, SWT.BORDER);
		txtLastname.setText(person.getLastName());
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
		while(itr.hasNext()){
			Email email = itr.next();
			String type = "";
			if(Email.Type.PERSONAL == email.getType())
				type = "Personal";
			else if (Email.Type.WORK == email.getType())
				type = "Work";
			else if (Email.Type.OTHER == email.getType())
				type = "Other";
			
			TableItem item = new TableItem(tableEmail, SWT.NONE);
			item.setText(new String[]{email.getEmail(), type});
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
		Iterator<PhoneNumber> itrp = person.getPhoneNumbers().iterator();
		while(itrp.hasNext()){
			PhoneNumber phone = itrp.next();
			String type = "";
			if(PhoneNumber.Type.CELL == phone.getType())
				type = "Cellular";
			else if(PhoneNumber.Type.HOME == phone.getType())
				type = "Home";
			else if (PhoneNumber.Type.WORK == phone.getType())
				type = "Work";
			else if (PhoneNumber.Type.OTHER == phone.getType())
				type = "Other";
			
			TableItem item = new TableItem(tablePhoneNumbers, SWT.NONE);
			item.setText(new String[]{phone.getPhoneNumber(), type});
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
		Iterator<Address> itra = person.getAddress().iterator();
		while(itra.hasNext()){
			Address address = itra.next();
			String type = "";
			if(Address.Type.HOME == address.getType())
				type = "Home";
			else if(Address.Type.WORK == address.getType())
				type = "Work";
			else if (Address.Type.OTHER == address.getType())
				type = "Other";
			
			TableItem item = new TableItem(tableAddress, SWT.NONE);
			item.setText(new String[]{address.getStreet(), address.getCity(), address.getRegion(), address.getZipCode(), address.getCountry() , type});
		}
		
		Label lblPicture = new Label(shell, SWT.NONE);
		lblPicture.setText("Picture:");
		
		// TODO Load image
		@SuppressWarnings("unused")
		Canvas canvas = new Canvas(shell, SWT.NONE);
	}

}
