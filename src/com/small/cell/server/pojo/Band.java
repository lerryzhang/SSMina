package com.small.cell.server.pojo;

public class Band {
	private int band;
	private int ulfreq;
	private int dlfreq;
	private int pci;

	public Band(int band, int ulfreq, int dlfreq, int pci) {
		super();
		this.band = band;
		this.ulfreq = ulfreq;
		this.dlfreq = dlfreq;
		this.pci = pci;
	}

	public int getBand() {
		return band;
	}

	public void setBand(int band) {
		this.band = band;
	}

	public int getUlfreq() {
		return ulfreq;
	}

	public void setUlfreq(int ulfreq) {
		this.ulfreq = ulfreq;
	}

	public int getDlfreq() {
		return dlfreq;
	}

	public void setDlfreq(int dlfreq) {
		this.dlfreq = dlfreq;
	}

	public int getPci() {
		return pci;
	}

	public void setPci(int pci) {
		this.pci = pci;
	}

}
