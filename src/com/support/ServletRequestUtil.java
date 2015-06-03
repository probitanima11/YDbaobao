package com.support;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.ydbaobao.model.SessionCustomer;

public class ServletRequestUtil {
	private ServletRequestUtil() {
	}
	
	public static boolean existedUserIdFromSession(HttpSession session) throws IOException {
		if (session.getAttribute("sessionUser") == null) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public static String getUserIdFromSession(HttpSession session) throws IOException {
		if(!existedUserIdFromSession(session)){
			return null;
		}
		return ((SessionCustomer)session.getAttribute("sessionCustomer")).getSessionId();
	}
}
