import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 
 * @author 박승연
 * 
 *         이 문제는 무 적 권 DFS인듯 하다.
 * 
 *         매개변수에 리스트를 넘겨받으면서 리스트가 존재하는지도 체크를 해줘야한다.
 * 
 *         있으면 리턴각이 나왔다.
 *
 */
public class Main {
	static char[][] map;
	static boolean[] alpa = new boolean[26];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int R, C, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String inp[] = bf.readLine().split(" ");
		R = Integer.parseInt(inp[0]);
		C = Integer.parseInt(inp[1]);
		map = new char[R][];

		for (int i = 0; i < R; i++) {
			map[i] = bf.readLine().toCharArray();
		}
		dfs(0, 0,1);
		
		System.out.println(ans);

	}

	private static void dfs(int i, int j, int dis) {
		ans = Math.max(dis, ans);
		int ni = 0, nj = 0;
		alpa[map[i][j] - 'A'] = true;
		for (int di = 0; di < 4; di++) {// 4방향 둘러보다가 갈 수 있는 곳이고 처음 만나는 알파벳이면 가본다.
			ni = i + dx[di];
			nj = j + dy[di];
			if (isIn(ni,nj) && !alpa[map[ni][nj]-'A']) {
				dfs(ni, nj, dis+1);
			}
		}
		alpa[map[i][j] - 'A'] = false;
	}

	private static boolean isIn(int ni, int nj) {
		return 0<=ni && ni < R && 0<=nj && nj < C;
	}
}
