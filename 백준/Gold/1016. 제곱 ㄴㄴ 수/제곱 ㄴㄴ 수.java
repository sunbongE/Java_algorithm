import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 제곱 ㄴㄴ수를 찾는 코드
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 받기 위한 BufferedReader와 StringTokenizer 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        // 제곱 ㄴㄴ수를 체크하기 위한 배열 선언
        boolean[] arr = new boolean[(int)(max - min + 1)];
        long ans = max - min + 1;  // 초기값: 범위 내의 모든 수가 제곱 ㄴㄴ수라고 가정하고 전체 개수 설정

        // 2부터 √max까지 반복하여 제곱수를 찾음
        for (long k = 2; k <= (long) Math.sqrt(max); k++) {
            long kk = k * k; // k의 제곱값 구하기 (제곱수)

            // 시작 위치 설정: min을 kk로 나눈 나머지를 기준으로 첫 배수를 찾음
            long start = min % kk == 0 ? 0 : kk - (min % kk);

            // start 위치부터 kk 간격으로 배수 체크하여 제곱수로 나눠지는 수는 제곱 ㄴㄴ수가 아니므로 제외
            for (long j = start; j < arr.length; j += kk) {
                if (!arr[(int) j]) {  // 아직 체크되지 않은 경우
                    ans--;       // 제곱수로 나눠지는 수이므로 제곱 ㄴㄴ수 개수에서 제외
                    arr[(int) j] = true;  // 해당 인덱스를 방문 처리
                }
            }
        }

        // 최종 제곱 ㄴㄴ수의 개수를 출력
        System.out.println(ans);
    }
}
