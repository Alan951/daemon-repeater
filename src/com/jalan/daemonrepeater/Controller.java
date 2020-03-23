package com.jalan.daemonrepeater;

import java.net.BindException;

import burp.BurpExtender;

public class Controller {

	private UI ui;
	private BurpExtender extender;
	private SockMngt sockMngt;
	
	public Controller(UI ui, BurpExtender extender) {
		if(ui == null) {
			ui = new UI(this);
			ui.init();
		}
		
		this.ui = ui;
		this.extender = extender;
		this.sockMngt = new SockMngt(this);
	}
	
	public UI getUI() {
		return this.ui;
	}
	
	public boolean onStartService() {
		try {
			this.sockMngt.init();
			this.ui.getLblServiceStatus().setText(UI.SERVICE_STATUS_STARTED);
			return true;
		} catch (BindException e) {
			this.ui.getLblServiceStatus().setText(UI.SERVICE_STATUS_ERR_PORT);
			return false;
		}
		
	}
	
	public boolean onStopService() {
		this.sockMngt.stopService();
		this.ui.getLblServiceStatus().setText(UI.SERVICE_STATUS_STOPPED);
		return true;
	}
	
	public static void main(String []args) {
		Controller controller = new Controller(null, null);
		controller.getUI().testUI();
	}
	
	public void sendToRepeater(ReqRepeaterModel reqRepeater) {
		if(extender != null)
			this.extender.sendToRepeater(reqRepeater);
		else 
			System.out.println("Controller: Extender not initialized");
		
	}
	
	
}
