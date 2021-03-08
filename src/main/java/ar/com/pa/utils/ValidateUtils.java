package ar.com.pa.utils;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ar.com.pa.enums.financialsummary.FinancialSummary;

public class ValidateUtils {

    public ValidateUtils() {};
 

	public static boolean isNumOrEmpty(String strNum) {
	    if (strNum.equalsIgnoreCase("")) {
	        return true;
	    }
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isSummaryModelValue(String s) {
	return isSummaryObject(s) || isNumOrEmpty(s);
	}
	
	
	public static boolean isSummaryObject(String obj) {
		boolean response = Arrays.asList(FinancialSummary.values()).stream()
				.anyMatch(item -> item.getTitle().equalsIgnoreCase(obj));
		return response;
	}
}