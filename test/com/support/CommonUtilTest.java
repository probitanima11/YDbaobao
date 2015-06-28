package com.support;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class CommonUtilTest {

	@Test
	public void isNumberTest() {
		assertFalse(CommonUtil.isNumber("hello"));
		assertTrue(CommonUtil.isNumber("435"));
		assertTrue(CommonUtil.isNumber("435.4"));
	}
	
	@Test
	public void testName() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = new GregorianCalendar();
	    cal.add(Calendar.DATE, -7);
	    System.out.println(format.format(cal.getTime()));
	}

}
