import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long a = Long.parseLong(br.readLine());

        long s=0;
        long e = a;
//        System.out.println((long)Math.sqrt(Math.pow(2,63)));
        while(s<=e){
            long q = (s+e)/2;

            if(Math.pow(q,2) < a){
                s = q+1;
            }else{
                e = q-1;
            }
        }

        System.out.println(s);

    }
}
