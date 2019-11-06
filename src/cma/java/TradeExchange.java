package cma.java;

public class TradeExchange {

	public static void main(String[] args) {
		double finalRate = 0.0;
		double profitAmount = 0.0;
		double profitSGD = 0.0;

		Tran t = new Tran();
		Rate rate = new Rate();
		

		t.setFinalRate(t.getAmount() * t.getStdRate());
		double usdAmount = t.getFinalRate();
		while (t.getClientType() == "I") {
			if (usdAmount <= 8000) {
				usdAmount = 40 / 100;
			} else if (8001 <= 20000) {
				usdAmount = 35 / 100;
			} else if (20001 <= 35000) {
				usdAmount = 30 / 100;
			} else {
				usdAmount = 25 / 100;
			}

			finalRate = (t.getStdRate() * (1 - (usdAmount / 100)));
		} // while end

		while (t.getClientType() == "C") {
			if (usdAmount <= 1000000) {
				usdAmount = 15 / 100;
			} else if (1000001 <= 3000000) {
				usdAmount = 10 / 100;
			} else {
				usdAmount = 5 / 100;
			}

			finalRate = (t.getStdRate() * (1 - (usdAmount / 100)));
		} // while end

		profitAmount = (t.getStdRate() - finalRate) * t.getAmount();
		profitSGD = (t.getStdRate() * profitAmount);

	}
}
