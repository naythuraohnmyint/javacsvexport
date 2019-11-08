package cma.java;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Test {
	public static void main(String[] args) throws IOException {
		
		List<Tran> resultTrans = TradeExchange.calculate("rate.csv", "transaction.csv");
		File f = CSVFileWriter.FileGenerated(resultTrans);
		CSVFileReader.readOutputFile(f);		
	}
}
