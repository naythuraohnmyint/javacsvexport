package sg.com.javatest.main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import sg.com.javatest.application.Rate;
import sg.com.javatest.application.Transaction;
import sg.com.javatest.util.CommonUtil;
import sg.com.javatest.util.CsvFileReader;

public class Test {

	public static String absolutePath = (Paths.get("").toAbsolutePath().toString());

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Transaction> transaction = new ArrayList<Transaction>();
		transaction = CsvFileReader.readTranfromCSV(absolutePath + "\\src\\transaction.csv");

		double finalRate = 0.0;
		double profitAmount = 0.0;
		double profitSGD = 0.0;

		for (int i = 0; i < transaction.size(); i++) {
			Transaction t = new Transaction();
			t = transaction.get(i);
			System.out.println("\n-----------------------------------------");
			System.out.println(t.getClientType()+" ("+t.getBaseCCY()+" -> "+t.getWantedCCY()+")");

			List<Rate> list = CommonUtil.RetrieveWantRate(t.getBaseCCY(), t.getWantedCCY());
			t.setStdRate(Double.valueOf(CommonUtil.RetriveStdRate(list, CommonUtil.ConvertStringToDate(t.getTime()))));

			// 4 decimals for standard rate
			BigDecimal bd = new BigDecimal(t.getStdRate()).setScale(4, RoundingMode.DOWN);
			profitAmount = bd.doubleValue();
			System.out.println("Standard Rate : " + t.getStdRate());

			double usdAmount = t.getAmount();
			if (!t.getWantedCCY().equals("USD")) {
				List<Rate> usdlist = CommonUtil.RetrieveWantRate(t.getBaseCCY(), "USD");
				usdAmount = Double
						.valueOf(CommonUtil.RetriveStdRate(usdlist, CommonUtil.ConvertStringToDate(t.getTime())))
						* t.getAmount();
			}
			if (t.getClientType().equals("Individual")) {
				double dps = CommonUtil.CheckIndRate(usdAmount);
				System.out.println("dps : " + dps);
				finalRate = (t.getStdRate() * (1 - (dps / 100)));
				BigDecimal fd = new BigDecimal(finalRate).setScale(4, RoundingMode.CEILING);
				finalRate = fd.doubleValue();
			}
			if (t.getClientType().equals("Corporate")) {
				double dps = CommonUtil.CheckCorpRate(usdAmount);
				System.out.println("dps : " + dps);
				finalRate = (t.getStdRate() * (1 - (dps / 100)));

				BigDecimal fd = new BigDecimal(finalRate).setScale(4, RoundingMode.CEILING);
				finalRate = fd.doubleValue();
			} // while end
			t.setFinalRate(finalRate);
			System.out.println("Final Rate : " + t.getFinalRate());

			profitAmount = (t.getStdRate() - finalRate) * t.getAmount();
			BigDecimal pd = new BigDecimal(profitAmount).setScale(2, RoundingMode.DOWN);
			profitAmount = pd.doubleValue();
			System.out.println("Profit In Wanted : " + profitAmount);

			// IF wanted Currency is not SGD, change to SGD
			double sgdAmount = profitAmount;
			if (!t.getWantedCCY().equals("SGD")) {
				List<Rate> usdlist1 = CommonUtil.RetrieveWantRate(t.getWantedCCY(), "SGD");
				sgdAmount = Double
						.valueOf(CommonUtil.RetriveStdRate(usdlist1, CommonUtil.ConvertStringToDate(t.getTime())))
						* t.getAmount();
			}
			BigDecimal rd = new BigDecimal(sgdAmount).setScale(2, RoundingMode.DOWN);
			profitSGD = rd.doubleValue();
			System.out.println("Profit SGD : " + profitSGD);
		}

	}
}
