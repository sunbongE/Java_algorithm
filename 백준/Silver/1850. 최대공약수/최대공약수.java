import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a =Long.parseLong(st.nextToken());
        long b =Long.parseLong(st.nextToken());

         change(gcd(a,b));
        System.out.println(sb);
    }

    private static void change(long n) {
        for(long i=0;i<n;i++){
            sb.append('1');
        }
    }

    private static long gcd(long a, long b) {
        if(b==0) return a;
        return gcd(b,a%b);
    }
}
