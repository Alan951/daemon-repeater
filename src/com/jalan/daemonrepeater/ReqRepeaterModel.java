package com.jalan.daemonrepeater;

public class ReqRepeaterModel {

	private String host;
	private int port;
	private boolean useHttps;
	private String request;
	private String tabName;
	
	public ReqRepeaterModel(String host, int port, boolean useHttps, String request, String tabName) {
		this.host = host;
		this.port = port;
		this.useHttps = useHttps;
		this.request = request;
		this.tabName = tabName;
	}
	
	public ReqRepeaterModel(String host, int port, boolean useHttps, String request) {
		this.host = host;
		this.port = port;
		this.useHttps = useHttps;
		this.request = request;
		this.tabName = null;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isUseHttps() {
		return useHttps;
	}

	public void setUseHttps(boolean useHttps) {
		this.useHttps = useHttps;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	@Override
	public String toString() {
		return "ReqRepeaterModel [host=" + host + ", port=" + port + ", useHttps=" + useHttps + ", request=" + request
				+ ", tabName=" + tabName + "]";
	}
}
