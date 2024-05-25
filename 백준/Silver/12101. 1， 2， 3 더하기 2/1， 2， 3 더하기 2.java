import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = (sc.nextInt());
        int k = (sc.nextInt());

        ArrayList<String>[] arr = new ArrayList[n+3];

        for (int i = 1; i < n+3; i++) {
            arr[i] = new ArrayList<>();
        }
        arr[1].add("1");
        arr[2].add("1+1");
        arr[2].add("2");
        arr[3].add("1+2");
        arr[3].add("1+1+1");
        arr[3].add("2+1");
        arr[3].add("3");

        for (int i = 4; i <= n; i++) {

            for (int j = 1; j <=3 ; j++) {
                for (String cur : arr[i-j]) {
                    arr[i].add(cur+"+"+j);
                }
            }
        }
//            System.out.println( Arrays.toString(arr));

        if(arr[n].size() < k){
            System.out.println(-1);
        }else {
            Collections.sort(arr[n]);
//            System.out.println("==>"+arr[n]);
            System.out.println(arr[n].get(k-1));
        }


    }
}
