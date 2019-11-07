package sg.com.javatest.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * CsvFileWriter
 *
 * @author Chan Mya Aye
 * @version 1.0
 * @since 2019-11-07
 *
 */
public class CsvFileWriter {
	public static void FileGenerated() {

		try (PrintWriter writer = new PrintWriter(new File("expected-output.csv"))) {

			StringBuilder sb = new StringBuilder();
			sb.append("BaseCurrency,");
			sb.append("WantedCurrency,");
			sb.append("AmountInBaseCurrency,");
			sb.append("StandardRate,");
			sb.append("FinalRate,");
			sb.append("ProfitInWantedCurrency,");
			sb.append("ProfitInSGD,");
			sb.append('\n');

			sb.append("CYN,");
			sb.append("SGD,");
			sb.append('\n');
			sb.append("AUD,");
			sb.append("SGD,");
			sb.append('\n');
			sb.append("SGD,");
			sb.append("AUD,");
			sb.append('\n');
			sb.append("EUR,");
			sb.append("USD,");
			sb.append('\n');
			sb.append("SGD,");
			sb.append("CNY");

			writer.write(sb.toString());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
