package jsonParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;



public class BuildJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
