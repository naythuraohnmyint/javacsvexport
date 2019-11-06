package cma.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileReader {

	public static void main(String[] args) {

	}
	
	private static List<Tran> readTranfromCSV(String fileName) {
        List<Tran> trans = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                Tran tran = createTran(attributes);

                // adding book into ArrayList
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
	
	private static Tran createTran(String[] metadata) {
		
		double stdRate = Double.valueOf(metadata[0]);
		String baseCCY = metadata[1];
		String wantedCCY = metadata[2] ;
		int amount = Integer.valueOf(metadata[3]);
		String clientType = metadata[4];
		String time = metadata[5];
		double finalRate = Double.valueOf(metadata[6]);
		double profitInWCCY ;
		double profitInSGD;
		
        String name = metadata[0];
        int price = Integer.parseInt(metadata[1]);
        String author = metadata[2];

        // create and return book of this metadata
        return new Tran();
    }
}
