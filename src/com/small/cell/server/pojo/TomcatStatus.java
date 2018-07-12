package com.small.cell.server.pojo;

public class TomcatStatus {
	private String freeMemory;
	private String totalMemory;
	private String maxMemory;
	private String maxThreads;
	private String currentThreadCount;
	private String currentThreadBusy;
	private String maxProcessingTime;
	private String msProcessingTime;
	private String requestCount;
	private String errCount;
	private String bytesReceived;
	private String bytesSent;

	public String getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(String freeMemory) {
		this.freeMemory = freeMemory;
	}

	public String getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(String totalMemory) {
		this.totalMemory = totalMemory;
	}

	public String getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(String maxMemory) {
		this.maxMemory = maxMemory;
	}

	public String getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(String maxThreads) {
		this.maxThreads = maxThreads;
	}

	public String getCurrentThreadCount() {
		return currentThreadCount;
	}

	public void setCurrentThreadCount(String currentThreadCount) {
		this.currentThreadCount = currentThreadCount;
	}

	public String getCurrentThreadBusy() {
		return currentThreadBusy;
	}

	public void setCurrentThreadBusy(String currentThreadBusy) {
		this.currentThreadBusy = currentThreadBusy;
	}

	public String getMaxProcessingTime() {
		return maxProcessingTime;
	}

	public void setMaxProcessingTime(String maxProcessingTime) {
		this.maxProcessingTime = maxProcessingTime;
	}

	public String getMsProcessingTime() {
		return msProcessingTime;
	}

	public void setMsProcessingTime(String msProcessingTime) {
		this.msProcessingTime = msProcessingTime;
	}

	public String getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(String requestCount) {
		this.requestCount = requestCount;
	}

	public String getErrCount() {
		return errCount;
	}

	public void setErrCount(String errCount) {
		this.errCount = errCount;
	}

	public String getBytesReceived() {
		return bytesReceived;
	}

	public void setBytesReceived(String bytesReceived) {
		this.bytesReceived = bytesReceived;
	}

	public String getBytesSent() {
		return bytesSent;
	}

	public void setBytesSent(String bytesSent) {
		this.bytesSent = bytesSent;
	}

}
