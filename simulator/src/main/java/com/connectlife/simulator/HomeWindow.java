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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import com.clapi.data.*;


/**
 * 
 * 
 * @author ericpinet
 * <br> 2015-09-16
 */
public class HomeWindow extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txtUid;
	private Text txtName;
	private Home home;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public HomeWindow(Shell parent, int style, Home _home) {
		super(parent, style);
		setText("Home");
		home = _home;
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
		shell.setSize(650, 450);
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
		
		Tree tree = new Tree(shell, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TreeColumn trclmnName = new TreeColumn(tree, SWT.NONE);
		trclmnName.setWidth(200);
		trclmnName.setText("Name");
		
		TreeColumn trclmnUid = new TreeColumn(tree, SWT.NONE);
		trclmnUid.setWidth(200);
		trclmnUid.setText("Uid");
		
		TreeItem root = new TreeItem(tree, SWT.NONE);
		root.setText(new String[] { "Home: " + home.getLabel(), home.getUid() });
		
		tree.setHeaderVisible(true);
		
		
		Iterator<Zone> itr = home.getZones().iterator();
		while(itr.hasNext()){
			Zone zone = itr.next();
			TreeItem item = new TreeItem(root, SWT.NONE);
		    item.setText(new String[] { "Zone: " + zone.getLabel(), zone.getUid() });
		    
		    Iterator<Room> itrr = zone.getRooms().iterator();
		    while(itrr.hasNext()){
		    	Room room = itrr.next();
				TreeItem item2 = new TreeItem(item, SWT.NONE);
			    item2.setText(new String[] { "Room: " + room.getLabel(), room.getUid() });
			    
			    Iterator<Accessory> itra = room.getAccessories().iterator();
			    while(itra.hasNext()){
			    	Accessory acc = itra.next();
					TreeItem item3 = new TreeItem(item2, SWT.NONE);
				    item3.setText(new String[] { "Accessory: " + acc.getLabel(), acc.getUid() });
			    }
		    }
		}
		
		tree.setTopItem(root);
	}

}
