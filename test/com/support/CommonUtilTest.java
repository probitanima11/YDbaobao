package com.support;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommonUtilTest {

	@Test
	public void isNumberTest() {
		assertFalse(CommonUtil.isNumber("hello"));
		assertTrue(CommonUtil.isNumber("435"));
		assertTrue(CommonUtil.isNumber("435.4"));
	}

}
