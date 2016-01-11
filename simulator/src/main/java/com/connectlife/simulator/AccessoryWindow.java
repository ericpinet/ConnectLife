/**
 *  AccessoryWindow.java
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Tree;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-01-10
 */
public class AccessoryWindow extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text textUid;
	private Text textLabel;
	private Text textManufacturer;
	private Text textModel;
	private Text textSerialNumber;
	private Text textSetupInRoom;
	private Text textString;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AccessoryWindow(Shell parent, int style) {
		super(parent, SWT.DIALOG_TRIM);
		setText("SWT Dialog");
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
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(531, 331);
		shell.setText(getText());
		shell.setLayout(new GridLayout(3, false));
		
		Tree tree = new Tree(shell, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 6));
		
		Label lblUid = new Label(shell, SWT.NONE);
		lblUid.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUid.setText("UID :");
		
		textUid = new Text(shell, SWT.BORDER);
		textUid.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblLabel = new Label(shell, SWT.NONE);
		lblLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblLabel.setAlignment(SWT.RIGHT);
		lblLabel.setText("Label :");
		
		textLabel = new Text(shell, SWT.BORDER);
		textLabel.setText("");
		textLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblManufacturer = new Label(shell, SWT.NONE);
		lblManufacturer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblManufacturer.setAlignment(SWT.RIGHT);
		lblManufacturer.setText("Manufacturer :");
		
		textManufacturer = new Text(shell, SWT.BORDER);
		textManufacturer.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblModel = new Label(shell, SWT.NONE);
		lblModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModel.setAlignment(SWT.RIGHT);
		lblModel.setText("Model :");
		
		textModel = new Text(shell, SWT.BORDER);
		textModel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblSerialNumber = new Label(shell, SWT.NONE);
		lblSerialNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSerialNumber.setAlignment(SWT.RIGHT);
		lblSerialNumber.setText("Serial Number :");
		
		textSerialNumber = new Text(shell, SWT.BORDER);
		textSerialNumber.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblSetupInRoom = new Label(shell, SWT.NONE);
		lblSetupInRoom.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSetupInRoom.setAlignment(SWT.RIGHT);
		lblSetupInRoom.setText("Setup in room :");
		
		textSetupInRoom = new Text(shell, SWT.BORDER);
		textSetupInRoom.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblBoolean = new Label(shell, SWT.NONE);
		lblBoolean.setAlignment(SWT.RIGHT);
		lblBoolean.setText("Boolean :");
		
		Button btnYes = new Button(shell, SWT.RADIO);
		btnYes.setText("Yes");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnNo = new Button(shell, SWT.RADIO);
		btnNo.setText("No");
		new Label(shell, SWT.NONE);
		
		Label lblFloat = new Label(shell, SWT.NONE);
		lblFloat.setAlignment(SWT.RIGHT);
		lblFloat.setText("Float :");
		
		Scale scaleFloat = new Scale(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblInteger = new Label(shell, SWT.NONE);
		lblInteger.setAlignment(SWT.RIGHT);
		lblInteger.setText("Integer :");
		
		Scale scaleInteger = new Scale(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblString = new Label(shell, SWT.NONE);
		lblString.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblString.setText("String :");
		
		textString = new Text(shell, SWT.BORDER);
		textString.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}
}
