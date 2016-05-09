package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.IntStream;


public class StreamUtil {

	public StreamUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   int[] arr = {1, 7, 5, 3, 9, 11} ;
		   Arrays.sort(arr);
		   
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
	            if (IntStream.of(arr).anyMatch(i -> i == x + k)) {
	            	table.put(x, x + k);
	            }
	        }
	        //System.out.print(table);
	        
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	       
	        for (int key : table.keySet()) {
	            List<Integer> pair = new ArrayList<Integer>();
	            pair.add(key);
	            pair.add(table.get(key));
	            //System.out.printf("%d , %d\n", key, table.get(key));
	            result.add(pair);
	        }
	        
	        return result;
	    }

}
