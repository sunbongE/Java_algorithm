import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        BigInteger A = BigInteger.ONE;
        for(int i = 0; i < N; i++){
            A = A.multiply(new BigInteger(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        BigInteger B = BigInteger.ONE;
        for(int i = 0; i < M; i++){
            B = B.multiply(new BigInteger(st.nextToken()));
        }

        BigInteger result = A.gcd(B);  // BigInteger의 gcd 메서드 사용
        if(result.toString().length() > 9){
            String ans = result.toString();
            System.out.println(ans.substring(ans.length() - 9));  // 마지막 9자리 출력
        } else {
            System.out.println(result);
        }
    }
}
