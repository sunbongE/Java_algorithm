package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1996_지뢰찾기 {
	static int N;
	// 8방향 탐색
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void CountM(int i, int j, char[][] originMap, char[][] resultMap) {
		int cnt = 0;
		int nx = 0;
		int ny = 0;
		// 받은 위치에서 8방향 탐색
		for (int k = 0; k < 8; k++) {
			nx = i + dx[k];
			ny = j + dy[k];
			if ((nx >= 0 && nx < N) && (ny >= 0 && ny < N) && (originMap[nx][ny] != '.')) {
				cnt += (originMap[nx][ny] - '0'); // 숫자로 만들어서 더해준다.
			}
		}
		if (cnt < 10) {
			resultMap[i][j] = (char) (cnt+'0');
		} else { // 10이상은 M으로 표시.
			resultMap[i][j] ='M';
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		char[][] originMap = new char[N][]; // 초기 배열

		char[][] resultMap = new char[N][N]; // 지뢰 위치보이는 배열 (답)

		for (int i = 0; i < N; i++) {
			originMap[i] = br.readLine().toCharArray();
		}

		// 원본 배열 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 만약 '.'이 아니면 같은 위치 resultMap에 '*' 할당.
				if (originMap[i][j] != '.') {
					resultMap[i][j] = '*';
				} else {
					// . 인경우는 8방향 탐색하면서 주변에 '.'이 아니면 숫자로 바꿔서 더해주고 result에 저장.
					CountM(i, j, originMap, resultMap);
				}
			}
		}
		for (char[] line : resultMap) {
			for (char val : line) {
				System.out.print(val);
			}
			System.out.println();
		}
	}

}
