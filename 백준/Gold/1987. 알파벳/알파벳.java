import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] di = {-1,1,0,0}, dj = {0,0,-1,1};
    static int r,c,maxCnt=0;
    static char[][] map;
    public static void main(String[] args) throws IOException {

        // 입력 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader(new File("./test.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String input = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        boolean[] v = new boolean[26];
        v[map[0][0]-'A'] = true;
        dfs(0,0,1,v);
        System.out.println(maxCnt);
    }

    public static void dfs(int ci, int cj, int cnt, boolean[] v){
        if(ci == r && cj == c) {
            maxCnt =  Math.max(maxCnt, cnt);
            return;
        }
        maxCnt =  Math.max(maxCnt, cnt);
        int ni,nj;
        for (int dir = 0; dir < 4; dir++) {
            ni = ci + di[dir];
            nj = cj + dj[dir];
            if(!isOut(ni,nj) && !v[map[ni][nj]-'A']){
                v[map[ni][nj]-'A']=true;
                dfs(ni,nj,cnt+1,v);
                v[map[ni][nj]-'A']=false;
            }
        }
    }

    private static boolean isOut(int ni, int nj){
        return 0>ni || ni >= r || 0>nj || nj >= c;
    }
}
