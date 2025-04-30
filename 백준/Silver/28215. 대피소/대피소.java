import java.io.*;
import java.util.*;
/**
 * 1. 조합을 구함
 * 2. 선택된 위치를 기준으로 거리가 최대인 값을 찾는다.
 *
 * */
public class Main {
    static int n,k, points[][],ans;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  //       BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        points= new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new int[]{x,y};
        }

        ans = Integer.MAX_VALUE;

        int[] combi = new int[k];
        getCombi(n,k,0,combi,0);
        System.out.println(ans);

    }

    private static void getCombi(int N, int c, int start, int[] combi,int cnt) {

        if(c == cnt){
            ans = Math.min(ans,simul(combi));
            return;
        }

        for (int i = start; i < N; i++) {
            combi[cnt] = i;
            getCombi(N,c,i+1,combi,cnt+1);
        }

    }

    private static int simul(int[] combi) {
        int maxDist = 0;
        // 가까운 대피소로 이동하는 거리 중 최대값을 갱신
        for (int i = 0; i < points.length; i++) {
            // 선택한 집이 대피소면 무시
            if(isPoint(i,combi)) continue;
            // 집
            int[] house = points[i];
            int minDist=Integer.MAX_VALUE;
            // 가장 가까운 대피소 찾기
            for (int j = 0; j < combi.length; j++) {
                int[] point = points[combi[j]];
                minDist = Math.min(minDist, getDist(house[0],house[1],point[0], point[1]));
            }
            maxDist = Math.max(minDist,maxDist);
        }
        return maxDist;
    }

    private static boolean isPoint(int i, int[] combi) {
        for (int j = 0; j < combi.length; j++) {
            if(combi[j] == i) return true;
        }
        return false;
    }

    private static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}
