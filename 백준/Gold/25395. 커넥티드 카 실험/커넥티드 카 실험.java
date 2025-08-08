import java.io.*;
import java.util.*;
/**
 * n 개의 차는 오름차순의 위치로 나온다.
 * 그래서 i번 위치 차가 이동할 수 있는 위치 범위에 있는지 확인하는 것이 필요한데
 * i-1위치의 값과 X[i] + H[i] 혹은 X[i] - H[i] 이게 범위내에 있는지 확인..
 *
 * */
public class Main {
    static int n, s,xData[],hData[];  // 차 대수 N, 시작 차량 번호 S

    // 정답에 추가된 차량 번호
    static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   //      BufferedReader br = new BufferedReader(new FileReader(new File("./test.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());    // 차량 대수
        s = Integer.parseInt(st.nextToken());    // 시작 차량 번호

        xData = new int[n+1];
        hData = new int[n+1];

        // 위치 x_i
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            xData[i] = Integer.parseInt(st.nextToken());
        }
        // 연료량 h_i
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            hData[i] = Integer.parseInt(st.nextToken());
        }

        sol();
        StringBuilder sb = new StringBuilder();
        Collections.sort(ans);
        for (Integer an : ans) {
            sb.append(an+" ");        }
        System.out.println(sb.toString());
    }

    private static void sol(){
        Queue<Car> q = new LinkedList<>();
        q.offer(new Car(s,xData[s],hData[s])); // 연결된 차 S부터 시작.
        boolean[] v = new boolean[n+1];
        v[0] = true; v[s] = true;

        while(!q.isEmpty()){
            Car cur = q.poll();
            ans.add(cur.no);
            // 왼쪽으로 이동
            for (int i = cur.no-1; i >= 0; i--) {
                int distance = Math.max(0, cur.x-cur.h);

                // 범위에있는 차 발견.
                if(isInRange(distance,cur.x,xData[i]) ){
                    if( v[i]) continue;
                    v[i] = true;
                    q.offer(new Car(i,xData[i],hData[i]));
                }else{
                    break;
                }
            }

            // 오른쪽으로 이동.
            for (int i = cur.no+1; i<=n; i++) {
                int distance =  cur.x+cur.h;

                // 범위에있는 차 발견.
                if(isInRange(cur.x,distance,xData[i])){

                    if( v[i]) continue;
                    v[i] = true;
                    q.offer(new Car(i,xData[i],hData[i]));
                }else{
                    break;
                }
            }

        }
    }

    private static boolean isInRange(int start, int end, int target){
        return start <= target && target <= end;
    }


    // 차량 정보 클래스
    private static class Car {
        int no;   // 차량 번호 (1..N)
        int x;    // 위치 좌표
        int h;    // 남은 연료량

        Car(int no, int x, int h) {
            this.no = no;
            this.x  = x;
            this.h  = h;
        }
    }
}
