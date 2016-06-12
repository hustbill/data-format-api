package date;

/*
Write a function that prints all possible combinations for a lock with the 
following properties: combinations are 4 digits of the form MMDD; no digit 
occurs more than once.

For a first implementation you can assume all months have 31 days.

Valid combinations
* 0123 - January 23
* 0931 - September 31 - OK even though Sept doesn't have 31 days

Invalid combinations
* 0121 - January 21 has a repeated digit (1)
* 0935 - No month has 35 days

*/

import java.util.*;
import java.text.DateFormatSymbols;

public class PrintAllCombinationDate {
	public static void main(String[] args) {

		List<String> list = printAllCombinations();

		for (String strDate : list) {
			System.out.println(strDate);
		}

	}

	public static List<String> printAllCombinations() {
		int mm = 0, dd = 0;
		List<String> list = new ArrayList<String>();

		// create all date numbers in for loop
		for (int i = 101; i < 1300; i++) {
			String s = String.valueOf(i);

			// add "0" in front of 301 => 0301 0301 is invalid
			if (i < 1000) {
				s = "0" + s;
			}
			if (!isDuplicate(s)) { // skip repeated digits
				mm = i / 100;
				dd = i % 100;

				// skip those invalid month xx
				String monthString = new DateFormatSymbols().getMonths()[mm - 1];
				StringBuilder sb = new StringBuilder();
				sb.append(monthString);

				// skip those invalid date xx which out of the range {01 - 31 }
				if (dd <= 31) {
					String strDate = sb.append(" " + dd).toString();
					list.add(strDate);
				}
			}
		}

		return list;

	}

	// skip repeated digits such as 01 21, 03 01
	public static boolean isDuplicate(String s) {
		char[] arr = s.toCharArray();

		HashMap<Character, Integer> map = new HashMap<>();
		for (int j = 0; j < arr.length; j++) {
			if (map.containsKey(arr[j])) {
				return true;
			} else {
				map.put(arr[j], 1);
			}
		}
		return false;
	}
}

/*
 * 
 * January 23 January 24 January 25 January 26 January 27 January 28 January 29
 * February 13 February 14 February 15 February 16 February 17 February 18
 * February 19 February 31 March 12 March 14 March 15 March 16 March 17 March 18
 * March 19 March 21 March 24 March 25 March 26 March 27 March 28 March 29 April
 * 12 April 13 April 15 April 16 April 17 April 18 April 19 April 21 April 23
 * April 25 April 26 April 27 April 28 April 29 April 31 May 12 May 13 May 14
 * May 16 May 17 May 18 May 19 May 21 May 23 May 24 May 26 May 27 May 28 May 29
 * May 31 June 12 June 13 June 14 June 15 June 17 June 18 June 19 June 21 June
 * 23 June 24 June 25 June 27 June 28 June 29 June 31 July 12 July 13 July 14
 * July 15 July 16 July 18 July 19 July 21 July 23 July 24 July 25 July 26 July
 * 28 July 29 July 31 August 12 August 13 August 14 August 15 August 16 August
 * 17 August 19 August 21 August 23 August 24 August 25 August 26 August 27
 * August 29 August 31 September 12 September 13 September 14 September 15
 * September 16 September 17 September 18 September 21 September 23 September 24
 * September 25 September 26 September 27 September 28 September 31 October 23
 * October 24 October 25 October 26 October 27 October 28 October 29 December 3
 * December 4 December 5 December 6 December 7 December 8 December 9 December 30
 */
