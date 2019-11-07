package cma.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeExchange {
	static CSVFileReader reader = new CSVFileReader();
	static CSVFileWriter writer = new CSVFileWriter();

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

	public static List<Rate> RetrieveWantRate(List<Rate> list, String baseCurr, String wantCurr) {

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

		if (paramAmout <= 8000) {
			dps = 0.4;
		} else if (paramAmout > 8000 && paramAmout <= 20000) {
			dps = 0.35;
		} else if (paramAmout > 20000 && paramAmout <= 35000) {
			dps = 0.3;
		} else {
			dps = 0.25;

		}
		return dps;
	}

	public static double CheckCorpRate(double paramAmout) {
		double dps;
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
		return dps;
	}

	public static File calculate(String rateFile, String tranFile) {

		List<Tran> trans = reader.readTranfromCSV(tranFile);
		List<Rate> rates = reader.readRatefromCSV(rateFile);
		List<Tran> updatedTrans = new ArrayList<Tran>();
		for (int i = 0; i < trans.size(); i++) {
			double finalRate = 0.0;
			double profitAmount = 0.0;
			double profitSGD = 0.0;
			Tran t = new Tran();
			t = trans.get(i);
			List<Rate> list = RetrieveWantRate(rates, t.getBaseCCY(), t.getWantedCCY());
			t.setStdRate(Double.valueOf(RetriveStdRate(list, ConvertStringToDate(t.getTime()))));

			// 4 decimals for standard rate
			BigDecimal bd = new BigDecimal(t.getStdRate()).setScale(4, RoundingMode.HALF_EVEN);
			profitAmount = bd.doubleValue();

			double usdAmount = t.getAmount();
			List<Rate> usdlist = RetrieveWantRate(rates, t.getBaseCCY(), "USD");
			usdAmount = Double.valueOf(RetriveStdRate(usdlist, ConvertStringToDate(t.getTime()))) * t.getAmount();

			if (t.getClientType().equals("Individual")) {
				double dps = CheckIndRate(usdAmount);
				finalRate = (t.getStdRate() * (1 - (dps / 100)));
				BigDecimal fd = new BigDecimal(finalRate).setScale(4, RoundingMode.HALF_EVEN);
				finalRate = fd.doubleValue();
			}
			if (t.getClientType().equals("Corporate")) {
				double dps = CheckCorpRate(usdAmount);
				finalRate = (t.getStdRate() * (1 - (dps / 100)));
				BigDecimal fd = new BigDecimal(finalRate).setScale(4, RoundingMode.HALF_EVEN);
				finalRate = fd.doubleValue();
			}
			t.setFinalRate(finalRate);

			profitAmount = (t.getStdRate() - finalRate) * t.getAmount();
			BigDecimal pd = new BigDecimal(profitAmount).setScale(2, RoundingMode.HALF_EVEN);
			profitAmount = pd.doubleValue();

			t.setProfitInWCCY(profitAmount);

			// IF wanted Currency is not SGD, change to SGD
			double sgdAmount = profitAmount;
			if (!t.getWantedCCY().equals("SGD")) {
				List<Rate> sgdList = RetrieveWantRate(rates, t.getWantedCCY(), "SGD");
				sgdAmount = Double.valueOf(RetriveStdRate(sgdList, ConvertStringToDate(t.getTime()))) * profitAmount;
			}
			BigDecimal rd = new BigDecimal(sgdAmount).setScale(2, RoundingMode.HALF_EVEN);
			profitSGD = rd.doubleValue();
			t.setProfitInSGD(profitSGD);
			updatedTrans.add(t);
		}
		return writer.FileGenerated(updatedTrans);
	}

	
}