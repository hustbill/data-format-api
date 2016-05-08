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
            System.out.println("File Not Found! ");
        } catch (IOException e) {
            System.out.println("Read File Exception! ");
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
