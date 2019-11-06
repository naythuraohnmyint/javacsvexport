package cma.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeExchange {

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
	
	public static boolean CompareDate(Date paramDate) {
		List<Rate> list = new ArrayList<Rate>();
		
		for (int i=0; i<list.size(); i++) {
			if(ConvertStringToDate(list.get(i).getTime()).after(paramDate)) {
				
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		double finalRate = 0.0;
		double profitAmount = 0.0;
		double profitSGD = 0.0;

		Tran t = new Tran();
		Rate rate = new Rate();
		

		t.setFinalRate(t.getAmount() * t.getStdRate());
		double usdAmount = t.getFinalRate();
		while (t.getClientType() == "I") {
			
			String rateTime = rate.getTime();
			String tranTime = t.getTime();

			
			
			//if(CompareDate())

			//System.out.println("Time: " + sdf.format(ra));
			
			//if(t.getTime() > rate.getTime())
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
