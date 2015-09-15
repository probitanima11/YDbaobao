package com.ydbaobao.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.ydbaobao.domain.Navigator;

public class HomeControllerTest {
	@SuppressWarnings("unused")
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HomeControllerTest.class);
	
	@Test
	public void navigator_생성이_정상적으로_수행되는지() {
		Navigator nav = new Navigator(1, 15);
		assertEquals(nav.getPrevBlock(), 0);
		int[] lst = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		assertTrue(Arrays.equals(lst, nav.getRange()));
		assertEquals(nav.getSelected(), 1);
	}

}
