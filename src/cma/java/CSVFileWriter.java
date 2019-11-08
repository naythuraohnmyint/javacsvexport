package cma.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CSVFileWriter {
	public static File FileGenerated(List<Tran> list) {
		File output = new File("expected-output.csv");
		try (PrintWriter writer = new PrintWriter(output)) {

			StringBuilder sb = new StringBuilder();
			sb.append("BaseCurrency,");
			sb.append("WantedCurrency,");
			sb.append("AmountInBaseCurrency,");
			sb.append("StandardRate,");
			sb.append("FinalRate,");
			sb.append("ProfitInWantedCurrency,");
			sb.append("ProfitInSGD,");
			sb.append('\n');
			for(int i = 0;i<list.size();i++){
				Tran t = new Tran();
				t=list.get(i);
				sb.append(t.getBaseCCY()+",");
				sb.append(t.getWantedCCY()+",");
				sb.append(t.getAmount()+",");
				sb.append(t.getStdRate()+",");
				sb.append(t.getFinalRate()+",");
				sb.append(t.getProfitInWCCY()+",");
				sb.append(t.getProfitInSGD()+",");
				sb.append('\n');
			}

			writer.write(sb.toString());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return output;
	}
}
