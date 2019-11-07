package sg.com.javatest.util;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.com.javatest.application.Rate;

/**
 * Common Util
 *
 * @author Chan Mya Aye
 * @version 1.0
 * @since 2019-11-07
 *
 */
public class CommonUtil {

	public static String absolutePath = (Paths.get("").toAbsolutePath().toString());
	public static CsvFileReader f = new CsvFileReader();

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
		List<Rate> list = CsvFileReader.readRatefromCSV(absolutePath + "\\src\\rate.csv");
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
		} else if (paramAmout > 1000000 && paramAmout <= 3000000) {
			dps = 0.10;
		} else if (paramAmout > 3000000) {
			dps = 0.05;
		} else {
			dps = 0;
		}
		return dps;
	}
}
