/**
 *  MainWindow.java
 *  simulator
 *
 *  Created by ericpinet on 2015-09-13.
 *  Copyright (c) 2015 ConnectLife (Eric Pinet). All rights reserved.
 *
 */
package com.connectlife.simulator;

// external
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import com.google.gson.Gson;
import com.connectlife.clapi.*;
import com.connectlife.clapi.client.Client;
import com.connectlife.clapi.client.NotificationListener;

/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-13
 */
public class MainWindow implements NotificationListener {
	
	/**
	 * Init logger instance for this class
	 */
	private static Logger m_logger = LogManager.getLogger(MainWindow.class);

	protected Shell shell;
	private Text textHost;
	private Text textPort;
	private Label lblStatus;
	private Client client;
	
	private static final String HOST = "127.0.0.1";
	
	private static final String PORT = "9006";
	
	/**
	 * Start Person window.
	 * 
	 * @author ericpinet
	 * <br> 2015-09-13
	 */
	static class startPerson implements Runnable{
		public Person person;
		public Shell shell;
		public Client client;
		public startPerson(Person _person, Shell _shell, Client _client){
			person = _person;
			shell = _shell;
			client = _client;
		}
		public void run(){
			PersonWindow per = new PersonWindow(shell, 0, person, client);
	    	per.open();
		}
	}
	
	/**
	 * Start Home window.
	 * 
	 * @author ericpinet
	 * <br> 2015-09-13
	 */
	static class startHome implements Runnable{
		public Home home;
		public Shell shell;
		public startHome(Home _home, Shell _shell){
			home = _home;
			shell = _shell;
		}
		public void run(){
			HomeWindow per = new HomeWindow(shell, 0, home);
	    	per.open();
		}
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
			window.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(328, 210);
		shell.setText("ConnectLife - Simulator");
		shell.setLayout(new GridLayout(2, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblHost = new Label(shell, SWT.NONE);
		lblHost.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHost.setText("Host:");
		
		textHost = new Text(shell, SWT.BORDER);
		textHost.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textHost.setText(HOST);
		
		Label lblPort = new Label(shell, SWT.NONE);
		lblPort.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPort.setText("Port:");
		
		textPort = new Text(shell, SWT.BORDER);
		textPort.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textPort.setText(PORT);
		new Label(shell, SWT.NONE);
		
		Button btnConnect = new Button(shell, SWT.NONE);
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				
				lblStatus.setText("Connecting to ConnectLife ...");
				
				connectionWithServer();
				
			}
		});
		btnConnect.setText("Connect");
		new Label(shell, SWT.NONE);
		
		Button btnDisconnect = new Button(shell, SWT.NONE);
		btnDisconnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				
				lblStatus.setText("Disconnect ...");
				
				disconnect();
				
			}
		});
		btnDisconnect.setText("Disconnect");
		new Label(shell, SWT.NONE);
		
		lblStatus = new Label(shell, SWT.NONE);
		lblStatus.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		lblStatus.setText("Not connected");

	}
	
	/**
	 * 
	 */
	private void connectionWithServer(){
		
		try {
			if(null != client){
				client.close();
			}
			client = new Client(textHost.getText(), Integer.parseInt(textPort.getText()), this);
			
			m_logger.info( "Check compatibility with the server : " + client.checkCompatibility("1.1.0") );
			
			Data env = client.getData();
			
			// open persons
			Iterator<Person> itrp = env.getPersons().iterator();
			while(itrp.hasNext()){
				shell.getDisplay().asyncExec( new startPerson (itrp.next(), shell, client) );
			}
			  
			// open homes
			Iterator<Home> itrh = env.getHomeIterator();
			while(itrh.hasNext()){
				shell.getDisplay().asyncExec( new startHome(itrh.next(), shell) );
			}
			
			lblStatus.setText("Connected.");
			
			//client.close();
			
		}
		catch( Exception e){
			System.out.println(e.getMessage());
			lblStatus.setText("Not connected. "+e.getMessage());
		}
	}
	
	private void disconnect(){
		if(client != null)
			client.close();
		
		lblStatus.setText("Not connected.");
	}
	
	private static void perform(CLApi.Client client, Shell _shell, Client _client) throws TException
	{
	    String responseBody = client.getEnvironmentDataJson();
	    
	    // Deal with the response.
		// Use caution: ensure correct character encoding and is not binary data
		Gson gson = new Gson();
		Data env = gson.fromJson(new String(responseBody), Data.class);
		  
		// open persons
		Iterator<Person> itrp = env.getPersons().iterator();
		while(itrp.hasNext()){
			_shell.getDisplay().asyncExec( new startPerson (itrp.next(), _shell, _client) );
		}
		  
		// open homes
		Iterator<Home> itrh = env.getHomeIterator();
		while(itrh.hasNext()){
			_shell.getDisplay().asyncExec( new startHome(itrh.next(), _shell) );
		}
	}

	/**
	 * @param _notification
	 * @see com.connectlife.clapi.client.NotificationListener#notificationReceive(com.connectlife.clapi.Notification)
	 */
	public void notificationReceive(Notification _notification) {
		System.out.println(_notification.getData());	
	}

}
