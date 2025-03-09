import java.io.*;
import java.util.*;

public class Main {

    static int n,T,P,Tresult, Presult,Presult2;
    static final int len = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] input = new int[len];

        Tresult=0;

        for (int i = 0; i < len; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        for(int i = 0; i < len; i++){
            sol(input[i]);
        }

        Presult = n/P;
        Presult2 = n%P;

        System.out.println(Tresult);
        System.out.println(Presult+" "+Presult2);
    }

    private static void sol(int in){
        if(in <= 0 ) return;

        Tresult++;

        if(in > T){
            Tresult += (in-T)/T;
            if(in%T != 0) Tresult++;
        }
    }
}