package jsonParser;

//import
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.io.*;
import java.util.*;
import java.net.*;

import java.io.StringReader;

import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class JsonExample {
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

	public static void main(String[] args) throws Exception {

		String file = "src/main/resources/data";
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
				// System.out.println(event.toString());
				break;
			case KEY_NAME:
				// System.out.print(event.toString() + " " + parser.getString()
				// + " - ");

				break;
			case VALUE_STRING:
			case VALUE_NUMBER:
				// System.out.println(event.toString() + " " +
				// parser.getString());
				break;
			}
		}

	}

	public static void readJson(String[] args) throws Exception {
				String personJSONData = "  {" + "       \"name\": \"John\", " + "       \"age\" : 35, "
				+ "       \"isMarried\" : true, " + "       \"email\": null, " + "       \"address\": { "
				+ "           \"street\": \"#234, Pembroke Road\", " + "           \"city\": \"Dublin\", "
				+ "           \"zipCode\": \"D4\" " + "       }, "
				+ "       \"phoneNumbers\": [\"89-923-2342\", \"89-213-2364\"] " + "   }";
		JsonParser parser = Json.createParser(new StringReader(personJSONData));
		
		while (parser.hasNext()) {
			Event event = parser.next();
			switch (event) {
			case START_OBJECT:
				System.out.print("{/*" + event + "*/ ");
				break;
			case END_OBJECT:
				System.out.print("}/*" + event + "*/ ");
				break;
			case START_ARRAY:
				System.out.print("[/*" + event + "*/ ");
				break;
			case END_ARRAY:
				System.out.print("]/*" + event + "*/ ");
				break;
			case VALUE_NUMBER:
				System.out.print(parser.getInt() + "/*" + event + "*/, ");
				break;
			case VALUE_STRING:
				System.out.print(parser.getString() + "/*" + event + "*/, ");
				break;
			case VALUE_TRUE:
				System.out.print("true/*" + event + "*/, ");
				break;
			case VALUE_FALSE:
				System.out.print("false/*" + event + "*/, ");
				break;
			case VALUE_NULL:
				System.out.print("null/*" + event + "*/, ");
				break;
			case KEY_NAME:
				System.out.print(parser.getString() + "/*" + event + "*/: ");
				break;
			default:
				System.out.print("Unrecognized Event : ");
			}
		}
	}

}

/*
 * http://stackoverflow.com/questions/25362942/how-to-parsing-csv-or-
 * json-file-with-apache-spark read data from url with jframe:
 * http://www.java2s.com/Tutorial/Java/0320__Network/ReaddatafromaURL. htm
 * http://www.programmingforliving.com/2013/07/java-api-for-json-jee-7-
 * part2.html
 * http://docs.oracle.com/javaee/7/api/javax/json/stream/JsonParser.html
 * https://databricks.gitbooks.io/databricks-spark-reference-
 * applications/content/logs_analyzer/chapter1/spark.html
 */