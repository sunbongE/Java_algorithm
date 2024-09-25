import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String data = br.readLine();

        int targetIdx = data.indexOf("*"); // *의 인덱스

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            sb.append(sol(tmp,data,targetIdx));
        }

        System.out.println(sb);



    }

    private static String sol(String tmp, String data, int targetIdx) {
        int pre=0;
        for (int i = 0; i < targetIdx; i++) {
            if(tmp.charAt(i) != data.charAt(i)) return "NE\n";
            pre=i;
        }

        for (int i = 0; i < data.length()-targetIdx-1; i++) {
            if(tmp.length()-1-i == pre) return "NE\n";
            if(tmp.charAt(tmp.length()-1-i) != data.charAt(data.length()-1-i)) return "NE\n";
        }

        return "DA\n";
    }
}
