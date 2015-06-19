package com.support;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class CommonUtil {
	final public static int PRODUCTS_PER_PAGE = 16;
	
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
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	public static List<String> extractValidationMessages(BindingResult result) {
		List<ObjectError> list = result.getAllErrors();
		List<String> messageList = new ArrayList<>();
		System.out.println(list);
		for (ObjectError e : list) {
			messageList.add(e.getDefaultMessage());
		}
		return messageList;
	}
	
	/**
	 * 
	 * @param 전체 상품 갯수
	 * @return 총 페이지 수
	 */
	public static Object countTotalPage(int count) {
		return count / CommonUtil.PRODUCTS_PER_PAGE + 1;
	}
}
