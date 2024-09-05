import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
집합 b를 오름차순하고 여기서 집합a에 있는 값을 하나씩 이분탐색으로 찾아본다.

 */
public class Main {
    static int a, b;
    static int[] arrA, arrB;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());

        arrA = new int[sizeA];
        arrB = new int[sizeB];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<sizeA;i++){
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<sizeB;i++){
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrA);
        Arrays.sort(arrB);
        boolean isNull = true;

        int cnt=0;
        for (Integer num : arrA) {
            if (!binarySearch(0, sizeB-1, num)) {
                isNull = false;
                sb.append(num + " ");
                cnt++;
            }
            ;
        }

        if (isNull) {
            sb.append(0);
        }

        if(!isNull) System.out.println(cnt);
        System.out.println(sb);
    }

    private static boolean binarySearch(int left, int right, int num) {

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arrB[mid] == num) {
                return true;
            } else if (arrB[mid] > num) {
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return false;
    }
}
