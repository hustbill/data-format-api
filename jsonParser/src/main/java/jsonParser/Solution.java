package jsonParser;

import java.io.*;
import java.util.*;
import java.net.*;

public class Solution {
	private static int infoCount = 0;
	private static int warnCount = 0;
	final static HashSet<String> snSet = new HashSet<String>();
	final static HashSet<String> siSet = new HashSet<String>();
	final static ArrayList<Integer> ssList = new ArrayList<Integer>();
	final static String[] tags = { "INFO: ", "WARN: " };
	final static String tail = "org.apache.jsp.index_jsp _jspService";
	

	public static String getData(String source) throws Exception {
		StringBuffer responseOutput = new StringBuffer();
		if (source.toLowerCase().startsWith("http")) {
			URL page = new URL(source);
			StringBuffer text = new StringBuffer();
			HttpURLConnection conn = (HttpURLConnection) page.openConnection();
			conn.connect();
			InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
			BufferedReader buff = new BufferedReader(in);

			String line;
			do {
				line = buff.readLine();
				text.append(line + "\n");
			} while (line != null);
			responseOutput.append(text.toString());
		} else {
			BufferedReader reader = new BufferedReader(new FileReader(source));
			String sCurrentLine;
			while ((sCurrentLine = reader.readLine()) != null) {
				responseOutput.append(sCurrentLine);
			}
			reader.close();
		}

		return responseOutput.toString();
	}

	/*
	 * Parse String as blow,
	 *  {"sq": 0, "vs": 3, "pf": 11, "sn": "1965f45398abbf9e995fe9eb18282510",
	 *   "ht": [{"cn": 1, "ap": 0, "ss": -71, "s2": 5041, "s3": -357911, 
	 *  		"si": "x0df11f201d334525", "sh": -71, "sm": "444C0C", 
	 *  		"sl": -71, "ot": 1435100343, "ct": 1435100343
	 *  		}]
	 *  }
	 */
	public static void parseLog(String data) {
		String[] strArr = data.split(tail);
		StringBuilder sb = new StringBuilder();

		System.out.println(sb.toString());
		for (String str : strArr) {
			String temp = "";
			if (str.indexOf(tags[0]) != -1) {
				infoCount++;
			}

			if (str.indexOf(tags[1]) != -1) {
				warnCount++;
			}
			
			if (str.indexOf("sn") != -1) {
				int start = str.indexOf("sn") + 5;
				int end = str.indexOf("ht") - 3;
				String snStr = str.substring(start, end);
				snSet.add(snStr);
			}
			
			if (str.indexOf("si") != -1) {
				int start = str.indexOf("si") + 5;
				int end = str.indexOf("sh") - 3;
				String siStr = str.substring(start, end);
				siSet.add(siStr);
			}
			
			if (str.indexOf("ss") != -1) {
				int start = str.indexOf("ss") + 5;
				int end = str.indexOf("s2") - 3;
				String ssStr = str.substring(start, end);
				
				int intr = Integer.parseInt(ssStr);
				if (intr < 0) {
					ssList.add(intr);
				}
			}
			
			sb.append(temp);
		}

		System.out.printf("\n %s %d, \t %s %d\n", tags[0], infoCount, tags[1], warnCount);
		System.out.printf("\nsn unique values count: %d \n", snSet.size());
		System.out.printf("\nsi unique values count: %d \n", siSet.size());

		Collections.sort(ssList);;
		System.out.printf("\nThe max value of ss is: %d\n",ssList.get(ssList.size() - 1));
		
	}

	public static void main(String[] args) throws Exception {
		String url = "http://interview.euclidanalytics.com/data";
		parseLog(getData(url));
	}
}
/*
 * Problem:
We've uploaded a sample of our raw data here at http://interview.euclidanalytics.com/data 

The data represents observations from a random set of routers recording wireless device signals. Lines will either be a date timestamp or else a line of data that begins with either INFO or WARN and is followed by a json data package that looks something like this: 

{"pf":11,"sn":"c9b238def5182c57c90c62816ac827a9","vs":3,"sq":0,"ht":[{"ss":-71,"ct":1435100343,"s3":-357911,"ot":1435100343,"si":"x0df11f201d334525",
"s2":5041,"ap":0,"cn":1,"sm":"444C0C","sl":-71,"sh":-71}]} 

The important fields for this exercise are the "sn" (router mac address),"si" (hashed wireless device mac address) and "ss" (signal strength) fields. Using the programming language of your choice, parse this data and answer the following questions: 

Questions:

How many INFO log lines are there?
How many WARN log lines are there?
How many unique sn values are there?
How many unique si values are there?
What is max value for ss? (note, max ss is a value that is closest to 0 without being positive)
 */
