package cma.java;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		File f = TradeExchange.calculate("rate.csv", "transaction.csv");
		CSVFileReader.readOutputFile(f);		
	}
}
