package com.jalan.daemonrepeater;

import java.net.BindException;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

import com.google.gson.Gson;
import com.jalan.cksock.MessageWrapper;
import com.jalan.cksock.SockConfig;
import com.jalan.cksock.SockLogger;
import com.jalan.cksock.SockServerService;

public class SockMngt {

	private SockServerService server;
	private Gson gson = new Gson();
	private Controller controller;
	
	
	public SockMngt(Controller controller) {
		LogManager.getRootLogger().setLevel(Level.DEBUG);
		SockLogger.autoConfigure();
		
		this.controller = controller;
	}
	
	public void init() throws BindException{
		SockConfig sc = new SockConfig(951);
		
		server = new SockServerService(sc);
		
		try {
			server.listen();
		} catch (BindException e) {
			e.printStackTrace();
			throw e;
		}
		
		server.getClientConnectionObserver().subscribe((event) -> {
			System.out.println(event.status);
		});
		
		server.getClientMessagesObserver().subscribe((message) -> onNewMessage(message));
		
	}
	
	public void onNewMessage(MessageWrapper message) {
		System.out.println("SockMngt: onNewMessage");
		
		ReqRepeaterModel reqRepeater = gson.fromJson(message.getPayload().toString(), ReqRepeaterModel.class);
		
		System.out.println(reqRepeater);
		
		this.controller.sendToRepeater(reqRepeater);
	}
	
	public void stopService() {
		System.out.println("SockMngt: Stop service invoked");
		server.stop();
	}
	
}
