/**
 * 
 */
package jsonParser;

import java.io.StringReader;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 * @author huazhang
 *
 */
public class ReadJson {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
		
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
