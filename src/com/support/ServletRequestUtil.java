package com.support;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.ydbaobao.model.SessionCustomer;

public class ServletRequestUtil {
	private ServletRequestUtil() {
	}
	
	public static boolean existedCustomerIdFromSession(HttpSession session) throws IOException {
		if (session.getAttribute("sessionCustomer") == null) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public static String getCustomerIdFromSession(HttpSession session) throws IOException {
		if(!existedCustomerIdFromSession(session)){
			return null;
		}
		return ((SessionCustomer)session.getAttribute("sessionCustomer")).getSessionId();
	}

	public static boolean hasAuthorizationFromCustomer(HttpSession session) {
		SessionCustomer sessionCustomer = (SessionCustomer)session.getAttribute("sessionCustomer");
		if ((sessionCustomer.getSessionGrade()).equals("0")) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
