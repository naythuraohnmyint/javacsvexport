package cma.java;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeExchange {
	static String absolutePath = (Paths.get("").toAbsolutePath().toString());
	static FileReader f = new FileReader();

	public static Date ConvertStringToDate(String paramString) {
		DateFormat sdf = new SimpleDateFormat("hh:mm");
		Date conv = new Date();
		try {
			conv = sdf.parse(paramString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conv;
	}

	public static List<Rate> RetrieveWantRate(String baseCurr, String wantCurr) {
		List<Rate> list = f.readRatefromCSV(absolutePath + "\\src\\rate.csv");
		List<Rate> wantedList = new ArrayList<Rate>();
		for (int i = 0; i < list.size(); i++) {
			Rate r = new Rate();
			r = list.get(i);
			if (baseCurr.equals(r.getBaseCCY()) && wantCurr.equals(r.getWantedCCY())) {
				wantedList.add(r);
			}
		}
		return wantedList;
	}

	public static String RetriveStdRate(List<Rate> list, Date paramDate) {
		// List<Rate> list = f.readRatefromCSV(absolutePath+"\\src\\rate.csv");
		for (int i = 0; i < list.size(); i++) {
			if (ConvertStringToDate(list.get(i).getTime()).after(paramDate)) {
				return String.valueOf(list.get(i).getStdRate());
			}
		}
		return String.valueOf(list.get(0).getStdRate());
	}

	public static double CheckIndRate(double paramAmout) {
		double dps = 0;

		System.out.println("paramamout......:" + paramAmout);
		if (paramAmout <= 8000) {
			dps = 0.4;
			System.out.println("comeindbps......:" + dps);
		} else if (paramAmout > 8000 && paramAmout <= 20000) {
			dps = 0.35;
		} else if (paramAmout > 20000 && paramAmout <= 35000) {
			dps = 0.3;
		} else {
			dps = 0.25;

		}

		System.out.println("ma nout ya wo:......." + dps);
		return dps;
	}

	public static double CheckCorpRate(double paramAmout) {
		double dps;

		System.out.println("paramamout coporate......:" + paramAmout);
		if (paramAmout <= 1000000) {
			dps = 0.15;
			System.out.println("comeindbps...cor...:" + dps);
		} else if (paramAmout > 1000000 && paramAmout <= 3000000) {
			dps = 0.10;
		} else if (paramAmout > 3000000) {
			dps = 0.05;
		} else {
			dps = 0; // error
		}

		System.out.println("ma nout ya wo:......." + dps);
		return dps;
	}

	public static void main(String[] args) {
		List<Tran> trans = f.readTranfromCSV(absolutePath + "\\src\\transaction.csv");
		double finalRate = 0.0;
		double profitAmount = 0.0;
		double profitSGD = 0.0;
		for (int i = 0; i < trans.size(); i++) {
			Tran t = new Tran();
			t = trans.get(i);
			System.out.println("-----------------------------------------");
			System.out.println(t.getClientType());
			System.out.println(t.getBaseCCY());
			System.out.println(t.getWantedCCY());
			List<Rate> list = RetrieveWantRate(t.getBaseCCY(), t.getWantedCCY());
			t.setStdRate(Double.valueOf(RetriveStdRate(list, ConvertStringToDate(t.getTime()))));

			// 4 decimals for standard rate
			BigDecimal bd = new BigDecimal(t.getStdRate()).setScale(4, RoundingMode.DOWN);
			profitAmount = bd.doubleValue();
			System.out.println("standard rate ...." + t.getStdRate());

			double usdAmount = t.getAmount();
			if (!t.getWantedCCY().equals("USD")) {
				List<Rate> usdlist = RetrieveWantRate(t.getBaseCCY(), "USD");
				usdAmount = Double.valueOf(RetriveStdRate(usdlist, ConvertStringToDate(t.getTime()))) * t.getAmount();
			}
			if (t.getClientType().equals("Individual")) {
				double dps = CheckIndRate(usdAmount);
				System.out.println("dps...:" + dps);
				finalRate = (t.getStdRate() * (1 - (dps / 100)));
				BigDecimal fd = new BigDecimal(finalRate).setScale(4, RoundingMode.CEILING);
				finalRate = fd.doubleValue();
			}
			if (t.getClientType().equals("Corporate")) {
				double dps = CheckCorpRate(usdAmount);
				System.out.println("dps...:" + dps);
				finalRate = (t.getStdRate() * (1 - (dps / 100)));

				BigDecimal fd = new BigDecimal(finalRate).setScale(4, RoundingMode.CEILING);
				finalRate = fd.doubleValue();
			} // while end
			t.setFinalRate(finalRate);
			System.out.println("final rate......." + t.getFinalRate());

			profitAmount = (t.getStdRate() - finalRate) * t.getAmount();
			BigDecimal pd = new BigDecimal(profitAmount).setScale(2, RoundingMode.DOWN);
			profitAmount = pd.doubleValue();
			System.out.println("Profit In Wanted.......:" + profitAmount);

			// IF wanted Currency is not SGD, change to SGD
			double sgdAmount = profitAmount;
			if (!t.getWantedCCY().equals("SGD")) {
				List<Rate> usdlist1 = RetrieveWantRate(t.getWantedCCY(), "SGD");
				sgdAmount = Double.valueOf(RetriveStdRate(usdlist1, ConvertStringToDate(t.getTime()))) * t.getAmount();
			}
			BigDecimal rd = new BigDecimal(sgdAmount).setScale(2, RoundingMode.DOWN);
			profitSGD = rd.doubleValue();
			System.out.println("Profit SGD........:" + profitSGD);

		}
	}
}