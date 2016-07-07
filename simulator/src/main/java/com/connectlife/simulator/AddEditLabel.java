/**
 *  AddAccessoryDialog.java
 *  simulator
 *
 *  Created by ericpinet on 2016-06-26.
 *  Copyright (c) 2016 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator;

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
import org.eclipse.swt.widgets.Text;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2016-06-26
 */
public class AddEditLabel extends Dialog {

	private String result;
	
	private String title;
	
	private String description;
	
	private Text textLabel;
	
	private String default_value;

	/**
	 * @param parent
	 */
	public AddEditLabel(Shell parent, String title, String description, String default_value) {
		super(parent);
		this.title = title;
		this.description = description;
		this.default_value = default_value;
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
		shell.setText(this.title);

		shell.setLayout(new GridLayout(2, true));

		Label label = new Label(shell, SWT.NULL);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText(this.description);
		
		textLabel = new Text(shell, SWT.BORDER);
		textLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textLabel.setText(default_value);

		final Button buttonOK = new Button(shell, SWT.PUSH);
		buttonOK.setText("Ok");
		buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		Button buttonCancel = new Button(shell, SWT.PUSH);
		buttonCancel.setText("Cancel");

		buttonOK.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				result = textLabel.getText();
				shell.dispose();
			}
		});

		buttonCancel.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				result = "";  
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

		return (result.isEmpty() ? Window.CANCEL : Window.OK );
	}

	/**
	 * Return the result. 
	 * 
	 * @return
	 */
	public String getResult() {
		return result;
	}

}
