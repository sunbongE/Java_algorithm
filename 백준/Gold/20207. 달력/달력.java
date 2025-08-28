import java.io.*;
import java.util.*;
/**
 * n 개의 차는 오름차순의 위치로 나온다.
 * 그래서 i번 위치 차가 이동할 수 있는 위치 범위에 있는지 확인하는 것이 필요한데
 * i-1위치의 값과 X[i] + H[i] 혹은 X[i] - H[i] 이게 범위내에 있는지 확인..
 *
 * */
public class Main {
    static int len = 370, days[];

    public static void main(String[] args) throws IOException {

      days = new int[len];

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int n = Integer.parseInt(br.readLine());
      StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            days[start]+=1;
            days[end+1]-=1;
        }

        int ans =0;
        boolean flag = false;
        int w=0, maxH=Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            days[i] += days[i-1];

            if(days[i] == 0 ){
                flag = false;
                if(maxH != 0 && w!=0){
                    ans+= (maxH*w);
                    maxH=0; w=0;
                }
            }
            else flag = true;

            if(flag){ // 높이가 있고, 연속된 구간.
                maxH = Math.max(maxH,days[i]);
                w++;
            }

        }
        System.out.println(ans);

    }
}