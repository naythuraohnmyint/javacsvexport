package cma.java;

public class Tran implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double stdRate;
	private String baseCCY;
	private String wantedCCY;
	private int amount;
	private String clientType;
	private String time;
	private double finalRate;
	private double profitInWCCY;
	private double profitInSGD;
	
	public double getStdRate() {
		return stdRate;
	}
	public void setStdRate(double stdRate) {
		this.stdRate = stdRate;
	}
	public String getBaseCCY() {
		return baseCCY;
	}
	public void setBaseCCY(String baseCCY) {
		this.baseCCY = baseCCY;
	}
	public String getWantedCCY() {
		return wantedCCY;
	}
	public void setWantedCCY(String wantedCCY) {
		this.wantedCCY = wantedCCY;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getFinalRate() {
		return finalRate;
	}
	public void setFinalRate(double finalRate) {
		this.finalRate = finalRate;
	}
	public double getProfitInWCCY() {
		return profitInWCCY;
	}
	public void setProfitInWCCY(double profitInWCCY) {
		this.profitInWCCY = profitInWCCY;
	}
	public double getProfitInSGD() {
		return profitInSGD;
	}
	public void setProfitInSGD(double profitInSGD) {
		this.profitInSGD = profitInSGD;
	}	
}
