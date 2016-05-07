package jsonParser;

// http://m.oschina.net/blog/358432

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
* Created by IntelliJ IDEA.
* User: FLY
* Date: 11-9-13
* Time: 下午3:59
* To change this template use File | Settings | File Templates.
*/
public class FileWordCount {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/data"));
            String s;
            StringBuffer sb = new StringBuffer();
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            Map<String,Integer> map = new HashMap<String, Integer>();
            //StringTokenizer st = new StringTokenizer(sb.toString(),",.! \n");
            
            StringTokenizer st = new StringTokenizer(sb.toString(),", \n");
            
            while (st.hasMoreTokens()) {
                String letter = st.nextToken();
                int count;
                if (map.get(letter) == null) {
                    count = 1;
                } else {
                    count = map.get(letter).intValue() + 1;
                }
                map.put(letter,count);
            }
            Set<WordEntity> set = new TreeSet<WordEntity>();
            for (String key : map.keySet()) {
                set.add(new WordEntity(key,map.get(key)));
            }
            System.out.println("Print stat result:");
            for (Iterator<WordEntity> it = set.iterator(); it.hasNext(); ) {
                WordEntity w = it.next();

                if (w.getKey().equals("INFO:")) {
                    System.out.println("INFO Count :" +  w.getCount());
                }
                if (w.getKey().equals("WARN:")) {
                    System.out.println("WARN Count :" +  w.getCount());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到~！");
        } catch (IOException e) {
            System.out.println("文件读异常~！");
        }
    }
}
