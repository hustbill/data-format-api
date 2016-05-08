package string;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class ValidateParenthesis {

	public ValidateParenthesis() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] argh) {
		Parser X = new Parser();
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			System.out.println(X.checkParenthesisBalance(in.next()));
		}

	}

	public static boolean checkParenthesisBalance(String input) {
		HashMap<Character, Character> map = new HashMap<Character, Character>() {
			{
				put('(', ')');
				put('{', '}');
				put('[', ']');
			}
		};

		Stack<Character> st = new Stack<Character>();
		for (char c : input.toCharArray()) {
			if (map.containsKey(c)) {
				st.push(c);
			} else if (st.isEmpty() || map.get(st.pop()) != c) {
				return false;
			}
		}
		return st.isEmpty();
	}
}

class Parser {
	public boolean checkParenthesisBalance(String input) {
		HashMap<Character, Character> map = new HashMap<Character, Character>() {
			{
				put('(', ')');
				put('{', '}');
				put('[', ']');
			}
		};

		Stack<Character> st = new Stack<Character>();
		for (char c : input.toCharArray()) {
			if (map.containsKey(c)) {
				st.push(c);
			} else if (st.isEmpty() || map.get(st.pop()) != c) {
				return false;
			}
		}
		return st.isEmpty();
	}
}
