import java.io.*;
import java.util.*;

/**
 *
 *
 * */
public class Main {
    static int subin, sis,time[], minTime=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 //        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        subin = Integer.parseInt(st.nextToken());
        sis = Integer.parseInt(st.nextToken());
        int len = 100001;
        time = new int[len];
        int ans;
        if(subin == sis)  ans = 1;
        else if(subin+1 == sis || subin-1 == sis || subin*2 == sis) {ans=1; time[sis]=1;}
        else ans = bfs(subin,sis);

//        System.out.println(Arrays.toString(time));
        System.out.println(time[sis]);
        System.out.println(ans);
    }

    private static Integer bfs(int subin, int sis) {
        Queue<Integer> q = new ArrayDeque<>();
        int visitCnt=0;
        q.offer(subin);

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == sis) visitCnt++;
            if(time[sis] != 0 && time[cur] > time[sis]) return visitCnt;
            // 앞
            int next = cur+1;
            if(!isOut(next)) {
                if(time[next]==0 || (time[next]!=0 && time[next] >= time[cur]+1)){
                    time[next]=time[cur]+1;
                    q.offer(next);
                }
            }
            // 뒤
            next = cur-1;

            if(!isOut(next)) {
                if(time[next]==0 || (time[next]!=0 && time[next] >= time[cur]+1)){
                    time[next]=time[cur]+1;
                    q.offer(next);
                }
            }

            // 순간이동.
            next = cur*2;

            if(!isOut(next)) {
                if(time[next]==0 || (time[next]!=0 && time[next] >= time[cur]+1)){
                    time[next]=time[cur]+1;
                    q.offer(next);
                }
            }
        }

        return visitCnt;
    }


    static private boolean isOut(int lo){
        return lo < 0 || lo >= 100001;
    }


}
