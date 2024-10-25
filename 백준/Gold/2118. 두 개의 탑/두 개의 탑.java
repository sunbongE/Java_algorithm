
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 위한 BufferedReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 지점의 개수 N 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] sumArr = new int[N+1];
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sumArr[i+1] = arr[i]+sumArr[i];
        }

        // 투포인터 시작
        int start = 0, end = 1,total = sumArr[N], result = 0,clockwise,counterclockwise;

        while(start < end && end <= N){
            clockwise = sumArr[end] - sumArr[start];
            counterclockwise = total - clockwise;
            result = Math.max(result,Math.min(clockwise, counterclockwise));

            if(clockwise >= counterclockwise){
                start++;
            }else{
                end++;
            }
        }
        System.out.println(result);

    }
}
