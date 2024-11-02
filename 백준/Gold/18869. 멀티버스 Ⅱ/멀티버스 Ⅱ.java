
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[m][n];
        int[][] points = new int[m][n];

        HashSet<Integer> set;
        ArrayList<Integer>  li;
        for (int i = 0; i < m; i++) {
            li = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            set = new HashSet<>();
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                set.add(arr[i][j]); // 중복을 제거하여 수 기록
            }
            li = new ArrayList<>(set);
            Collections.sort(li); // 행성의 크기 오름차순.
            // 압축 좌표 구하기.
            for (int j = 0; j < n; j++) {
                // 정렬된 크기에서 몇번째로 큰수인지 위치를 가져옴
                points[i][j] = Collections.binarySearch(li,arr[i][j]);
            }
        }
        int cnt=0;
        // 완성된 압축 좌표 points값을 순회하면서 같은 것을 찾아 개수 올려준다.
        for (int i = 0; i < m-1; i++) {
            for (int j = i+1; j < m; j++) {
                if(Arrays.equals(points[i], points[j])) cnt++;
            }
        }
//        show(points);
        System.out.println(cnt);

    }
    private static void show(int[][] a){
//        System.out.println("?");
        for (int[] ints : a) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
