package com.connectlife.simulator;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.clapi.client.CLApiClient;
import com.clapi.data.Email;
import com.clapi.data.Email.EmailType;
import com.clapi.data.Person;
import org.eclipse.swt.widgets.Combo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class EmailWindow extends Dialog {
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(EmailWindow.class);
	protected Object result;
	private Shell shlEmail;
	private Person person;
	private int emailId;
	private CLApiClient client;
	private Text text;
	private Combo comboType;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public EmailWindow(Shell parent, int style, Person _person, int index, CLApiClient _client) {
		super(parent, style);
		setText("SWT Dialog");
		person = _person;
		emailId = index;
		client = _client;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlEmail.open();
		shlEmail.layout();
		Display display = getParent().getDisplay();
		while (!shlEmail.isDisposed()) {
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
		shlEmail = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlEmail.setSize(450, 119);
		shlEmail.setText("Email");
		
		Label lblEmail = new Label(shlEmail, SWT.NONE);
		lblEmail.setBounds(10, 10, 47, 19);
		lblEmail.setText("Email :");
		
		text = new Text(shlEmail, SWT.BORDER);
		text.setBounds(63, 10, 241, 19);
		
		comboType = new Combo(shlEmail, SWT.NONE);
		comboType.setItems(new String[] {"Personal", "Work", "Other"});
		comboType.setBounds(310, 10, 66, 22);
		comboType.setText("Type");
		if(emailId >= 0)
		{
			text.setText(person.getEmails().get(emailId).getEmail());
			comboType.select(emailId);
		}
		
		Button btnSave = new Button(shlEmail, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					if(emailId < 0)
					{
						client.AddEmail(person.getUid(), text.getText(), comboType.getSelectionIndex());
					}
					else
					{
						client.UpdateEmail(person.getEmails().get(emailId).getUid(), text.getText(), comboType.getSelectionIndex());
					}
					shlEmail.close();
				} catch (Exception e1) {
					m_logger.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(182, 38, 95, 28);
		btnSave.setText("Save");
		
		Button btnCancel = new Button(shlEmail, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					shlEmail.close();
				} catch (Exception e1) {
					m_logger.error(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnCancel.setBounds(283, 38, 95, 28);
		btnCancel.setText("Cancel");

	}
}
