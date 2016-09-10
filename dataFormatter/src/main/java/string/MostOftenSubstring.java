package string;
import java.util.*;
 
/**
 * Created by Hua on 2016-05-17.
 * 面试题
 * 重复出现次数最多的子串中那个子串
 * "abcabc" 2次abc
 * "bbbb" 4次b
 * ref : https://gist.github.com/zachelko/9177987
 */
public class MostOftenSubstring {
    public static int getLongestMaxCountString(String s) {
    	int result = 1;
    	String substr;
       
        Map<String, Integer> map = new HashMap<>();//<sub-string，freq>
        for (int i = 1; i <= s.length(); i++) {
            
            for (int j = 0; j < s.length(); j++) {
                if (i + j <= s.length()) {
                    substr = s.substring(j, j + i);
                    if (substr.length() >=2) {
                        if (map.containsKey(substr) )
                            map.put(substr, map.get(substr) + 1);
                        else map.put(substr, 1);
                    }
                
                }
            }
        }
        System.out.print(map);
        int count;
        substr = null;
        Map<Integer, String> map1 = new HashMap<>();//<次数，最长子串>
        Set<String> keys = map.keySet();
        for (String key : keys) {
            count = map.get(key);
            if (map1.containsKey(count)) {
                substr = map1.get(count);
                result = count;
                    map1.put(count, key);
            } else
                map1.put(count, key);
        }
        count = 0;
        Set<Integer> set = map1.keySet();
        for (Integer a : set) {// find the most occurance of substr
            if (a > count) {
                count = a;
                substr = map1.get(a);
                result = count;
            }
        }
        return result;
    }
 
    public static void main(String[] args) {
        String s = "ababab";
        System.out.println(getLongestMaxCountString(s));

//        s = "ababab";
//        System.out.println(getLongestMaxCountString(s));
    }
    /**输出：
     * abcd
     * b
     * null
     */
}