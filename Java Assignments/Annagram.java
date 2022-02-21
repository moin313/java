package Day6;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Annagram {
    public static void main(String[] args) {
        String s1 = "ADFcef";
        String s2 = "fdFcEa";
        System.out.println(isAnagram(s1, s2));
    }
    public static boolean isAnagram(String s1, String s2){
        s1 = s1.toLowerCase(Locale.ROOT);
        s2 = s2.toLowerCase(Locale.ROOT);
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }
}
/*
    Those who are done with all the questions can attempt this question if not already attempted:
        https://github.com/codageaider/java-training/blob/master/java-training/src/homework/homework_3/HomeWork.java
        This is the extended version where you have to return top k played songs
*/