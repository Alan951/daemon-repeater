package burp;


import java.awt.Component;

import javax.swing.SwingUtilities;

import com.jalan.daemonrepeater.Controller;
import com.jalan.daemonrepeater.ReqRepeaterModel;

import burp.IBurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.ITab;

public class BurpExtender implements IBurpExtender {
	
	private Controller controller;
	private BurpExtender instance = this;
	private IBurpExtenderCallbacks callbacks;

	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		callbacks.setExtensionName("Daemon Repeater");
		this.callbacks = callbacks;
		
		SwingUtilities.invokeLater(() -> {

			controller = new Controller(null, instance);
			
			callbacks.customizeUiComponent(controller.getUI().getMainPanel());
			
			ITab tab = new ITab() {
				
				@Override
				public Component getUiComponent() {
					return controller.getUI().getMainPanel();
				}
				
				@Override
				public String getTabCaption() {
					return "DaemonRepeater";
				}
			};
			
			callbacks.addSuiteTab(tab);
		});
	}
	
	public void sendToRepeater(ReqRepeaterModel reqRepeater) {
		this.callbacks.sendToRepeater(reqRepeater.getHost(), 
				reqRepeater.getPort(), 
				reqRepeater.isUseHttps(), 
				reqRepeater.getRequest().getBytes(), 
				reqRepeater.getTabName());
	}
	
}
