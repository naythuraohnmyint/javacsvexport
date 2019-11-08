package cma.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import cma.java.TradeExchange;
import cma.java.Tran;

public class TradeExchangeTestCase {
	
	/*
	 * @Test void testConvertStringToDate() { String
	 * paramString="Thu, Nov 11 2019 09:37:50"; TradeExchange t = new
	 * TradeExchange(); Date expected = t.ConvertStringToDate(paramString);
	 * 
	 * SimpleDateFormat actual=new SimpleDateFormat("Thu, Nov 11 2019 09:37:50");
	 * Date actual = SimpleD assertTrue(expected,);
	 * 
	 * 
	 * }
	 */
	/*
	 * @Test void testRetrieveWanteddRate() { Rate r = new Rate(); TradeExchange t =
	 * new TradeExchange(); List<Rate> expectedlist = Arrays.asList( new
	 * Rate("CNY","SGD",0.2021, "8:32"), new Rate("USD","SGD",0.2014, "9:00"));
	 * List<Rate> actuallist = Arrays.asList( new Rate("CNY","SGD",0.2021, "8:32"),
	 * new Rate("USD","SGD",0.2014, "9:00")); String baseCurr = "SGD"; String
	 * wantCurr = "USD"; List<Rate> expected = t.RetrieveWantRate(expectedlist,
	 * baseCurr, wantCurr); assertEquals(expectedlist, actuallist); }
	 */
	
	/*
	 * @Test void testRetrieveStandardRate() { double actual = 0.2021; TradeExchange
	 * t = new TradeExchange(); List<Rate> list = Arrays.asList( new
	 * Rate("CNY","SGD",0.2021, "8:32"), new Rate("USD","SGD",0.2014, "9:00")); Date
	 * paramDate = new Date(); paramDate.setTime(61202516585000L);
	 * String expected = t.RetriveStdRate(list,
	 * paramDate); assertEquals(expected,actual); }
	 */
	
	
	@Test
	void testIndividualRatee() {
		double paraAmount = 3500;
		double expected = 0.40;
		double actual = 0.40;		
			
		actual = TradeExchange.CheckIndRate(paraAmount);
		assertEquals(expected,actual);		
	}
	
	
	@Test
	void testCorpRate() {
		double paraAmount = 3500000;
		double expected = 0.05;
		double actual = 0.05;		
		
		actual = TradeExchange.CheckCorpRate(paraAmount);
		assertEquals(expected,actual);		
	}
	
	@Test
	void testCalculateFunc() {
		
		
		List<Tran> actualResultTrans = TradeExchange.calculate("rate.csv", "transaction.csv");
		List<Tran> expectedResultTrans = new ArrayList<Tran>();
		
		Tran t1 = new Tran();
		t1.setBaseCCY("CNY");
		t1.setWantedCCY("SGD");
		t1.setAmount(40000);
		t1.setStdRate(0.2012);
		t1.setFinalRate(0.2004);
		t1.setProfitInWCCY(32);
		t1.setProfitInSGD(32);
		
		/** TO DO: Add more here **/
		
		expectedResultTrans.add(t1);
		
		for(int i=0; i<expectedResultTrans.size();i++) {
			assertEquals(expectedResultTrans.get(i).getFinalRate(),actualResultTrans.get(i).getFinalRate());	
		}
		
	}

}
