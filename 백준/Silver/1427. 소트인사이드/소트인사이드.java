import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //        BufferedReader br = new BufferedReader(new FileReader("test.txt"));

        String inp = br.readLine();

        ArrayList<Integer> li = new ArrayList<>();

        for (int i = 0; i < inp.length(); i++) {
            li.add(inp.charAt(i)-'0');
        }

        Collections.sort(li, Collections.reverseOrder());
//        System.out.println(li);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < li.size(); i++) {
            sb.append(li.get(i));
        }
        System.out.println(sb);
    }
}
