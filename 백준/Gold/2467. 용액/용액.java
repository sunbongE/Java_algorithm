 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N = 10^5  => n^2풀이는 안된다.
 * 백트레킹은 깊이가 너무 깊어서 안되고
 * dp는 모르겠고
 * 완탐은 안될거같은데
 *
 * 수를 하나 선택해서 전부 비교
 *
 *
 *
 */
public class Main {
    static long minValue1 = 0, minValue2 = 0, minResult = Long.MAX_VALUE;
    static int nums[], N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        nums = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N-1;
        while(start<end){
            long tmp = nums[start]+nums[end];
            long absTmp = Math.abs(tmp);
            if(absTmp<minResult){
                minResult  = absTmp;
                minValue1 = nums[start];
                minValue2 = nums[end];
            }

            if(minResult == 0 ) break;


            if(tmp>=0){
                end--;
            }else if(tmp < 0){
                start++;
            }
        }

        System.out.println(minValue1+" "+minValue2);

    }
}
