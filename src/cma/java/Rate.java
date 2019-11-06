package cma.java;
public class Rate implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String baseCCY;
	private String wantedCCY;
	private double stdRate;
	private String time;


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

	public double getStdRate() {
		return stdRate;
	}

	public void setStdRate(double stdRate) {
		this.stdRate = stdRate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
