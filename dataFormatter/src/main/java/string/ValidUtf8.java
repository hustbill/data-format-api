import java.util.*;

public class ValidUtf8 {
    public boolean validUtf8(int[] data) {
        int count = 0;
        for (int c : data) {
           if (count == 0) {
               if ((c >> 5) == 0b110) count = 1;
               else if ((c >> 4) == 0b1110) count = 2;
               else if ((c >> 3) == 0b11110) count = 3;
               else if ((c >> 7) == 1) return false;
           } else {
               if ((c >> 6) != 0b10) return false;
               count--;
           }
        }
        return count == 0;
    }
    
    public static void main(String[] args) {
        ValidUtf8 sl = new ValidUtf8();
        
        
        int[] data1 = {197, 130, 1};
        int[] data2 = {235, 140, 4};
        
        System.out.println(sl.validUtf8(data1));
        System.out.println(sl.validUtf8(data2));
    }
}

