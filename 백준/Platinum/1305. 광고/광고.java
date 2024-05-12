
import java.util.*;
import java.io.*;

/**

 */
public class Main {

    static StringBuilder sb = new StringBuilder();
    static int[] pi;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        String text = sc.next();

        makePi(text);
        System.out.println(L-pi[L-1]);

    }

    // 접두사 접미사를 구하는거.
    public static void makePi(String word) {
        int j = 0;
        pi = new int[word.length()];

        for (int i = 1; i < word.length(); i++) {
            while (j > 0 && word.charAt(i) != word.charAt(j)) {
                j = pi[j - 1];
            }

            if (word.charAt(i) == word.charAt(j)) {
                pi[i] = ++j;
            }
        }
//        System.out.println(Arrays.toString(pi));
    }


//    public static void kmp(String)

}