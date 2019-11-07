package cma.java;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		TradeExchange te = new TradeExchange();
		File f = te.calculate("rate.csv", "transaction.csv");
		CSVFileReader.readOutputFile(f);
		
	}
}
