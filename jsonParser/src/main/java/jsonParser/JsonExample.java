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

class WebReader {
	public StringBuffer getData(String address) throws Exception {
		StringBuffer sb = new StringBuffer();

		URL page = new URL(address);
		StringBuffer text = new StringBuffer();
		HttpURLConnection conn = (HttpURLConnection) page.openConnection();
		conn.connect();
		InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
		InputStream inputStream = (InputStream) conn.getContent();
		JsonReader jsonReader = Json.createReader(inputStream);

		// // get JsonObject from JsonReader
		// JsonObject jsonObject = jsonReader.readObject();
		//
		// // we can close IO resoure and JsonReader now
		// jsonReader.close();
		// inputStream.close();

		// System.out.println(jsonObject.getInt("ss"));

		BufferedReader buff = new BufferedReader(in);

		String line;
		do {
			line = buff.readLine();
			text.append(line + "\n");
		} while (line != null);
		sb.append(text.toString());
		return sb;
	}
}

public class JsonExample {

	public static String readFile(String Path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

	public static void main(String[] args) throws Exception {
		// WebReader app = new WebReader();
		// ref:
		// http://docs.oracle.com/middleware/1213/wls/WLPRG/java-api-for-json-proc.htm#WLPRG1064
		// ref 2:
		// http://www.programcreek.com/java-api-examples/index.php?api=javax.json.JsonReader
		// StringBuffer sb =
		// app.getData("http://interview.euclidanalytics.com/data");
		
		/*
		 * http://stackoverflow.com/questions/25362942/how-to-parsing-csv-or-json-file-with-apache-spark
		 * read data from url with jframe: http://www.java2s.com/Tutorial/Java/0320__Network/ReaddatafromaURL.htm
		 * http://www.programmingforliving.com/2013/07/java-api-for-json-jee-7-part2.html
		 * http://docs.oracle.com/javaee/7/api/javax/json/stream/JsonParser.html
		 * https://databricks.gitbooks.io/databricks-spark-reference-applications/content/logs_analyzer/chapter1/spark.html
		 */
		String fullData = "src/main/resources/dataline.txt";
		String partData = "src/main/resources/part.txt";
		String data = "src/main/resources/data";
		
		String jsonData = readFile(data);

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
				System.out.print(event.toString() + " " + parser.getString() + " - ");
				break;
			case VALUE_STRING:
			case VALUE_NUMBER:
				System.out.println(event.toString() + " " + parser.getString());
				break;
			default:
				System.out.println("arrived here");
				break;
			}
		}

	}

	public static void readJson(String[] args) throws Exception {
		WebReader app = new WebReader();

		StringBuffer sb = app.getData("http://interview.euclidanalytics.com/data");
		String personJSONData = "  {" + "       \"name\": \"John\", " + "       \"age\" : 35, "
				+ "       \"isMarried\" : true, " + "       \"email\": null, " + "       \"address\": { "
				+ "           \"street\": \"#234, Pembroke Road\", " + "           \"city\": \"Dublin\", "
				+ "           \"zipCode\": \"D4\" " + "       }, "
				+ "       \"phoneNumbers\": [\"89-923-2342\", \"89-213-2364\"] " + "   }";
		// JsonParser parser = Json.createParser(new
		// StringReader(personJSONData));
		JsonParser parser = Json.createParser(new StringReader(sb.toString().substring(100)));
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

	public static void test(String[] args) {

		List<User> users = getUsers();

		// build JSON
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		for (User user : users) {
			jsonArrayBuilder.add(Json.createObjectBuilder().add("id", user.getId()).add("name", user.getName())
					.add("join", user.getJoin().getTime()));
		}

		JsonArray usersJson = jsonArrayBuilder.build();

		System.out.println(usersJson.toString());

	}

	// dummy users
	public static List<User> getUsers() {
		// http://dreamand.me/java/jee7-json-example/
		List<User> users = new ArrayList<User>(10);
		User user = new User();
		user.setId(1);
		user.setName("Hero");
		user.setJoin(new Date());
		users.add(user);

		user = new User();
		user.setId(2);
		user.setName("Citizen");
		user.setJoin(new Date());
		users.add(user);

		return users;
	}

	// POJO
	public static class User implements Serializable {
		private long id;
		private String name;
		private Date join;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getJoin() {
			return join;
		}

		public void setJoin(Date join) {
			this.join = join;
		}
	}
}
// Output of the program:
//
//
// [{"id":1,"name":"Hero","join":1366808892498},{"id":2,"name":"Citizen","join":1366808892498}]
//
//
// Filed under java
