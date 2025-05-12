import java.io.*;
import java.util.*;

/**
 *
 * */
public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 //        BufferedReader br = new BufferedReader(new FileReader("test.txt"));



        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < N; tc++) {


        String input = br.readLine();
        HashSet<String> ben = new HashSet<>();

        while(true){
            String data = br.readLine();
            if(data.equals("what does the fox say?")) break;

            String[] arr = data.split(" ");
            ben.add(arr[2]);
        }


        for (String say : input.split(" ")) {
            if(!ben.contains(say)){
                sb.append(say+" ");
            }
        }
        sb.append("\n");
        }

        System.out.println(sb);
    }
}
