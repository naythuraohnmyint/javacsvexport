package cma.junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThat;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import cma.java.Rate;
import cma.java.TradeExchange;

class TradeExchangeTestCase {
	
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
		TradeExchange t = new TradeExchange();		
		actual = t.CheckIndRate(paraAmount);
		assertEquals(expected,actual);		
	}
	
	
	@Test
	void testCorpRate() {
		double paraAmount = 3500000;
		double expected = 0.05;
		double actual = 0.05;		
		TradeExchange t = new TradeExchange();		
		actual = t.CheckCorpRate(paraAmount);
		assertEquals(expected,actual);		
	}

}
