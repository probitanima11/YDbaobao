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
}
