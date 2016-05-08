/**
 * 
 */
package jsonParser;

import java.io.*;
import java.util.*;
import java.net.*;
import javax.json.Json;
import javax.json.stream.JsonParser;

/**
 * @author huazhang
 *
 */
public class JsonExample {

	/**
	 * 
	 */
	public JsonExample() {
		// TODO Auto-generated constructor stub
	}

	private static int infoCount = 0;
	private static int warnCount = 0;

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
	 * Create Json from String, ex [{ }, {}, {}]
	 */
	public static String format2Json(String data) {
		// http://stackoverflow.com/questions/33432671/how-to-read-the-json-data-from-log-file-i-will-some-log-file-please-give-me-the
		String[] tags = { "INFO: ", "WARN: " };
		String timestamp = "Jun 23, 2015 11:00:00 PM ";
		String tail = "org.apache.jsp.index_jsp _jspService";

		String[] strArr = data.split(tail);
		StringBuilder sb = new StringBuilder();

		System.out.println(sb.toString());
		for (String str : strArr) {
			String temp = "";
			if (str.indexOf(tags[0]) != -1) {
				infoCount++;
				temp = str.replace(tags[0], ",");
				temp = temp.substring(0, temp.length() - timestamp.length());
			}

			if (str.indexOf(tags[1]) != -1) {
				warnCount++;
				temp = str.replace(tags[1], ",");
				temp = temp.substring(0, temp.length() - timestamp.length());
			}
			sb.append(temp);
		}

		System.out.printf("\n %s %d, \t %s %d\n", tags[0], infoCount, tags[1], warnCount);

		String result = sb.toString();
		result = "[" + result.substring(2, result.length()) + "]";
		return result;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String url = "http://interview.euclidanalytics.com/data";
		HashSet<String> snSet = new HashSet<String>();

		String jsonData = format2Json(getData(url));

		JsonParser parser = Json.createParser(new StringReader(jsonData));
		while (parser.hasNext()) {

			JsonParser.Event event = parser.next();
			switch (event) {
			case START_ARRAY:
			case END_ARRAY:
			case START_OBJECT:
			case END_OBJECT:
			case VALUE_FALSE:
			case VALUE_NULL:
			case VALUE_TRUE:
				 System.out.println(event.toString());
				break;
			case KEY_NAME:
				 System.out.print(event.toString() + " " + parser.getString()
				 + " - ");

				break;
			case VALUE_STRING:
			case VALUE_NUMBER:
				 System.out.println(event.toString() + " " +
				 parser.getString());
				break;
			}
		}

	}

}
