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
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import com.google.gson.Gson;
import com.clapi.client.CLApiClient;
import com.clapi.data.*;
import com.connectlife.simulator.device.Device;
import com.connectlife.simulator.device.LightColoredDimmable;
import com.clapi.client.NotificationListener;

/**
 * Main window of the simulator application.
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
	private Label lblDevice;
	private CLApiClient client;
	private Vector<Device> m_devices;
	
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
		public CLApiClient client;
		public startPerson(Person _person, Shell _shell, CLApiClient _client){
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
		
		m_devices = new Vector<Device>();
		
		shell = new Shell();
		shell.setSize(328, 255);
		shell.setText("ConnectLife - Simulator");
		shell.setLayout(new GridLayout(2, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblHost = new Label(shell, SWT.NONE);
		lblHost.setText("Host:");
		
		textHost = new Text(shell, SWT.BORDER);
		textHost.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textHost.setText(HOST);
		
		Label lblPort = new Label(shell, SWT.NONE);
		lblPort.setText("Port:");
		
		textPort = new Text(shell, SWT.BORDER);
		textPort.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textPort.setText(PORT);
		new Label(shell, SWT.NONE);
		
		Button btnConnect = new Button(shell, SWT.NONE);
		btnConnect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
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
		btnDisconnect.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
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
		lblStatus.setAlignment(SWT.CENTER);
		lblStatus.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		lblStatus.setText("Not connected");
		new Label(shell, SWT.NONE);
		
		Button btnCreateDevice = new Button(shell, SWT.NONE);
		btnCreateDevice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				/*
				// Create the light
				Light light = new Light("Light", "Philips", "Hue0", "122-1770", "");
				light.startServices();				
				m_devices.addElement(light);
				
				// Create the light dimmable
				LightDimmable lightdim = new LightDimmable("LightDim", "Philips", "Hue1", "123-1772", "");
				lightdim.startServices();				
				m_devices.addElement(lightdim);
				*/
				// Create the light colored dimmable
				LightColoredDimmable lightcoldim = new LightColoredDimmable("LightColorDim", "Philips", "Hue1", "122-2232", "");
				lightcoldim.startServices();				
				m_devices.addElement(lightcoldim);
				
				lblDevice.setText("Device listen");
			}
		});
		btnCreateDevice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnCreateDevice.setText("Create Device");
		new Label(shell, SWT.NONE);
		
		lblDevice = new Label(shell, SWT.NONE);
		lblDevice.setAlignment(SWT.CENTER);
		lblDevice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblDevice.setText("No device");
		
		
	}
	
	/**
	 * Connect client
	 */
	private void connectionWithServer(){
		
		try {
			
			if(client!=null){
				client.shutdown();
			}
			
			CLApiClient client = new CLApiClient(textHost.getText(), Integer.parseInt(textPort.getText()), this);
			
			m_logger.info( "Get server version : " + client.getVersion() );
			m_logger.info( "check compatibility with the server : " + client.checkCompatibility() );
			
			String json_data = client.getJsonData();
			Gson gson = new Gson();
			Data env = gson.fromJson(json_data, Data.class);
			
			// open persons
			Iterator<Person> itrp = env.getPersons().iterator();
			while(itrp.hasNext()){
				shell.getDisplay().asyncExec( new startPerson (itrp.next(), shell, client) );
			}
			  
			// open homes
			Iterator<Home> itrh = env.getHomes().iterator();
			while(itrh.hasNext()){
				shell.getDisplay().asyncExec( new startHome(itrh.next(), shell) );
			}
			
			// Client start his listener on the environment change.
			client.waitNotification();
			
			m_logger.info("Connected.");
			lblStatus.setText("Connected.");
			
		}
		catch( Exception e){
			System.out.println(e.getMessage());
			lblStatus.setText("Not connected. "+e.getMessage());
		}
	}
	
	/**
	 * Disconnect client
	 */
	private void disconnect(){
		
		try {
			if(client != null){
				client.shutdown();
			}
		} catch (InterruptedException e) {
			m_logger.error(e.getMessage());
			e.printStackTrace();
		}
		m_logger.info("Not connected.");
		lblStatus.setText("Not connected.");
	}

	/**
	 * @param _notification
	 * @see com.clapi.client.NotificationListener#notificationReceive(com.clapi.Notification)
	 */
	@Override
	public void notificationReceive(com.clapi.Notification _notification) {
		m_logger.info( "WaitNotification recieved." );
	}

}