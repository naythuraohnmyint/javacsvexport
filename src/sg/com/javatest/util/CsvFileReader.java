package sg.com.javatest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import sg.com.javatest.application.Rate;
import sg.com.javatest.application.Transaction;

/**
 * CsvFileReader
 *
 * @author Chan Mya Aye
 * @version 1.0
 * @since 2019-11-07
 *
 */
public class CsvFileReader {

	public static List<Rate> readRatefromCSV(String fileName) {
		List<Rate> rates = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");

				Rate rate = createRate(attributes);

				// adding Transaction into ArrayList
				rates.add(rate);

				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return rates;
	}

	public static Rate createRate(String[] metadata) {

		String baseCCY = metadata[0];
		String wantedCCY = metadata[1];
		double rate = Double.valueOf(metadata[2]);
		String time = metadata[3];

		Rate r = new Rate();
		r.setBaseCCY(baseCCY);
		r.setWantedCCY(wantedCCY);
		r.setTime(time);
		r.setStdRate(rate);

		r.setTime(time);
		return r;
	}

	public static List<Transaction> readTranfromCSV(String fileName) {
		List<Transaction> trans = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");

				Transaction tran = createTran(attributes);

				// adding Transaction into ArrayList
				trans.add(tran);

				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return trans;
	}

	public static Transaction createTran(String[] metadata) {

		String baseCCY = metadata[0];
		String wantedCCY = metadata[1];
		int amount = Integer.valueOf(metadata[2]);
		String clientType = metadata[3];
		String time = metadata[4];

		Transaction t = new Transaction();
		t.setBaseCCY(baseCCY);
		t.setWantedCCY(wantedCCY);
		t.setAmount(amount);
		t.setClientType(clientType);
		t.setTime(time);
		return t;
	}
}
