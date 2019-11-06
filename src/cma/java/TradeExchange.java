package cma.java;

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
		DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date conv = new Date();
		try {
			conv = sdf.parse(paramString);
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conv;
		
	}
	
	public static List<Rate> RetrieveWantRate(String baseCurr, String wantCurr){
		List<Rate> list = f.readRatefromCSV(absolutePath+"\\src\\rate.csv");
		List<Rate> wantedList = new ArrayList<Rate>();
		
		for(int i=0; i<list.size();i++) {
			
			Rate r = new Rate();
			r = list.get(i);
			
			if(baseCurr.equals(r.getBaseCCY()) && wantCurr.equals(r.getWantedCCY())){
				wantedList.add(r);
			}
			
		}
		
		return wantedList;
	}
	
	public static String RetriveStdRate(List<Rate> list, Date paramDate) {
	
		//List<Rate> list = f.readRatefromCSV(absolutePath+"\\src\\rate.csv");
		
		for (int i=0; i<list.size(); i++) {
			if(ConvertStringToDate(list.get(i).getTime()).after(paramDate)) {
				return String.valueOf(list.get(i).getStdRate());
			}
		}
		
		return String.valueOf(list.get(0).getStdRate());
	}
	
	public static double CheckIndRate(double paramAmout) {
		
		double dps;
		
		if (paramAmout <= 8000) {
			dps = 40 / 100;
		} else if (paramAmout> 8000 && paramAmout <= 20000) {
			dps = 35 / 100;
		} else if (paramAmout> 20000 && paramAmout <= 35000) {
			dps = 30 / 100;
		} else {
			dps = 25 / 100;
		}
		
		return dps;
	}
	
	public static double CheckCorpRate(double paramAmout) {
		
		double dps;
		
		if (paramAmout <= 1000000) {
			dps = 15 / 100;
		} else if (paramAmout> 1000000 && paramAmout <= 3000000) {
			dps = 10 / 100;
		} else if (paramAmout> 3000000) {
			dps = 5 / 100;
		} else {
			dps = 0; // error
		}
		
		return dps;
	}
	
	
	
	public static void main(String[] args) {
		
		List<Tran> trans = f.readTranfromCSV(absolutePath+"\\src\\transaction.csv");
		
		double finalRate = 0.0;
		double profitAmount = 0.0;
		double profitSGD = 0.0;

		for(int i=0; i<trans.size(); i++) {
			
			Tran t = trans.get(i);
			List<Rate> list = RetrieveWantRate(t.getBaseCCY(), t.getWantedCCY());
			t.setStdRate(Double.valueOf(RetriveStdRate(list,ConvertStringToDate(t.getTime()))));
			t.setFinalRate(t.getAmount() * t.getStdRate());
			
			double usdAmount = t.getFinalRate();
		
			System.out.println(usdAmount);
			while (t.getClientType().equals("Individual")) {

				Date tranTime = ConvertStringToDate(t.getTime());
				//RetriveStdRate(tranTime);

				finalRate = (t.getStdRate() * (1 - (usdAmount / 100)));
			} // while end

			while (t.getClientType().equals("Corporate")) {
			
				finalRate = (t.getStdRate() * (1 - (usdAmount / 100)));
			} // while end

			profitAmount = (t.getStdRate() - finalRate) * t.getAmount();
			profitSGD = (t.getStdRate() * profitAmount);

		}
	}
}
