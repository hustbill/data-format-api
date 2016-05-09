package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtil {

	public StreamUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   int[] arr = {1, 7, 5, 3, 9, 11} ;
	        int k = 2;
	        List<List<Integer>> pairs = findPairs(arr, k);
	        
	        for (List<Integer> pair : pairs) {
	            for (Integer i : pair) {
	                System.out.print(i + " ");
	            }
	            System.out.println();
	        }

	}
	
	  public static List<List<Integer>> findPairs (int[] arr, int k) {
	        Hashtable<Integer, Integer>  table = new Hashtable<Integer, Integer>();
	       
	        for (int x : arr) {
	            //List<Integer> list = Arrays.asList(arr);
	 	        //if (Arrays.asList(arr).contains(x - k)) {
	            if (IntStream.of(arr).anyMatch(i -> i == x - k)) {
	            	if (!table.contains(x - k))
	            		table.put(x - k, x);  
	            }

	            if (IntStream.of(arr).anyMatch(i -> i == x + k)) {
	              	if (!table.contains(x + k))
	              		table.put(x, x + k);
	            }
	        }
	        
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        for (int i = 0; i < table.size(); i++) {
	            List<Integer> pair = new ArrayList<Integer>();
	            pair.add(i);
	            pair.add(table.get(i));
	            result.add(pair);
	        }
	        
	        return result;
	    }

}
