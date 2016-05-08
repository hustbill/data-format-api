/**
 * 
 */
package jsonParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Java program to show how to format date in Java using SimpleDateFormat
 * Examples. Java allows to include date, time and timezone information while
 * formatting dates in Java.
 * 
 * @author huazhang
 *
 */
public class DateFormatExample {

	/**
	 * 
	 */
	public DateFormatExample() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String args[]) throws ParseException {

		String input = "1st Oct 2016";
		String x = formatDate(input);

		System.out.println(x);
	}

	public static String formatDate(String input) throws ParseException {
		String result = "";
		String[] strArr = input.split(" ");

		for (int i = 0; i < strArr.length; i++) {
			String str = strArr[i];
			System.out.println(str);
			if (str.indexOf("nd") != -1) {
				str = str.replace("nd", "");
				str = "0" + str;

			}

			if (str.indexOf("st") != -1) {
				str = str.replace("st", "");
				str = "0" + str;
			}

			str = str.replace("th", "");

			if (i < strArr.length - 1) {
				str += "-";
			}

			result += str;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

		Date d = formatter.parse(result);

		result = changeFormat(d);
		return result;

	}

	public static String changeFormat(Date startDate) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		return df.format(startDate);
	}
	
	public static void fomartDateExample() {
	    
        // This is how to get today's date in Java
        Date today = new Date();
      
        //If you print Date, you will get un formatted output
        System.out.println("Today is : " + today);
      
        //formatting date in Java using SimpleDateFormat
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        String date = DATE_FORMAT.format(today);
        System.out.println("Today in dd-MM-yyyy format : " + date);
      
        //Another Example of formatting Date in Java using SimpleDateFormat
        DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");
        date = DATE_FORMAT.format(today);
        System.out.println("Today in dd/MM/yy pattern : " + date);
      
        //formatting Date with time information
        DATE_FORMAT = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
        date = DATE_FORMAT.format(today);
        System.out.println("Today in dd-MM-yy:HH:mm:SS : " + date);
      
        //SimpleDateFormat example - Date with timezone information
        DATE_FORMAT = new SimpleDateFormat("dd-MM-yy:HH:mm:SS Z");
        date = DATE_FORMAT.format(today);
        System.out.println("Today in dd-MM-yy:HH:mm:SSZ : " + date);
	}

}
/**
 * http://java67.blogspot.com Read more:
 * http://java67.blogspot.com/2013/01/how-to-format-date-in-java-
 * simpledateformat-example.html#ixzz47xDfAV1K
 * 
 */
