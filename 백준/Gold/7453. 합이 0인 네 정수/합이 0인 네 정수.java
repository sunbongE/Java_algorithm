
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 배열 4개의 최대 크기 4000
 * 모든 경우를 따지기에 크기가 매우 커서 2그룹으로 나눠서 진행
 * ab : a와b에 수들을 각각 덧셈한 값 ,cd : 마찬가지
 * 오름차순 정렬.
 * <p>
 * 두수 합이 0인거 찾기(투포인터)
 * 1. 합이 0보다 작으면 L ++
 * 2. 합이 0보다 크면 R --
 * 3. 0이면 개수를 세는데 동일한 값이 있을 수 있으니까 동일한거 개수도 세줌.
 *
 */
public class Main {
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A, B, C, D;
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        long[] ab = new long[N * N];
        long[] cd = new long[N * N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ab[idx] = A[i] + B[j];
                cd[idx++] = C[i] + D[j];
            }
        }

        // 투포인터로 값찾기.
        Arrays.sort(ab);
        Arrays.sort(cd);
//        System.out.println(Arrays.toString(ab));
//        System.out.println(Arrays.toString(cd));
        int left = 0, right = ab.length - 1;
        long sum = 0;

        while (left < ab.length && right >= 0) {
            sum = ab[left] + cd[right];
            // 두수의 합이 0보다 크면 R내리고, 작으면 L올리고
            if (sum < 0) {
                left++;
            } else if(sum > 0 ) {
                right--;
            }else{
                long sameCnt1=0;
                long sameCnt2=0;
                
                long num1 = ab[left];
                
                while(left<ab.length && num1 == ab[left] ){
                    left++;
                    sameCnt1++;
                }
                long num2 = cd[right];
                while(right>=0 && num2 == cd[right]){
                    right--;
                    sameCnt2++;
                }
                ans+=(sameCnt1*sameCnt2);
            }
        }


        System.out.println(ans);
    }
}
