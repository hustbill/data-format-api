package jsonParser;

import java.util.*;
import java.util.regex.*;

import org.xml.sax.Parser;

class Test{
	
	public static void main(String []argh)
	{
		Parser X=new Parser();
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			System.out.println(X.checkParenthesisBalance(in.next()));
		}
		
	}
    
    public static boolean checkParenthesisBalance(String input) {
        HashMap<Character, Character> map = new HashMap<Character, Character>() { {
           put('(', ')');
            put('{', '}');
        }};

        Stack<Character> st = new Stack<Character>();
        for (char c:  input.toCharArray()) {
            if (map.containsKey(c)) {
                st.push(c);
            } else if (st.isEmpty() || map.get(st.pop()) != c) {
                return false;
            }
        }
        return st.isEmpty();
    } 
}


