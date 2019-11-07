/**
 * 
 */
package sg.com.javatest.application;

/**
 * Business logic for ExpectedOutput CSV file
 *
 * @author Chan Mya Aye
 * @version 1.0
 * @since 2019-11-07
 *
 */
public class ExpectedOutput implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String baseCCY;
	private String wantedCCY;
	private double amountInBaseCCY;
	private double stdRate;
	private double finalRate;
	private double profitInWantedCCY;
	private double profitInSGD;

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

	public double getAmountInBaseCCY() {
		return amountInBaseCCY;
	}

	public void setAmountInBaseCCY(double amountInBaseCCY) {
		this.amountInBaseCCY = amountInBaseCCY;
	}

	public double getStdRate() {
		return stdRate;
	}

	public void setStdRate(double stdRate) {
		this.stdRate = stdRate;
	}

	public double getFinalRate() {
		return finalRate;
	}

	public void setFinalRate(double finalRate) {
		this.finalRate = finalRate;
	}

	public double getProfitInWantedCCY() {
		return profitInWantedCCY;
	}

	public void setProfitInWantedCCY(double profitInWantedCCY) {
		this.profitInWantedCCY = profitInWantedCCY;
	}

	public double getProfitInSGD() {
		return profitInSGD;
	}

	public void setProfitInSGD(double profitInSGD) {
		this.profitInSGD = profitInSGD;
	}

}
