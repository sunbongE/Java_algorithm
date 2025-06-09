import java.io.*;
import java.util.*;

/**
 * 문제
 * 리유나는 세일러복을 정말 좋아한다. 그래서 세일러복을 많이 많이 만들어서 다른 사람들에게도 입히려고 한다.
 * 세일러복을 만들기 위해서는 두 가지 재료가 필요하다. 바로 하얀 티셔츠와 세일러 카라이다. 둘을 사용해서 세일러복을 만드는 방법은 다음과 같다.
 * 리유나는 세상 사람들의 하얀 티셔츠를 모두 세일러복으로 만들고 싶었지만, 예산과 시간 등의 부족으로 인해 N개의 하얀 티셔츠만을 구해올 수 있었다. 세일러 카라도 많이 만들어야 했지만, 마찬가지의 이유로 세일러 카라도 M개 밖에 만들지 못했다고 한다.
 * 게다가, 불행히도 세일러 카라를 너무 급하게 만들다 보니까 크기가 들쭉날쭉하게 되었다. 아무 티셔츠에나 아무 세일러 카라를 붙일 수 있는 것이 아니다. 하얀 티셔츠의 너비를 정수 w라고 할 때, 여기에 붙일 수 있는 세일러 카라는 두 가지가 있다. 너비가 w/2 이상 w×3/4이하인 세일러 카라를 붙일 수 있고, 혹은 카라가 옷보다 살짝 더 넓게 만들어서 너비가 w 이상 w×5/4 이하인 카라를 붙일 수 있다. 이 외의 경우는 리유나가 좋아하지 않기 때문에 만들지 않으려고 한다.
 * 리유나는 이런 악조건 속에서도 최대한 많은 세일러복을 만들고 싶어한다. 리유나가 세일러복을 최대 몇개나 만들 수 있는지 알려주자.
 *
 * 입력
 * 첫째 줄에는 가지고 있는 하얀 티셔츠와 세일러 카라의 개수가 주어진다. (1 ≤ N, M ≤ 200)
 * 두번째 줄부터 n개의 줄에는 각 하얀 티셔츠들의 너비 w가 주어진다. (1 ≤ w ≤ 1,000)
 * 다음 n+2번째 줄부터 m개의 줄에는 각 세일러 카라들의 너비 w가 주어진다. (1 ≤ w ≤ 1,000)
 * 둘 모두 너비 w는 정수이다.
 *
 * 출력
 * 첫째 줄에 만들 수 있는 세일러복 개수의 최댓값을 출력한다.
 *
 * 이분매칭
 * */

public class Main {
    static int N,M;
    static int[] T,S,match;
    static boolean[] v;
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        T = new int[N];
        S = new int[M];

        match = new int[M]; // 티셔츠와 매칭된 카라 기록.
        Arrays.fill(match,-1);
        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(br.readLine());
        }
        
        for (int i = 0; i < M; i++) {
            S[i] = Integer.parseInt(br.readLine());
        }

        int ans=0;

        /// 티 기준으로
        for (int i = 0; i < N; i++) {
            v = new boolean[M];
            if(dfs(i)) ans++;
        }

        System.out.println(ans);

//        System.out.println(Arrays.toString(match));
    }

    public static boolean dfs(int cur){

        for(int i=0; i<M;i++){
            if(v[i]) continue;
            // 매칭이 가능한지 확인. 불가능하면 넘어감.
            if(!isValid(S[i],T[cur])) continue;
            v[i] = true;



            // 매칭이 안됐고, 이전 매칭이 다른 매칭으로 변경할 수 있는 경우.
            if(match[i] == -1 || dfs(match[i])){
                match[i] = cur;
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(int s,int w) {
        // 너비가 w/2 이상 w×3/4이하인 || 너비가 w 이상 w×5/4 이하인
        return (w/2.0 <= s && s <= w*3.0/4.0) || (w <= s && s <= w*5.0/4.0);
    }
}
//c-t
//2-3
//4-7
//5-10
//6-5