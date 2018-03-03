package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {
		
	public static String convert(Date date) {
		SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = sdfr.format(date);
		return dateString;
	}
	
}
