package com.support;

import java.util.regex.Pattern;

public class CommonUtil {
	public static boolean isNumber(String str){
        boolean result = false; 
        try{
            Integer.parseInt(str);
            result = true ;
        }catch(Exception e){}
        return result ;
    }
	
	/**
	 * <pre>
	 * matched
	 *
	 * <pre>
	 * @param regex
	 * @param inputTxt
	 * @return
	 */
	public static boolean matched(String regex, String inputTxt) {
		return Pattern.matches(regex, inputTxt);
	}
}
